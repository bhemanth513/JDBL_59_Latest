package com.gfg.minorproject.repository;


import com.gfg.minorproject.model.Book;
import com.gfg.minorproject.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {


    //Book findByEmail(String email);

    List<Book> findByName(String searchVal);

    List<Book> findByGenre(Genre valueOf);
}
