package com.gfg.minorproject.repository;


import com.gfg.minorproject.model.Book;
import com.gfg.minorproject.model.Genre;
import com.gfg.minorproject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {


    List<Book> findByName(String name);

    List<Book> findByGenre(Genre genre);
    //@Query("select b from Book b where b.name = :name and b.student is null")
    List<Book> findByNameAndStudentIsNull(String name);

    @Modifying
    @Transactional
    @Query("update Book b set b.student = ?2 where b.id = ?1 and b.student is null")
    void assignBookToStudent(int bookId, Student student);
    @Modifying
    @Transactional
    @Query("update Book b set b.student = NULL where b.id = ?1")
    void unAssignBookToStudent(int id);
}
