package spring.security.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests() /*все запросы должны проходить авторизацию*/
                /*запросы, на адреса, совпдающие с представленным шаблоном, не будут авторизовываться*/
                .antMatchers("/css/**", "/js/**").permitAll()
                /*Все остальные запросы, должны быть от пользователя, который прошел аутентификацию*/
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/loginCustom").permitAll() /*страница для ввода данных учетной записи, открыта для всех*/
                .and()
                .logout().permitAll();/*страница для выхода из сеанса, открыта для всех*/

    }

    /**
     * `AuthenticationManagerBuilder` позволяет легко построить в памяти аутентификацию,
     * аутентификацию `LDAP`, аутентификацию основанную на `JDBC`, добавляя `UserDetailsService`,
     * добавляя `AuthenticationProvider`.
     * auth.userDetailsService(userDetailsService) - определили пользовательские правила,
     * проверки аутентификации и авторизации
     *
     * @param auth
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


