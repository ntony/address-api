package com.vw.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ffazil
 * @since 01/03/16
 */
@Data
@Document
@AllArgsConstructor
public class County extends AbstractEntity {

    private final String name;

    private final Double population;

    private final Point location;

    private final String featureCode;

    private final Integer admin1Code;

    private final String admin2Code;

    @DBRef
    private final Province province;

    protected County(){
        this(null, null, null, null, null, null, null);
    }
}
