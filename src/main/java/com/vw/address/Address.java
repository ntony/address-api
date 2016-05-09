package com.vw.address;

import lombok.Data;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

/**
 * @author ffazil
 * @since 29/03/16
 */
@Data
@Document
public class Address extends AbstractEntity{

    private final String reference;
    private final Type type;
    private final Set<String> labels = new HashSet<>();

    private final String block;
    private final String premise;
    private final String street;

    @DBRef
    private final Zone zone;
    @DBRef
    private final Zip zip;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private final Point location;

    public Address(String reference, Type type, Collection<String> labels, String block, String premise, String street, Zone zone, Zip zip, Point location){
        this.reference = reference == null ? UUID.randomUUID().toString() : reference;
        this.type = type == null ? Type.Home : type;
        this.labels.addAll(labels == null ? Collections.emptyList() : labels);

        this.block = block;
        this.premise = premise;
        this.street = street;

        this.zone = zone;
        this.zip = zip;

        this.location = location == null ? new Point(0.0, 0.0) : location;

    }

    public Address(String block, String building, String street, Zone zone, Zip zip){
        this(null, null, Arrays.asList(new String[0]), block, building, street, zone, zip, null);
    }

    public Address(){
        this(null, null, null, null, null);
    }
}
