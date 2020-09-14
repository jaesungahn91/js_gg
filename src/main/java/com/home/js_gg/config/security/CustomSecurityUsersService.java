package com.home.js_gg.config.security;

import com.home.js_gg.entity.Users;
import com.home.js_gg.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomSecurityUsersService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Users> userOp = usersRepository.findByUserId(userId);
        Users users = userOp.orElseThrow(() -> new UsernameNotFoundException("User not found by email : " + userId));

        return new CustomSecurityUser(users);
    }
}
