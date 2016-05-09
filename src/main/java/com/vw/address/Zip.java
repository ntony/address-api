package com.vw.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
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

    @DBRef
    private final Country country;

    private final String placeName;

    @DBRef
    private final Province province;

    @DBRef
    private final County county;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private final Point location;

    private final Integer accuracy;


    protected Zip(){
        this(null, null, null, null, null, null, null);
    }

}
