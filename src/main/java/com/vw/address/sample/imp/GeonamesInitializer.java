package com.vw.address.sample.imp;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author nidhintony
 * @since 20/03/16
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GeonamesInitializer implements CommandLineRunner{

    @NonNull
    private JobLauncher jobLauncher;

    @NonNull
    private Job importCountriesJob;

    @NonNull
    private Job importProvincesJob;

    @NonNull
    private Job importCountiesJob;

    @NonNull
    private Job importZonesJob;

    @NonNull
    private Job importZipJob;

    @Override
    public void run(String... strings) throws Exception {
        ExitStatus countriesExitStatus = executeJob(importCountriesJob);
        ExitStatus provincesExitStatus;
        ExitStatus countiesExitStatus;
        ExitStatus zonesExitStatus;

        if(countriesExitStatus.equals(ExitStatus.COMPLETED)){
            provincesExitStatus = executeJob(importProvincesJob);
            if(provincesExitStatus.equals(ExitStatus.COMPLETED)){
                countiesExitStatus = executeJob(importCountiesJob);
                if(countiesExitStatus.equals(ExitStatus.COMPLETED)){
                    zonesExitStatus = executeJob(importZonesJob);
                }
            }
        }

        ExitStatus zipExitStatus = executeJob(importZipJob);

    }

    private ExitStatus executeJob(Job job)  throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        log.info("Starting Job {}", job.getName());
        JobParameters jobParameters =
                new JobParametersBuilder()
                        .addLong("time",System.currentTimeMillis()).toJobParameters();
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);
        log.info("Status {}", jobExecution.getExitStatus());
        return jobExecution.getExitStatus();
    }


}
