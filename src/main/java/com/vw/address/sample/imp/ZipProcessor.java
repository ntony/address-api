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

    @Autowired
    private ZoneRepository zoneRepository;

    @Override
    public Zip process(SampleZipData item) throws Exception {

        Zone zone = zoneRepository.findByName(item.getPlaceName());

        return new Zip(
                item.getPostalCode(),
                zone,
                new Point(Double.valueOf(item.getLongitude()), Double.valueOf(item.getLatitude())),
                item.getAccuracy()
        );
    }
}
