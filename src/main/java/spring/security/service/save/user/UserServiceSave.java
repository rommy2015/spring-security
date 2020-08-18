package spring.security.service.save.user;

import spring.security.service.dto.user.UserDto;
import spring.security.service.exceptions.ForbiddenException;

public interface UserServiceSave {

    void saveUser(UserDto userDto) throws ForbiddenException;

}
