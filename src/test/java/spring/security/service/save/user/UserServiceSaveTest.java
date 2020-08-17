package spring.security.service.save.user;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.security.service.dto.user.UserDto;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceSaveTest {

    @Autowired
    private UserServiceSave serviceSave;

    @Test
    void saveUser() {

        UserDto userDto = UserDto.newBuilder()
                .username("user")
                .password("user").build();

        serviceSave.saveUser(userDto);
    }
}
