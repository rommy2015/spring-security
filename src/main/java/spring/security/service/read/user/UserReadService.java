package spring.security.service.read.user;

import spring.security.service.dto.user.UserDto;

public interface UserReadService {

    UserDto getDataOfUserByName(String byUserName);
}
