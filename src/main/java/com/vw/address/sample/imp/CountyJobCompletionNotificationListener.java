package com.vw.address.sample.imp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * @author nidhintony
 * @since 14/03/16
 */
@Slf4j
@Component
public class CountyJobCompletionNotificationListener extends JobExecutionListenerSupport {

    CountyJobCompletionNotificationListener() {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! County Import JOB FINISHED! Time to verify the results");

        }
    }
}
