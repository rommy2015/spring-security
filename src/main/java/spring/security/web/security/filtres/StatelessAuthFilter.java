package spring.security.web.security.filtres;


import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import spring.security.service.token.TokenAuthService;
import spring.security.web.security.auth.SecurityContextHolderFacade;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * В этом классе нельзя выполнить внедрение компонентов, так как
 * этот класс не помечен как компонет, который нужно создать во
 * время запуска приложения.
 */
public class StatelessAuthFilter extends GenericFilterBean {

    private SecurityContextHolderFacade securityContextHolderFacade;

    private TokenAuthService tokenAuthService;

    public StatelessAuthFilter(@NonNull TokenAuthService tokenAuthService) {
        this.tokenAuthService = tokenAuthService;
    }


    /**
     * tokenAuthService.getAuthentication((HttpServletRequest) servletRequest) - достается информация
     * об аутентификации пользователя из ServletRequest, то есть из запроса, который обрабатывается.
     *  Если данных нет, тогда возвращается null.
     * securityContext.setAuthentication(authentication); - здесь устанавливаем данные об аутентификации
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        SecurityContext securityContext = SecurityContextHolder.getContext();


        Authentication authentication =
                tokenAuthService.getAuthentication((HttpServletRequest) servletRequest)
                        .orElse(null);

        securityContext.setAuthentication(authentication);


        filterChain.doFilter(servletRequest, servletResponse);
    }
}


/*
/*    private final TokenAuthService tokenAuthService;

    public StatelessAuthFilter(@NonNull TokenAuthService tokenAuthService) {
        this.tokenAuthService = tokenAuthService;
    }
     SecurityContextHolder.getContext().setAuthentication(
         tokenAuthService.getAuthentication((HttpServletRequest) servletRequest).orElse(null));

    */


