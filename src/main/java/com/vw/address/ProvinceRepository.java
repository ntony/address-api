package com.vw.address;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author ffazil
 * @since 29/03/16
 */
public interface ProvinceRepository extends PagingAndSortingRepository<Province, String> {
    public Province findByAdmin1Code(Integer admin1Code);
    public Province findByName(@Param("name") String name);
}
