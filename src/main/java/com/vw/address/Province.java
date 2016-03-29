package com.vw.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author ffazil
 * @since 06/03/16
 */
@Data
@Document
@AllArgsConstructor
public class Province extends AbstractEntity {

        private final String name;

        private final Point location;

        private final Double population;

        private final String featureCode;

        private final Integer admin1Code;

        @DBRef
        private final Country country;

        protected Province(){
            this(null, null, null, null, null, null);
        }

}
