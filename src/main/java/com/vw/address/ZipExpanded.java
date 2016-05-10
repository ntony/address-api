package com.vw.address;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author ffazil
 * @since 10/05/16
 */
@Projection(name = "zipExpanded", types = {Zip.class})
interface ZipExpanded {
    String getPostalCode();
    Point getLocation();
    @Value("#{target.getZone().getName()}")
    String getPlace();
}
