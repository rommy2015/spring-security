package spring.security.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.security.dao.domain.Role;
import spring.security.dao.domain.User;
import spring.security.web.security.auth.AuthenticationFacade;

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

        model.addAttribute("username", "from model username");
        model.addAttribute("roles", "from model roles");

        return "index";

       // SecurityContext context = SecurityContextHolder.getContext();

     //   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

/*        Authentication authentication = this.authenticationFacade.getAuthentication();

        User user = (User) authentication.getPrincipal();

        model.addAttribute("username", user.getUsername());

        String collectRoles = user.getAuthorities()
                .stream()
                .map(Role::getAuthority)
                .collect(joining(","));

        model.addAttribute("roles", collectRoles);*/

    }

}
