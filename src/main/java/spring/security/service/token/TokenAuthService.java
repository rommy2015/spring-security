package spring.security.service.token;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import spring.security.service.read.user.UserReadService;
import spring.security.web.security.dto.UserAuthentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class TokenAuthService {


    private static final String AUTH_HEADER_NAME = "X-Auth-Token";

    private UserDetailsService userService;

    private TokenHandler tokenHandler;

    private UserReadService userReadService;

    @Autowired
    public TokenAuthService(@Qualifier("userDetailsServiceImpl") UserDetailsService userService,
                            TokenHandler tokenHandler,
                            UserReadService userReadService) {
        this.userService = userService;
        this.tokenHandler = tokenHandler;
        this.userReadService = userReadService;
    }


    public Optional<Authentication> getAuthentication(@NonNull HttpServletRequest request) {


        String requestHeader = request.getHeader(AUTH_HEADER_NAME);

        Optional<Authentication> userAuthentication = Optional.ofNullable(requestHeader)
                .flatMap(tokenHandler::extractUserId)
                .flatMap(userReadService::getUserById)
                .map(UserAuthentication::new);


        return userAuthentication;
    }
}
