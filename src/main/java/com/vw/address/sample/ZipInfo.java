package com.vw.address.sample;

import lombok.Data;

/**
 * @author ffazil
 * @since 07/03/16
 */
@Data
public class ZipInfo {

    /*private String adminName2;

    private String adminName3;

    private String adminCode1;

    private String distance;

    private String postalCode;

    private String countryCode;

    private String placeName;

    private String lng;

    private String lat;
    @JsonProperty("ISO3166-2")
    private String ISO3166_2;

    private String adminName1;*/

    private String countryCode;

    private String postalCode;

    private String placeName;

    private String adminName1;

    private Integer adminCode1;

    private String adminName2;

    private Integer adminCode2;

    private String adminName3;

    private Integer adminCode3;

    private Integer latitude;

    private Integer longitude;

    private Integer accuracy;

}
