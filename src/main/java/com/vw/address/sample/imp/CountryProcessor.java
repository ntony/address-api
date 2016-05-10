package com.vw.address.sample.imp;

import com.vw.address.Alpha2Code;
import com.vw.address.Alpha3Code;
import com.vw.address.Continent;
import com.vw.address.Country;
import com.vw.address.sample.SampleData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.Locale;

/**
 * @author ffazil
 * @since 08/03/16
 */
@Slf4j
@Component
public class CountryProcessor implements ItemProcessor<SampleData, Country>{
    @Override
    public Country process(SampleData item) throws Exception {
        if(item.getFeatureCode().equals("PCLI")){
            return new Country(item.getAsciiName(),
                    new Alpha2Code(item.getAlpha2Code()),
                    new Alpha3Code(item.getAlpha2Code()),
                    Continent.Europe,
                    new Double(0),
                    new Double(item.getPopulation().toString()),
                    new Point(Double.valueOf(item.getLongitude()), Double.valueOf(item.getLatitude())));
        }

        return null;
    }


}
