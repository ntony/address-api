package com.vw.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Currency;

/**
 * @author ffazil
 * @since 01/03/16
 */
@Data
@Document
@AllArgsConstructor
public class Country extends AbstractEntity{

    private final String name;

    private final Alpha2Code alpha2Code;

    private final Alpha3Code alpha3Code;

    //private final Currency currency;

    private final Continent continent;

    private final Double area;

    private final Double population;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private final Point location;

    protected Country(){
        this(null, null, null, null, null, null, null);
    }
}
