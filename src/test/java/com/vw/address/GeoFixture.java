package com.vw.address;

import org.springframework.data.geo.Point;

import java.util.Arrays;
import java.util.Currency;

/**
 * @author ffazil
 * @since 29/03/16
 */
public class GeoFixture {


    public static Country standardCountry(){
        return new Country("India",
                new Alpha2Code("IN"),
                new Alpha3Code("IND"),
                Continent.Asia,
                0.0,
                0.0,
                new Point(0.0, 0.0));
    }

    public static Province standardProvince(Country country){
        return new Province("Karnataka",
                new Point(0.0, 0.0),
                0.0,
                "test",
                19,
                country);
    }

    public static County standardCounty(Province province){
        return new County("Bangalore Urban",
                0.0,
                new Point(0.0, 0.0),
                "test",
                19,
                "test",
                province);
    }

    public static Zone standardZone(County county){
        return new Zone("Sarjapur road",
                0.0,
                new Point(0.0, 0.0),
                county);
    }

    public static Zip standardZip(){
        return new Zip("560102",
                new Zone(),
                new Point(0.0, 0.0),
                3);
    }

    public static Address standardAddress(Zone zone, Zip zip){
        return new Address("addr001",
                null,
                Arrays.asList("ffl-home", "home-trinity"),
                "013 East",
                "Trinity Acres and Woods",
                "Near Bellandur gate",
                zone,
                zip,
                null);
    }
}
