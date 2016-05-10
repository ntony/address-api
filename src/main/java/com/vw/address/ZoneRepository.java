package com.vw.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * @author ffazil
 * @since 29/03/16
 */
public interface ZoneRepository extends PagingAndSortingRepository<Zone, String> {
    public Zone findByName(@Param("name") String name);
    public Set<Zone> findByNameContaining(@Param("key") String key);

    public Page<Zone> findByCounty(@Param("county") County county, Pageable pageable);
}
