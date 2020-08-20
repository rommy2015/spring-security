package spring.security.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import spring.security.dao.domain.User;
import spring.security.service.detailsservice.impl.UserDetailsImpl;
import spring.security.web.security.dto.UserAuthentication;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
public class AdminAuthFilter extends GenericFilterBean {


    private UserDetailsService userService;

    @Autowired
    public AdminAuthFilter(@Qualifier("userDetailsServiceImpl") UserDetailsService userService) {
        this.userService = userService;
    }

    private UserDetails admin;

    @PostConstruct
    public void init() {

       this.admin = userService.loadUserByUsername("admin");

    }



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(admin));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
