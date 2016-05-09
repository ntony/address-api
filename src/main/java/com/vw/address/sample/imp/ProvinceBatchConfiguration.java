package com.vw.address.sample.imp;

import com.vw.address.Province;
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
 * @since 13/03/16
 */
@Slf4j
@Configuration
@EnableBatchProcessing
public class ProvinceBatchConfiguration {

    @Value(value = "${geonames.sample.file}")
    private String geonamesSampleFile;

    @Bean
    public Job importProvincesJob(JobBuilderFactory jobs, @Qualifier("provincesStep1") Step s1, ProvinceJobCompletionNotificationListener listener) {
        return jobs.get("importProvincesJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step provincesStep1(StepBuilderFactory stepBuilderFactory, FlatFileItemReader<SampleData> geonamesReader,
                               ProvinceWriter provinceWriter , ProvinceProcessor provinceProcessor) {
        return stepBuilderFactory.get("provincesStep1")
                .<SampleData, Province> chunk(1000)
                .reader(geonamesReader)
                .processor(provinceProcessor)
                .writer(provinceWriter)
                .build();
    }
}
