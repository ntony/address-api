package com.vw.address.sample.imp;

import com.vw.address.County;
import com.vw.address.Province;
import com.vw.address.ProvinceRepository;
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
public class CountyProcessor implements ItemProcessor<SampleData, County> {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public County process(SampleData item) throws Exception {
        if(item.getFeatureCode().equals("ADM2")){
            Province province = provinceRepository.findByAdmin1Code(item.getAdmin1Code());

            return new County(
                    item.getAsciiName(),
                    new Double(item.getPopulation().toString()),
                    new Point(Double.valueOf(item.getLongitude()), Double.valueOf(item.getLatitude())),
                    item.getFeatureCode(),
                    item.getAdmin1Code(),
                    item.getAdmin2Code(),
                    province
            );
        }

        return null;
    }
}
