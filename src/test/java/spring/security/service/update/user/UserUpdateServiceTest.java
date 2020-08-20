package spring.security.service.update.user;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.security.dao.domain.Role;
import spring.security.service.dto.user.UserDto;
import spring.security.service.read.user.UserReadService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserUpdateServiceTest {

    @Autowired
    private UserReadService readService;

    @Autowired
    private UserUpdateService userUpdateService;


    @Test
    void updateDocumentInCollection() {

        UserDto user = readService.getDataOfUserByName("user");

        user.setAccountNonLocked(true);

        long resultOfAccountLocked = 0L;

       if(user.getUsername() != null)
         resultOfAccountLocked = userUpdateService.updateAccountLocked(user);

       if(resultOfAccountLocked == 0L) System.out.println("нет нужды обновлять данное поле.");
       // Assert.assertNotEquals(0L, resultOfAccountLocked);
    }

    @Test
    void updateUser() {

        UserDto user = readService.getDataOfUserByName("user");

        user.setEnabled(true);
        user.setAccountNonLocked(true);

        List<Role> authorities = user.getAuthorities();
        //authorities.add(Role.ADMIN);
       // user.setAuthorities(authorities);


        if(user.getUsername() != null)
            userUpdateService.updateUser(user);

    }
}
