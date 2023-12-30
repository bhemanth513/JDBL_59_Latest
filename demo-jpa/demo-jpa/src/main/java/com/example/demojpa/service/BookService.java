package com.example.demojpa.service;

import com.example.demojpa.models.Book;
import com.example.demojpa.models.BookCategory;
import com.example.demojpa.models.Language;
import com.example.demojpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public Book get(int id) {
        return  bookRepository.findById(id).orElse(null);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> bookByCategory(BookCategory category) {
        //category using JPQL query
        //return bookRepository.findBooks(category);

        //category using native query
       // return  bookRepository.findBooksUsingNativeQuery(category.name());
       // return  bookRepository.findBooksUsingNativeQuery(category.name());

        //without using any query
        //return bookRepository.findByBookCategory(category);
        //return bookRepository.findByBookCategoryAndLanguage(category, Language.ENGLISH);
        return bookRepository.findByBookCategoryAndLanguageAndPagesGreaterThan(category,Language.ENGLISH,300);
    }
}
