package com.vw.address;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author ffazil
 * @since 29/03/16
 */
public interface ZipRepository extends PagingAndSortingRepository<Zip, String> {
}