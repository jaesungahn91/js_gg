package com.home.js_gg.config.security;

import com.home.js_gg.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomSecurityUsersService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String uEmail) throws UsernameNotFoundException {
        /*if(!uEmail.equals("sung431@naver.com")){
            new UsernameNotFoundException("User not found by email : " + uEmail);
        }*/
        Users users = new Users();
        users.setUser_id("sung431");
        users.setUser_name("안재성");
        String pwd = "1111";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        users.setUser_pwd(passwordEncoder.encode(pwd));

//        Optional<User> usersEmail = userRepository.findByEmail(email);
//        User user = usersEmail.orElseThrow(() -> new UsernameNotFoundException("User not found by email : " + email));

        return new CustomSecurityUser(users);
    }
}
