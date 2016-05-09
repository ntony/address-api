package com.vw.address.sample;

import lombok.Data;

/**
 * @author nidhintony
 * @since 20/03/16
 */
@Data
public class SampleZipData {
    private String countryCode;
    private String postalCode;
    private String placeName;
    private String adminName1;
    private Integer adminCode1;
    private String adminName2;
    private String adminCode2;
    private String adminName3;
    private Integer adminCode3;
    private String latitude;
    private String longitude;
    private Integer accuracy;
}
