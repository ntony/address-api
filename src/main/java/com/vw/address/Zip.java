package com.vw.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ffazil
 * @since 01/03/16
 */

@Data
@Document
@AllArgsConstructor
public class Zip extends AbstractEntity{


    private final String postalCode;

    private final String countryCode;

    private final String placeName;

    private final String adminName1;

    private final Integer adminCode1;

    private final String adminName2;

    private final String adminCode2;

    private final String adminName3;

    private final Integer adminCode3;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private final Point location;

    private final Integer accuracy;


    protected Zip(){
        this(null, null, null, null, null, null, null, null, null, null, null);
    }

}
