package com.example.springsecurity.demo;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
//
    //Authentication related stuff
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Hemanth")
                .password("$2a$10$wNiSKlyArxiCtp5e.cIuAu.F50ebV9gFlgT./NmxAcuMr2IZg8zxC")
                .authorities("employee")
                .and()
                .withUser("waseem")
                .password("$2a$10$dlWc666OZygCl5qB5/ANousjVnMYlpqC/2UhBpMR2BAzT4w8taHfa")
                .authorities("manager");
    }

    //Authorization stuff

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/employee/**").hasAuthority("employee")
                    .antMatchers("/manager/**").hasAuthority("manager")
                    .antMatchers("/library/**").hasAnyAuthority("employee","manager")
                    .antMatchers("/**").permitAll()
                    .and()
                    .formLogin();
    }

//    @Bean
//    public PasswordEncoder encoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
