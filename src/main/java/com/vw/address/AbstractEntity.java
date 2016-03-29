package com.vw.address;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.hateoas.Identifiable;

/**
 * @author ffazil
 * @since 28/03/16
 */
@Data
public abstract class AbstractEntity implements Identifiable<String>{
    @Id
    private final String id;

    protected AbstractEntity(){
        this.id = null;
    }

}
