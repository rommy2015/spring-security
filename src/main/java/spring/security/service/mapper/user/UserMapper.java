package spring.security.service.mapper.user;

import org.mapstruct.Mapper;
import spring.security.dao.domain.User;
import spring.security.service.dto.user.UserDto;
import spring.security.service.mapper.CommonMapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends CommonMapper<UserDto, User> {
}
