package com.vw.address.sample.imp;

import com.vw.address.County;
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
public class CountyBatchConfiguration {

    @Value(value = "${geonames.sample.file}")
    private String geonamesSampleFile;

    @Bean
    public Job importCountiesJob(JobBuilderFactory jobs, @Qualifier("countiesStep1") Step s1, CountyJobCompletionNotificationListener listener) {
        return jobs.get("importCountiesJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step countiesStep1(StepBuilderFactory stepBuilderFactory, FlatFileItemReader<SampleData> geonamesReader,
                               CountyWriter countyWriter , CountyProcessor countyProcessor) {
        return stepBuilderFactory.get("countiesStep1")
                .<SampleData, County> chunk(1000)
                .reader(geonamesReader)
                .processor(countyProcessor)
                .writer(countyWriter)
                .build();
    }

}
