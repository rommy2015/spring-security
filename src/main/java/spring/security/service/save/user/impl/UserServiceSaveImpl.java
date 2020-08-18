package spring.security.service.save.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.security.dao.domain.User;
import spring.security.dao.repository.UserRepository;
import spring.security.service.dto.user.UserDto;
import spring.security.service.exceptions.ForbiddenException;
import spring.security.service.mapper.user.UserMapper;
import spring.security.service.save.user.UserServiceSave;

import java.util.Optional;

@Service
public class UserServiceSaveImpl implements UserServiceSave {

    private UserRepository repository;

    private UserMapper mapper;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceSaveImpl(UserRepository repository,
                               UserMapper mapper,
                               PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) throws ForbiddenException {

        User user = this.mapper.toEntity(userDto);

       if (isContainUser(user)) throw new  ForbiddenException("Пользователь с таким именем уже существует");

        String password = user.getPassword();
        String encodePassword = this.passwordEncoder.encode(password);

        user.setPassword(encodePassword);

        this.repository.save(user);

    }

    private boolean isContainUser(User user){

        Optional<User> byUsername = this.repository.findByUsername(user.getUsername());

      return   byUsername.isPresent();

    }

}
