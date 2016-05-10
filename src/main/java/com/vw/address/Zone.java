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
public class Zone extends AbstractEntity {

    private final String name;

    private final Double population;

    private final Point location;

    @DBRef
    private final County county;

    protected Zone(){
        this(null, null, null, null);
    }
}
