package com.vw.address.sample.imp;

import com.vw.address.County;
import com.vw.address.CountyRepository;
import com.vw.address.Zone;
import com.vw.address.sample.SampleData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

/**
 * @author nidhintony
 * @since 14/03/16
 */
@Slf4j
@Component
public class ZoneProcessor implements ItemProcessor<SampleData, Zone> {

    @Autowired
    private CountyRepository countyRepository;

    @Override
    public Zone process(SampleData item) throws Exception {
        if (
            item.getFeatureCode().startsWith("PPL") &&
                (
                    !item.getFeatureCode().equals("PPLH") ||
                    !item.getFeatureCode().equals("PPLQ") ||
                    !item.getFeatureCode().equals("PPLW")
                )
            ) {
            County county = countyRepository.findByFeatureCodeAndAdmin2Code("ADM2", item.getAdmin2Code());

            return new Zone(
                    item.getAsciiName(),
                    new Double(item.getPopulation().toString()),
                    new Point(Double.valueOf(item.getLongitude()), Double.valueOf(item.getLatitude())),
                    county
            );
        }
        return null;
    }
}
