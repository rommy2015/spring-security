package spring.security.service.detailsservice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.security.dao.domain.User;
import spring.security.dao.repository.UserRepository;
import spring.security.service.dto.user.UserDto;
import spring.security.service.mapper.user.UserMapper;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserMapper mapper;

    private UserRepository repository;


    @Autowired
    public UserDetailsServiceImpl(UserMapper mapper,
                                  UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> byUsername = repository.findByUsername(username);
        UserDetails userDetails = getUserDetails(byUsername);

        return userDetails;
    }

    private UserDetails getUserDetails (Optional<User> byUsername){

        User user = byUsername.orElse(new User());
        UserDto userDto = mapper.toDto(user);

        return UserDetailsImpl.newBuilder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .authorities(userDto.getAuthorities())
                .accountNonExpired(userDto.isAccountNonExpired())
                .accountNonLocked(userDto.isAccountNonLocked())
                .credentialsNonExpired(userDto.isCredentialsNonExpired())
                .enabled(userDto.isEnabled())
                .build();
    }
}
