package com.vw.address.sample;

import lombok.Data;

import java.math.BigInteger;

/**
 * @author ffazil
 * @since 05/03/16
 */
@Data
public class SampleData {
    private Integer geonameId;
    private String name;
    private String asciiName;
    private String alternatenames;
    private String latitude;
    private String longitude;
    private String featureClass;
    private String featureCode;
    private String alpha2Code;
    private String cc2;
    private Integer admin1Code;
    private String admin2Code;
    private String admin3Code;
    private BigInteger admin4Code;
    private BigInteger population;
    private Integer elevation;
    private Integer dem;
    private String timezone;
    private String modificationDate;


}
