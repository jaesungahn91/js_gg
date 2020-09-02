package com.home.js_gg.config.security;

import com.home.js_gg.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CustomSecurityUsersService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String uEmail) throws UsernameNotFoundException {
        /*if(!uEmail.equals("sung431@naver.com")){
            new UsernameNotFoundException("User not found by email : " + uEmail);
        }*/
        Member member = new Member();
        member.setUid("sung431");
        member.setUname("안재성");
        String pwd = "1111";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setUpw(passwordEncoder.encode(pwd));

//        Optional<User> usersEmail = userRepository.findByEmail(email);
//        User user = usersEmail.orElseThrow(() -> new UsernameNotFoundException("User not found by email : " + email));

        return new CustomSecurityUser(member);
    }
}
