package com.vw.address;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

/**
 * @author ffazil
 * @since 10/05/16
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ZoneResourceProcessor implements ResourceProcessor<Resource<Zone>>{

    @NonNull
    private final EntityLinks entityLinks;


    @Override
    public Resource<Zone> process(Resource<Zone> resource) {
        Zone zone = resource.getContent();
        log.info("Adding link to zips of {}", zone.getName());
        Link zipsOfZone = new Link(getZipBaseUri() + "/search/findByZone?zone=" + entityLinks.linkToSingleResource(Zone.class, zone.getId()).getHref());

        resource.add(zipsOfZone.withRel("zips"));
        return resource;
    }

    private String getZipBaseUri() {
        String templatedBaseUri = entityLinks.linkToCollectionResource(Zip.class).getHref();
        return templatedBaseUri.substring(0, templatedBaseUri.indexOf("{"));
    }
}
