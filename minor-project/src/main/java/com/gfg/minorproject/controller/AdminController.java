package com.gfg.minorproject.controller;

import com.gfg.minorproject.dto.CreateAdminRequest;
import com.gfg.minorproject.model.Admin;
import com.gfg.minorproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/admin")
    public Admin addAdmin(@RequestBody CreateAdminRequest createAdminRequest){
        return adminService.addAdmin(createAdminRequest);
    }
}
