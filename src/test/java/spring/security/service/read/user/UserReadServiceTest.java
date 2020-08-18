package spring.security.service.read.user;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.security.service.dto.user.UserDto;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserReadServiceTest {

    @Autowired
    private UserReadService service;

    @Test
    void getDataOfUserByName() {

        String userName = "user";

        UserDto dataOfUserByName =
                service.getDataOfUserByName(userName);

        if(dataOfUserByName.getUsername() == null)
            System.out.println("Пользователя с именем : " + userName + " - не существует." );
    }

}
