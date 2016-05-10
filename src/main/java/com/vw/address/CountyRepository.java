package com.vw.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author ffazil
 * @since 29/03/16
 */
public interface CountyRepository extends PagingAndSortingRepository<County, String> {
    public County findByFeatureCodeAndAdmin2Code(String featureCode, String admin2Code);
    public County findByName(@Param("name") String name);
    public County findByNameContaining(@Param("key") String key);

    public Page<County> findByProvince(@Param("province") Province province, Pageable pageable);
}
