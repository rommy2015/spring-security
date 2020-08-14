package spring.security.web.security;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class SecurityConfigTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final String B_CRYPT_TYPE = "$";

    @Test
    void passwordEncoder() {

        String encodePassword = this.passwordEncoder.encode("user");
        LOGGER.info("password : " + encodePassword);

        boolean isEncodePassword = encodePassword.startsWith(B_CRYPT_TYPE);

        Assert.assertTrue(isEncodePassword);

    }
}
