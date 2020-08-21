package spring.security.web.security.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import spring.security.web.security.auth.AuthenticationFacade;
import spring.security.web.security.auth.SecurityContextHolderFacade;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {


    private SecurityContextHolderFacade securityContextHolderFacade;


    @Autowired
    public AuthenticationFacadeImpl(SecurityContextHolderFacade securityContextHolderFacade) {
        this.securityContextHolderFacade = securityContextHolderFacade;
    }

    @Override
    public Authentication getAuthentication() {

        SecurityContext contextSecurity = securityContextHolderFacade.getContextSecurity();

        Authentication authentication = contextSecurity.getAuthentication();

        return authentication;
    }
}
