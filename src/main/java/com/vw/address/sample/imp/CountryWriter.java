package com.vw.address.sample.imp;

import com.vw.address.Country;
import com.vw.address.CountryRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ffazil
 * @since 08/03/16
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CountryWriter implements ItemWriter<Country>{

    @NonNull
    private final CountryRepository countryRepository;

    @Override
    public void write(List<? extends Country> items) throws Exception {
        countryRepository.save(items);
    }
}
