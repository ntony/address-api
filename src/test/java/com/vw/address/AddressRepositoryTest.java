package com.vw.address;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ffazil
 * @since 29/03/16
 */
public class AddressRepositoryTest extends AbstractIntegrationTest{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ZipRepository zipRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void createsAddress(){
        Country country = GeoFixture.standardCountry();
        country = countryRepository.save(country);

        Province province = GeoFixture.standardProvince(country);
        province = provinceRepository.save(province);

        County county = GeoFixture.standardCounty(province);
        county = countyRepository.save(county);

        Zone zone = GeoFixture.standardZone(county);
        zone = zoneRepository.save(zone);

        Zip zip = GeoFixture.standardZip();
        zip = zipRepository.save(zip);

        Address address = GeoFixture.standardAddress(zone, zip);
        address = addressRepository.save(address);

        Assert.assertNotNull(address.getZone().getCounty().getProvince().getCountry());

    }
}
