package com.vw.address.sample;

import lombok.Data;

/**
 * @author ffazil
 * @since 06/03/16
 */
@Data
public class ProvincesInfo{
    private String totalResultsCount;
    private ProvinceInfo[] geonames;
}
