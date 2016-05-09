package com.vw.address;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ffazil
 * @since 29/03/16
 */
@Slf4j
public class GoecodingTest {

    protected GeoApiContext context;

    @Before
    public void setUp() throws Exception {
        context = new GeoApiContext().setApiKey("AIzaSyBYgyRKPae6KWrRjZ7ly13d4DdAWF127rM");
    }

    @Test
    public void testGeocode_Trinity_Acres_Woods() throws Exception {
        GeocodingResult[] results =  GeocodingApi.geocode(context,
                "Trinity acres and woods, bangalore").await();
        log.info("Response {}", results[0].formattedAddress);
    }
}
