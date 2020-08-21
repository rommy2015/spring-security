package spring.security.web.security.auth;

import org.springframework.security.core.context.SecurityContext;

public interface SecurityContextHolderFacade {

    SecurityContext getContextSecurity();
}
