package com.example.securitydb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.beans.Encoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DemoUserService demoUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(demoUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
        //.csrf().disable()//disable only for testing purpose,should not be used in prod env
                .authorizeHttpRequests()
                .antMatchers("/faculty/attendance/**").hasAuthority("check_attendance")
                .antMatchers("/faculty/signup/**").permitAll()
                .antMatchers("/faculty/**").hasAuthority("login_faculty")
                .antMatchers("/student/signup/**").permitAll()
                .antMatchers("/student/**").hasAuthority("login_student")
                .antMatchers("/library/**").hasAnyAuthority("access_library")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
