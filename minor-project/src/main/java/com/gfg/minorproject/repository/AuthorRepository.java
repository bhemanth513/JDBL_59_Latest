package com.gfg.minorproject.repository;

import com.gfg.minorproject.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {

    //find by email
    public Author findByEmail(String email);
}
