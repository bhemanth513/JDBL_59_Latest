package com.example.demojpa.controller;

import com.example.demojpa.dto.CreateBookRequest;
import com.example.demojpa.models.Book;
import com.example.demojpa.models.BookCategory;
import com.example.demojpa.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/book")
    public Book getBookId(@RequestParam("id") int id){
        return bookService.get(id);
    }
    @GetMapping("/books/all")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @RequestMapping(value = "/book",method = RequestMethod.POST)
    public Book createBook(@RequestBody @Valid CreateBookRequest createBookRequest){
        return bookService.createBook(createBookRequest.to());
    }
    //try to avoid using underscore and camel cases in api path, instead use hypen
    @GetMapping("/books-by-category")
    public List<Book> getBooksByCategory(@RequestParam("category") BookCategory category){
        return bookService.bookByCategory(category);
    }
}
