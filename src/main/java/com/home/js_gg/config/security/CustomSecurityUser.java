package com.home.js_gg.config.security;

import com.home.js_gg.entity.Role;
import com.home.js_gg.entity.Users;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomSecurityUser extends User {

    private static final String ROLE_PREFIX = "ROLE_";

    private Users users;

    public CustomSecurityUser(Users users){
        super(users.getUserId(), new BCryptPasswordEncoder().encode(users.getUserPwd()), makeGrantedeAuth(users.getRoles()));
        this.users = users;
    }

    private static List<GrantedAuthority> makeGrantedeAuth(List<Role> roles) {
        List<GrantedAuthority> list = new ArrayList();
        roles.forEach(
                role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName()))
        );
        return list;
    }
}
