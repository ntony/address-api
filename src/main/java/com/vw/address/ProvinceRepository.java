package com.vw.address;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author ffazil
 * @since 29/03/16
 */
public interface ProvinceRepository extends PagingAndSortingRepository<Province, String> {
    public Province findByAdmin1Code(Integer admin1Code);
}
