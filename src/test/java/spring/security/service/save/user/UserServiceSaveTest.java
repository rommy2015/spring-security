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
    void saveUserUser() {

        String userNameUser = "user";
        String passwordUser = "user";

        UserDto userByNameForUser =
                readService.getDataOfUserByName(userNameUser);

        ImmutableList<Role> roleImmutableListForUser = ImmutableList.of(Role.USER);

        UserDto userDtoUser = prepareDataOfUser(roleImmutableListForUser, userNameUser, passwordUser);

        try {
            if (userByNameForUser.getUsername() == null)
                serviceSave.saveUser(userDtoUser);
        } catch (ForbiddenException e) {

            ForbiddenException bufferException =  e;

            Class<? extends ForbiddenException> aClass = bufferException.getClass();
            Assert.assertEquals(ForbiddenException.class, aClass);
        }

    }

    @Test
    void saveUserAdmin(){

        String userNameAdmin = "admin";
        String passwordAdmin = "admin";

        UserDto userByNameForAdmin =
                readService.getDataOfUserByName(userNameAdmin);

        ImmutableList<Role> roleImmutableListForAdmin = ImmutableList.of(Role.ADMIN, Role.POWER_USER);

        UserDto userDtoAdmin = prepareDataOfUser(roleImmutableListForAdmin, userNameAdmin, passwordAdmin);

        try {
            if (userByNameForAdmin.getUsername() == null)
                serviceSave.saveUser(userDtoAdmin);
        } catch (ForbiddenException e) {

            ForbiddenException bufferException =  e;

            Class<? extends ForbiddenException> aClass = bufferException.getClass();
            Assert.assertEquals(ForbiddenException.class, aClass);
        }
    }

    @Test
    void saveUserPowerUser(){

        String userNamePowerUser = "powerUser";
        String passwordPowerUser = "powerUser";

        UserDto userByNameForPowerUser =
                readService.getDataOfUserByName(userNamePowerUser);

        ImmutableList<Role> roleImmutableListForPowerUser = ImmutableList.of(Role.POWER_USER);

        UserDto userDtoPowerUser = prepareDataOfUser(roleImmutableListForPowerUser, userNamePowerUser, passwordPowerUser);

        try {
            if (userByNameForPowerUser.getUsername() == null)
                serviceSave.saveUser(userDtoPowerUser);
        } catch (ForbiddenException e) {

            ForbiddenException bufferException =  e;

            Class<? extends ForbiddenException> aClass = bufferException.getClass();
            Assert.assertEquals(ForbiddenException.class, aClass);
        }
    }




    private UserDto prepareDataOfUser( ImmutableList<Role> roleImmutableList, String userName, String password){

        UserDto userDto = UserDto.newBuilder()
                .username(userName)
                .password(password)
                .accountNonExpired(true)
                .authorities(roleImmutableList)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();

        return userDto;
    }
}
