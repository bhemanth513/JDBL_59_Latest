package com.example.securitydb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DemoUserService implements UserDetailsService {
    
    @Autowired
    DemoUserRepository demoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return demoRepository.findByUsername(username);
    }
    public DemoUser save(DemoUser demoUser) {
        return demoRepository.save(demoUser);
    }
}
