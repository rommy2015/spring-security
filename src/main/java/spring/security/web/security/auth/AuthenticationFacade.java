package spring.security.web.security.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

public interface AuthenticationFacade {

    Authentication getAuthentication();

}
