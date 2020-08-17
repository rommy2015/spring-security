package spring.security.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);


    @Override
    protected void configure(HttpSecurity http) throws Exception {

             http
                .authorizeRequests() /*все запросы должны проходить авторизацию*/
                      /*запросы, на адреса, совпдающие с представленным шаблоном, не будут авторизовываться*/
                .antMatchers("/css/**", "/js/**").permitAll()
                /*Все остальные запросы, должны быть от пользователя, который прошел аутентификацию*/
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll() /*страница для ввода данных учетной записи, открыта для всех*/
                .and()
                .logout().permitAll();/*страница для выхода из сеанса, открыта для всех*/

    }

    /**
     * `AuthenticationManagerBuilder` позволяет легко построить в памяти аутентификацию,
     * аутентификацию `LDAP`, аутентификацию основанную на `JDBC`, добавляя `UserDetailsService`,
     * добавляя `AuthenticationProvider`.
     * inMemoryAuthentication() - Метод орагнизовывает аутентификацию пользователей, данные
     * о которой храняться в памяти приложения
     *
     * @param auth
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {

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


