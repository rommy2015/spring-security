package spring.security.web.security.auth.impl;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import spring.security.web.security.auth.SecurityContextHolderFacade;

@Service
public class SecurityContextHolderFacadeImpl implements SecurityContextHolderFacade {


    @Override
    public SecurityContext getContextSecurity() {

        SecurityContext securityContext = SecurityContextHolder.getContext();

        return securityContext;
    }
}
