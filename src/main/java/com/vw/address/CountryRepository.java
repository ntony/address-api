package com.vw.address;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author ffazil
 * @since 29/03/16
 */
public interface CountryRepository extends PagingAndSortingRepository<Country, String> {
}
