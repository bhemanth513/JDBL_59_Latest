package com.gfg.minorproject.service;

import com.gfg.minorproject.model.Author;
import com.gfg.minorproject.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Author createOrGet(Author authorObj){
        Author author = authorRepository.findByEmail(authorObj.getEmail());
        if(author!=null){
            return author;
        }
        return this.authorRepository.save(authorObj);
    }
}
