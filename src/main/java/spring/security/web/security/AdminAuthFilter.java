package spring.security.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
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

    private UserDetails userDetails;

    @PostConstruct
    public void init() {

        this.userDetails = userService.loadUserByUsername("user");

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        SecurityContext securityContext = SecurityContextHolder.getContext();

        UserAuthentication userAuthentication = new UserAuthentication(userDetails);

        securityContext.setAuthentication(userAuthentication);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
