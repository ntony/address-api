package com.vw.address.sample.imp;

import com.vw.address.Country;
import com.vw.address.sample.SampleData;
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
import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author ffazil
 * @since 08/03/16
 */
@Slf4j
@Configuration
@EnableBatchProcessing
public class CountryBatchConfiguration {

    @Value(value = "${geonames.sample.file}")
    private String geonamesSampleFile;

    @Bean
    public Job importCountriesJob(JobBuilderFactory jobs, @Qualifier("countriesStep1") Step s1, CountryJobCompletionNotificationListener listener) {
        return jobs.get("importCountriesJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(s1)
                .end()
                .build();
    }


    @Bean
    public Step countriesStep1(StepBuilderFactory stepBuilderFactory, FlatFileItemReader<SampleData> geonamesReader,
                             CountryWriter countryWriter , CountryProcessor countryProcessor) {
        return stepBuilderFactory.get("countriesStep1")
                .<SampleData, Country> chunk(1000)
                .reader(geonamesReader)
                .processor(countryProcessor)
                .writer(countryWriter)
                .build();
    }

    @Bean
    public FlatFileItemReader<SampleData> geonamesReader() throws IOException {
        ClassPathResource resource = new ClassPathResource(geonamesSampleFile);
        Scanner scanner = new Scanner(resource.getInputStream());
        String line = scanner.nextLine();
        scanner.close();

        FlatFileItemReader<SampleData> itemReader = new FlatFileItemReader<SampleData>();
        itemReader.setResource(resource);

        // DelimitedLineTokenizer defaults to comma as its delimiter
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(line.split(","));
        tokenizer.setStrict(false);

        DefaultLineMapper<SampleData> lineMapper = new DefaultLineMapper<SampleData>();
        lineMapper.setFieldSetMapper(fields -> {
            SampleData data = new SampleData();

            data.setGeonameId(fields.readInt("geonameid"));
            data.setName(fields.readString("name"));
            data.setAsciiName(fields.readString("asciiname"));
            data.setAlternatenames(fields.readString("alternatenames"));
            data.setLatitude(fields.readString("latitude"));
            data.setLongitude(fields.readString("longitude"));
            data.setFeatureClass(fields.readString("feature class"));
            data.setFeatureCode(fields.readString("feature code"));
            data.setAlpha2Code(fields.readString("alpha2Code"));
            data.setCc2(fields.readString("cc2"));
            String admin1Code = (fields.readString("admin1 code").equals("") ? "0" : fields.readString("admin1 code"));
            data.setAdmin1Code(Integer.parseInt(admin1Code));
            data.setAdmin2Code(fields.readString("admin2 code"));
            data.setAdmin3Code(fields.readString("admin3 code"));
            String admin4Code = (fields.readString("admin4 code").equals("") ? "0" : fields.readString("admin4 code"));
            data.setAdmin4Code(new BigInteger(admin4Code));
            data.setPopulation(new BigInteger(fields.readString("population")));
            data.setElevation(fields.readString("elevation").equals("") ? 0 : Integer.parseInt(fields.readString("elevation")));
            data.setDem(new Integer(fields.readString("dem")));
            data.setTimezone(fields.readString("timezone"));
            data.setModificationDate(fields.readString("modification date"));

            return data;
        });

        lineMapper.setLineTokenizer(tokenizer);
        itemReader.setLineMapper(lineMapper);
        itemReader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
        itemReader.setLinesToSkip(1);

        return itemReader;
    }

}
