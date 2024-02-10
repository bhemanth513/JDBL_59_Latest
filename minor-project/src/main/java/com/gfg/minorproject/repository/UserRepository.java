package com.gfg.minorproject.repository;

import com.gfg.minorproject.model.SecuredUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SecuredUser,Integer> {

    SecuredUser findByUsername(String user);
}
