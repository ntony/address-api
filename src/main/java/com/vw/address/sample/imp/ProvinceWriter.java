package com.vw.address.sample.imp;

import com.vw.address.Province;
import com.vw.address.ProvinceRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author nidhintony
 * @since 13/03/16
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProvinceWriter implements ItemWriter<Province> {
    @NonNull
    private final ProvinceRepository provinceRepository;

    @Override
    public void write(List<? extends Province> items) throws Exception {
        provinceRepository.save(items);
    }

}
