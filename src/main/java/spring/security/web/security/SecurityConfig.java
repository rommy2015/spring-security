package spring.security.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);


    /**
     * `AuthenticationManagerBuilder` позволяет легко построить в памяти аутентификацию,
     * аутентификацию `LDAP`, аутентификацию основанную на `JDBC`, добавляя `UserDetailsService`,
     * добавляя `AuthenticationProvider`.
     * inMemoryAuthentication() - Метод орагнизовывает аутентификацию пользователей, данные
     * о которой храняться в памяти приложения
     * @param auth
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)  {

        String password = passwordEncoder().encode("user");

        LOGGER.info("password : " + password);

        try {
            auth.inMemoryAuthentication()
                    .withUser("user")
                    .password(password)
                    .roles("USER");
        } catch (Exception e) {

            LOGGER.error("Ошибка создания настройки менеждера авторизации в памяти : " + e);

        }
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


