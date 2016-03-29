package com.vw.address;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Base class to implement transactional integration tests using the root application configuration.
 *
 * @author ffazil
 * @since 04/03/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AddressApiApplication.class)
@Transactional
public abstract class AbstractIntegrationTest {
}
