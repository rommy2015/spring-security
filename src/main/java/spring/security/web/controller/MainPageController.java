package spring.security.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.security.dao.domain.Role;
import spring.security.dao.domain.User;
import spring.security.service.detailsservice.impl.UserDetailsImpl;
import spring.security.web.security.auth.AuthenticationFacade;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Controller
public class MainPageController {

    private AuthenticationFacade authenticationFacade;


    @Autowired
    public MainPageController(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @RequestMapping("/")
    public String getMainPage(Model model) {

        Authentication authentication = this.authenticationFacade.getAuthentication();

        UserDetailsImpl principalOfUSer = (UserDetailsImpl) authentication.getPrincipal();

        User user = User.newBuilder()
                .id(principalOfUSer.getId())
                .username(principalOfUSer.getUsername())
                .password(principalOfUSer.getPassword())
                .authorities((List<Role>) principalOfUSer.getAuthorities())
                .credentialsNonExpired(principalOfUSer.isCredentialsNonExpired())
                .accountNonExpired(principalOfUSer.isAccountNonExpired())
                .accountNonLocked(principalOfUSer.isAccountNonLocked())
                .enabled(principalOfUSer.isEnabled())
                .build();

        model.addAttribute("username", user.getUsername());

          String collectRoles = user.getAuthorities()
                .stream()
                .map(Role::getAuthority)
                .collect(joining(","));

        model.addAttribute("roles", collectRoles);

        return "index";
    }

}
