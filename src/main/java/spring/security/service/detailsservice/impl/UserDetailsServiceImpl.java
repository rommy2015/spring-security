package spring.security.service.detailsservice.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
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

        User user = byUsername.orElseThrow(() ->
                new UsernameNotFoundException("User nof found."));

        UserDetails userDetails = getUserDetails(user);

        return userDetails;
    }


    public Optional<User> findById(@NonNull ObjectId id) {
        return repository.findById(id);
    }

    private UserDetails getUserDetails (User user){

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
