package com.home.js_gg.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomSecurityUsersService customSecurityUsersService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        log.info("security config....");
        http.authorizeRequests()
                /* 비권한 */
                .antMatchers("/summoner/**").permitAll()

                /* 권한 */
                .antMatchers("/").hasAnyRole("BASIC", "MANAGER", "ADMIN")
                .antMatchers("/main").hasAnyRole("BASIC", "MANAGER", "ADMIN");
        http.formLogin();

//        http.csrf().disable().formLogin().loginPage("/login");

//        http.exceptionHandling().accessDeniedPage("/accessDenied");

//        http.logout().logoutUrl("/logout").invalidateHttpSession(true);

//        http.userDetailsService(new CustomSecurityUsersService());

//        http.authorizeRequests()
//                .antMatchers("/member/**").authenticated()
//                .antMatchers("/admin/**").authenticated()
//                .antMatchers("/**").permitAll();
//
//        http.formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/")
//                .permitAll();
//
//        http.logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true);
//
//        http.exceptionHandling()
//                .accessDeniedPage("/denied");

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        log.info("SecurityConfig configureGlobal...");
        auth.userDetailsService(customSecurityUsersService).passwordEncoder(passwordEncoder());
    }

}
