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

public class CountyRepositoryTest extends AbstractIntegrationTest{

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CountryRepository countryRepository;




    @Test
    public void createsCountyForProvince(){
        Country country = GeoFixture.standardCountry();
        country = countryRepository.save(country);

        Province province = GeoFixture.standardProvince(country);
        province = provinceRepository.save(province);

        County county = GeoFixture.standardCounty(province);
        county = countyRepository.save(county);

        Assert.assertNotNull(county.getProvince().getCountry());
    }
}
