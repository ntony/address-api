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
public class CountyResourceProcessor implements ResourceProcessor<Resource<County>>{

    @NonNull
    private final EntityLinks entityLinks;

    @Override
    public Resource<County> process(Resource<County> resource) {
        County county = resource.getContent();
        log.info("Adding link to zones of {}", county.getName());
        Link zonesOfCounty = new Link(getZoneBaseUri() + "/search/findByCounty?county=" + entityLinks.linkToSingleResource(County.class, county.getId()).getHref());

        resource.add(zonesOfCounty.withRel("zones"));
        return resource;
    }

    private String getZoneBaseUri() {
        String templatedBaseUri = entityLinks.linkToCollectionResource(Zone.class).getHref();
        return templatedBaseUri.substring(0, templatedBaseUri.indexOf("{"));
    }
}
