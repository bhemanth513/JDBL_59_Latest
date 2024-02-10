package com.gfg.minorproject.controller;

import com.gfg.minorproject.dto.CreateBookRequest;
import com.gfg.minorproject.dto.SearchBookRequest;
import com.gfg.minorproject.model.Book;
import com.gfg.minorproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/minor-project")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public Book getBookById(@RequestParam("id") Integer id){
        return  bookService.getBookById(id);
    }

    @GetMapping("/books/all")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    //incase of searching book we have different possiblities
    //1.search book which has pages lessthan 500 (pages<500)
    //2.get all the books of given author name(author_name = peter)
    //3. search based on book name(name 'java book' =)
    @GetMapping("/{search}")
    public List<Book> getBooksByAuthor(@RequestBody @Valid SearchBookRequest searchBookRequest) throws Exception {

        return bookService.search(searchBookRequest);
    }

    @PostMapping("/book")
    public Book addBook(@RequestBody @Valid CreateBookRequest createBookRequest){
        return bookService.addBook(createBookRequest);

    }



    @DeleteMapping("/deletebook/{id}")
    public Book  deleteBook(@PathVariable("id") Integer bookId){
        return bookService.deleteBook(bookId);
    }
}
