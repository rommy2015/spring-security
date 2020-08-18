package spring.security.service.update.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.dao.domain.User;
import spring.security.dao.repository.CustomUpdateRepository;
import spring.security.dao.repository.UserRepository;
import spring.security.service.dto.user.UserDto;
import spring.security.service.mapper.user.UserMapper;
import spring.security.service.update.user.UserUpdateService;

@Service
public class UserUpdateServiceImpl implements UserUpdateService {

    private CustomUpdateRepository repository;

    private UserRepository userRepository;

    private UserMapper mapper;


    @Autowired
    public UserUpdateServiceImpl(CustomUpdateRepository repository,
                                 UserRepository userRepository,
                                 UserMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Override
    public long updateAccountLocked(UserDto userDto) {

        boolean accountNonLocked = userDto.isAccountNonLocked();

        /*поле по которому производиться поиск записи для обновления*/
        String nameField = "username";
        /*значение поле по которому производиться поиск записи для обновления*/
        String nameFieldValue = userDto.getUsername();

        /*имя поля для обновления*/
        String nameFieldForUpdate = "accountNonLocked";

        long accountLocked =
                repository.updateAccountLocked(nameField, nameFieldValue,
                        nameFieldForUpdate, accountNonLocked);

        return accountLocked;
    }

    @Override
    public void updateUser(UserDto userDto) {

        User user = mapper.toEntity(userDto);

        /**
         * это вызывает ошибку, если запись с такими данными уже есть.
         */
        userRepository.insert(user);

    }
}
