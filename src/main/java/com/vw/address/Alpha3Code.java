package com.vw.address;

import lombok.Data;

/**
 * @author ffazil
 * @since 07/03/16
 */
@Data
public class Alpha3Code {

    private final String alpha3Code;

    public Alpha3Code(String code){
        this.alpha3Code = code;
    }

    protected Alpha3Code(){
        this(null);
    }
}
