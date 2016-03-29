package com.vw.address;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ffazil
 * @since 29/03/16
 */

public class ZoneRepositoryTest extends AbstractIntegrationTest{

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CountryRepository countryRepository;




    @Test
    public void createsZoneForCounty(){
        Country country = GeoFixture.standardCountry();
        country = countryRepository.save(country);

        Province province = GeoFixture.standardProvince(country);
        province = provinceRepository.save(province);

        County county = GeoFixture.standardCounty(province);
        county = countyRepository.save(county);

        Zone zone = GeoFixture.standardZone(county);
        zone = zoneRepository.save(zone);

        Assert.assertNotNull(zone.getCounty().getProvince().getCountry());
    }
}
