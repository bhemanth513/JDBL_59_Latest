package com.gfg.minorproject.service;

import com.gfg.minorproject.dto.CreateAdminRequest;
import com.gfg.minorproject.model.Admin;
import com.gfg.minorproject.model.SecuredUser;
import com.gfg.minorproject.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${admin.authorities}")
    String authorities;

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    SecuredUserService securedUserService;

    public Admin addAdmin(CreateAdminRequest createAdminRequest) {
        Admin admin = createAdminRequest.to();
        //adding username to the student table
        SecuredUser securedUser = admin.getSecuredUser();
        //encode password before storing
        securedUser.setPassword(passwordEncoder.encode(securedUser.getPassword()));
        securedUser.setAuthorities(authorities);

        //save user to userdetail table to create userId
        SecuredUser securedUser1 = securedUserService.save(securedUser);

        //then store user in the student table
        admin.setSecuredUser(securedUser1);
        return adminRepository.save(admin);
    }

}
