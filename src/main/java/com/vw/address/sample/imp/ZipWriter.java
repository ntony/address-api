package com.vw.address.sample.imp;

import com.vw.address.Zip;
import com.vw.address.ZipRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author nidhintony
 * @since 20/03/16
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ZipWriter implements ItemWriter<Zip> {

    @NonNull
    private final ZipRepository zipRepository;

    @Override
    public void write(List<? extends Zip> items) throws Exception {
        zipRepository.save(items);
    }
}
