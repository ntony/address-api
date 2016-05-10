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
public class ProvinceResourceProcessor implements ResourceProcessor<Resource<Province>>{

    @NonNull
    private final EntityLinks entityLinks;

    @Override
    public Resource<Province> process(Resource<Province> resource) {
        Province province = resource.getContent();
        log.info("Adding link to counties of {}", province.getName());
        Link countiesOfProvince = new Link(getCountyBaseUri() + "/search/findByProvince?province=" + entityLinks.linkToSingleResource(Province.class, province.getId()).getHref());

        resource.add(countiesOfProvince.withRel("counties"));
        return resource;
    }

    private String getCountyBaseUri() {
        String templatedBaseUri = entityLinks.linkToCollectionResource(County.class).getHref();
        return templatedBaseUri.substring(0, templatedBaseUri.indexOf("{"));
    }
}
