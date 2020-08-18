package spring.security.service.update.user;

import spring.security.service.dto.user.UserDto;

public interface UserUpdateService {

    long updateAccountLocked(UserDto userDto);

    void updateUser(UserDto userDto);
}
