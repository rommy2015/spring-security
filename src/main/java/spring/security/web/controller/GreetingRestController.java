package spring.security.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class GreetingRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingRestController.class);

    @GetMapping("greeting")
    public String getGreeting(){
        LOGGER.info("The endpoint - `getGreeting`");

        return "Hello everyone.";
    }

}
