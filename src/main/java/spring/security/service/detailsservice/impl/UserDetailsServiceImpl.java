package spring.security.service.detailsservice.impl;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.security.dao.domain.Role;
import spring.security.dao.domain.User;
import spring.security.service.dto.user.UserDto;
import spring.security.service.mapper.user.UserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private UserMapper mapper;

    private UserDetails userDetails;


    @Autowired
    public UserDetailsServiceImpl(UserMapper mapper, UserDetails userDetails) {
        this.mapper = mapper;
        this.userDetails = userDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return getDataOfUsers();
    }


    private UserDetails getDataOfUsers(){

        User userData = createUserData();
        UserDto userDto = mapper.toDto(userData);

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

    private User createUserData(){

        ImmutableList<Role> roleImmutableList = ImmutableList.of(Role.USER);

        String userName = "user";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String password = passwordEncoder.encode("user");

      return   User.newBuilder()
                .username(userName)
                .password(password)
                .authorities(roleImmutableList)
                .accountNonExpired(true)/*период действия учетной записи - не истек*/
                .accountNonLocked(true)/*учетная запись не заблокирована*/
                .credentialsNonExpired(true)/*период действия */
                .enabled(true)
                .build();
    }

}
