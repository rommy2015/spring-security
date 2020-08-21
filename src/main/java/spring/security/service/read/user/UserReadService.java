package spring.security.service.read.user;

import org.bson.types.ObjectId;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import spring.security.service.dto.user.UserDto;

import java.util.Optional;

public interface UserReadService {

    UserDto getDataOfUserByName(String byUserName);

    Optional<UserDetails> getUserById(@NonNull ObjectId id);
}
