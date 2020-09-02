package com.home.js_gg.config.security;

import com.home.js_gg.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomSecurityUser extends User {

    private static final String ROLE_PREFIX = "ROLE_";

    private Member member;

//    public CustomSecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//    }
//
//    public CustomSecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//    }
    public CustomSecurityUser(Member member){
        super(member.getUid(), member.getUpw(), makeGrantedeAuth());
        this.member = member;
    }

    private static List<GrantedAuthority> makeGrantedeAuth() {
        List<GrantedAuthority> list = new ArrayList();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + "ADMIN"));
        return list;
    }
}
