package com.vw.address;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;

import java.util.Currency;

/**
 * @author ffazil
 * @since 29/03/16
 */

public class ProvinceRepositoryTest extends AbstractIntegrationTest{

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CountryRepository countryRepository;


    @Test
    public void createsProvinceForCountry(){
        Country country = GeoFixture.standardCountry();
        country = countryRepository.save(country);

        Province province = GeoFixture.standardProvince(country);
        province = provinceRepository.save(province);

        Assert.assertNotNull(province.getCountry());
    }
}
