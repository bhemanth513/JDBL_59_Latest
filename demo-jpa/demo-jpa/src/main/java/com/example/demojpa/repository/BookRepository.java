package com.example.demojpa.repository;

import com.example.demojpa.models.Book;
import com.example.demojpa.models.BookCategory;
import com.example.demojpa.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query("select b from Book b where b.bookCategory = :bc")
    //@Query("select b from Book b where b.bookCategory = ?1")
    List<Book> findBooks(BookCategory bc);

    @Query(value = "select * from book b where b.book_category = :bc",nativeQuery=true)
    List<Book> findBooksUsingNativeQuery(String bc);

    List<Book> findByBookCategory(BookCategory category);

    List<Book> findByBookCategoryAndLanguage(BookCategory category, Language language);

    List<Book> findByBookCategoryAndLanguageAndPagesGreaterThan(BookCategory category, Language english, int pages);
}
