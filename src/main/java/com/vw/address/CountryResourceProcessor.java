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
public class CountryResourceProcessor implements ResourceProcessor<Resource<Country>>{

    @NonNull
    private final EntityLinks entityLinks;

    @Override
    public Resource<Country> process(Resource<Country> resource) {
        Country country = resource.getContent();
        log.info("Adding link to provinces of {}", country.getName());
        Link provincesOfCountry = new Link(getProvinceBaseUri() + "/search/findByCountry?country=" + entityLinks.linkToSingleResource(Country.class, country.getId()).getHref());

        resource.add(provincesOfCountry.withRel("provinces"));
        return resource;
    }

    private String getProvinceBaseUri() {
        String templatedBaseUri = entityLinks.linkToCollectionResource(Province.class).getHref();
        return templatedBaseUri.substring(0, templatedBaseUri.indexOf("{"));
    }
}
