package com.vw.address;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ffazil
 * @since 29/03/16
 */
public class ZipRepositoryTest extends AbstractIntegrationTest{

    @Autowired
    private ZipRepository zipRepository;

    @Test
    public void createsZip(){
        Zip zip = GeoFixture.standardZip();
        zip = zipRepository.save(zip);
        Assert.assertNotNull(zip.getPostalCode());
    }
}
