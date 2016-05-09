package com.vw.address.sample.imp;

import com.vw.address.Zone;
import com.vw.address.sample.SampleData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author nidhintony
 * @since 14/03/16
 */
@Slf4j
@Configuration
@EnableBatchProcessing
public class ZoneBatchConfiguration {

    @Value(value = "${geonames.sample.file}")
    private String geonamesSampleFile;

    @Bean
    public Job importZonesJob(JobBuilderFactory jobs, @Qualifier("zonesStep1") Step s1, ZoneJobCompletionNotificationListener listener) {
        return jobs.get("importZonesJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step zonesStep1(StepBuilderFactory stepBuilderFactory, FlatFileItemReader<SampleData> geonamesReader,
                              ZoneWriter zoneWriter , ZoneProcessor zoneProcessor) {
        return stepBuilderFactory.get("zonesStep1")
                .<SampleData, Zone> chunk(1000)
                .reader(geonamesReader)
                .processor(zoneProcessor)
                .writer(zoneWriter)
                .build();
    }
}
