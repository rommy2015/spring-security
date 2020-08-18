package spring.security.service.save.user;

import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.security.dao.domain.Role;
import spring.security.service.dto.user.UserDto;
import spring.security.service.exceptions.ForbiddenException;
import spring.security.service.read.user.UserReadService;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceSaveTest {

    @Autowired
    private UserServiceSave serviceSave;

    @Autowired
    private UserReadService readService;


    @Test
    void saveUser() {

        String userName = "user";

        String password = "user";

        UserDto userByName =
                readService.getDataOfUserByName(userName);

        ImmutableList<Role> roleImmutableList = ImmutableList.of(Role.USER);

        UserDto userDto = UserDto.newBuilder()
                .username(userName)
                .password(password)
                .accountNonExpired(true)
                .authorities(roleImmutableList)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

        try {
            if (userByName.getUsername() != null)
                serviceSave.saveUser(userDto);
        } catch (ForbiddenException e) {

            ForbiddenException bufferException =  e;

            Class<? extends ForbiddenException> aClass = bufferException.getClass();
            Assert.assertEquals(ForbiddenException.class, aClass);
        }
    }
}
