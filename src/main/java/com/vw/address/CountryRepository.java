package com.vw.address;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author ffazil
 * @since 29/03/16
 */
public interface CountryRepository extends PagingAndSortingRepository<Country, String> {
    public Country findByAlpha2Code(Alpha2Code alpha2Code);
}
