package com.vw.address;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * @author ffazil
 * @since 29/03/16
 */
public interface ZipRepository extends PagingAndSortingRepository<Zip, String> {
    public Set<Zip> findByPlaceName(@Param("placeName") String placeName);
}
