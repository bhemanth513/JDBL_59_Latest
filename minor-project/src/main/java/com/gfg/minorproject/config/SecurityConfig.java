package com.gfg.minorproject.config;

import com.gfg.minorproject.service.SecuredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecuredUserService securedUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securedUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()
                .csrf().disable()//disable only for testing purpose,should not be used in prod env
                .authorizeHttpRequests()
                //student controller
                .antMatchers(HttpMethod.POST,"/student/**").permitAll() //public api
                .antMatchers("/student/details/**").hasAuthority("get-student-profile")
                .antMatchers(HttpMethod.GET,"/student/**").hasAuthority("get-student-details")
                .antMatchers("/student/**").hasAuthority("update-student-account") //PUT,delete,patch api's
                //book controller
                .antMatchers(HttpMethod.GET,"/book/**").hasAuthority("get-book-details")
                .antMatchers("/book/**").hasAuthority("update-book")
                //transaction controller
                .antMatchers("/transaction").hasAuthority("book-transaction")
                //admin controller
                .antMatchers(HttpMethod.POST,"/admin/**").hasAuthority("add-admin")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
