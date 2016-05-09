package com.vw.address;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author ffazil
 * @since 29/03/16
 */
public interface CountyRepository extends PagingAndSortingRepository<County, String> {
    public County findByFeatureCodeAndAdmin2Code(String featureCode, String admin2Code);
}
