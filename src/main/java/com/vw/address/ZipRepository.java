package com.vw.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

/**
 * @author ffazil
 * @since 29/03/16
 */
@RepositoryRestResource(excerptProjection = ZipExpanded.class)
public interface ZipRepository extends PagingAndSortingRepository<Zip, String> {
    public Set<Zip> findByZone_Name(@Param("name") String name);
    public Zip findByPostalCode(@Param("postalCode") String postalCode);

    public Page<Zip> findByZone(@Param("zone") Zone zone, Pageable pageable);
}
