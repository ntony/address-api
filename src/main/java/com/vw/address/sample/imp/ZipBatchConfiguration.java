package com.vw.address.sample.imp;

import com.vw.address.Zip;
import com.vw.address.sample.SampleZipData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author nidhintony
 * @since 20/03/16
 */
@Slf4j
@Configuration
@EnableBatchProcessing
public class ZipBatchConfiguration {

    @Value(value = "${geonames.zip.file}")
    private String geonamesZipFile;

    @Bean
    public Job importZipJob(JobBuilderFactory jobs, @Qualifier("zipStep1") Step s1, ZipJobCompletionNotificationListener listener) {
        return jobs.get("importZipJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(s1)
                .end()
                .build();
    }

    @Bean
    public Step zipStep1(StepBuilderFactory stepBuilderFactory, FlatFileItemReader<SampleZipData> geonamesZipReader,
                               ZipWriter zipWriter , ZipProcessor zipProcessor) {
        return stepBuilderFactory.get("zipStep1")
                .<SampleZipData, Zip> chunk(1000)
                .reader(geonamesZipReader)
                .processor(zipProcessor)
                .writer(zipWriter)
                .build();
    }

    @Bean
    public FlatFileItemReader<SampleZipData> geonamesZipReader() throws IOException {
        ClassPathResource resource = new ClassPathResource(geonamesZipFile);
        Scanner scanner = new Scanner(resource.getInputStream());
        String line = scanner.nextLine();
        scanner.close();

        FlatFileItemReader<SampleZipData> itemReader = new FlatFileItemReader<SampleZipData>();
        itemReader.setResource(resource);

        // DelimitedLineTokenizer defaults to comma as its delimiter
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(line.split(","));
        tokenizer.setStrict(false);

        DefaultLineMapper<SampleZipData> lineMapper = new DefaultLineMapper<SampleZipData>();
        lineMapper.setFieldSetMapper(fields -> {
            SampleZipData data = new SampleZipData();

            data.setCountryCode(fields.readString("country code"));
            data.setPostalCode(fields.readString("postal code"));
            data.setPlaceName(fields.readString("place name"));
            data.setAdminName1(fields.readString("admin name1"));
            data.setAdminCode1(fields.readInt("admin code1"));
            data.setAdminName2(fields.readString("admin name2"));

            String admin2Code = (fields.readString("admin code2").equals("") ? "0" : fields.readString("admin code2"));
            data.setAdminCode2(admin2Code);

            data.setAdminName3(fields.readString("admin name3"));

            String admin3Code = (fields.readString("admin code3").equals("") ? "0" : fields.readString("admin code3"));
            data.setAdminCode3(Integer.parseInt(admin3Code));

            data.setLatitude(fields.readString("latitude"));
            data.setLongitude(fields.readString("longitude"));
            data.setAccuracy(fields.readInt("accuracy"));

            return data;
        });

        lineMapper.setLineTokenizer(tokenizer);
        itemReader.setLineMapper(lineMapper);
        itemReader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
        itemReader.setLinesToSkip(1);

        return itemReader;
    }

}
