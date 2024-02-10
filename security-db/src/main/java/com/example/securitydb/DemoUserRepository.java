package com.example.securitydb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DemoUserRepository extends JpaRepository<DemoUser,Integer> {

    DemoUser findByUsername(String user);
}
