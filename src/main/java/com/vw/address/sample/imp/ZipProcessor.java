package com.vw.address.sample.imp;

import com.vw.address.*;
import com.vw.address.sample.SampleZipData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

/**
 * @author nidhintony
 * @since 20/03/16
 */
@Slf4j
@Component
public class ZipProcessor implements ItemProcessor<SampleZipData, Zip> {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CountyRepository countyRepository;

    @Override
    public Zip process(SampleZipData item) throws Exception {

        Country country = countryRepository.findByAlpha2Code(new Alpha2Code(item.getCountryCode()));
        Province province = provinceRepository.findByName(item.getAdminName1());
        County county = countyRepository.findByName(item.getAdminName2());

        return new Zip(
                item.getPostalCode(),
                country,
                item.getPlaceName(),
                province,
                county,
                new Point(Double.valueOf(item.getLongitude()), Double.valueOf(item.getLatitude())),
                item.getAccuracy()
        );
    }
}
