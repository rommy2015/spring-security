package spring.security.service.read.user.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import spring.security.dao.domain.User;
import spring.security.dao.repository.UserRepository;
import spring.security.service.detailsservice.impl.UserDetailsImpl;
import spring.security.service.dto.user.UserDto;
import spring.security.service.mapper.user.UserMapper;
import spring.security.service.read.user.UserReadService;

import java.util.Optional;

@Service
public class UserReadServiceImpl implements UserReadService {

    private UserRepository repository;

    private UserMapper mapper;

    @Autowired
    public UserReadServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDto getDataOfUserByName(String byUserName) {

        Optional<User> username = repository.findByUsername(byUserName);
        User user = username.orElse(new User());

        UserDto userDto = mapper.toDto(user);

        return userDto;
    }

    @Override
    public Optional<UserDetails> getUserById(ObjectId id) {

        Optional<User> username = repository.findById(id);

        UserDetails userDetails = (UserDetails) username.orElse(null);

        return Optional.of(userDetails);
    }


}
