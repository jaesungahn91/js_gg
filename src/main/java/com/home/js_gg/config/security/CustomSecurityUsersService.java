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

    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String uEmail) throws UsernameNotFoundException {
        Optional<Users> usersEmail = null;//usersRepository.findByUser_id(uEmail);
        Users users = usersEmail.orElseThrow(() -> new UsernameNotFoundException("User not found by email : " + uEmail));

        return new CustomSecurityUser(users);
    }
}
