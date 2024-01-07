package com.gfg.minorproject.service;

import com.gfg.minorproject.dto.CreateBookRequest;
import com.gfg.minorproject.dto.SearchBookRequest;
import com.gfg.minorproject.model.Author;
import com.gfg.minorproject.model.Book;
import com.gfg.minorproject.model.Genre;
import com.gfg.minorproject.repository.AuthorRepository;
import com.gfg.minorproject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    AuthorService authorService;

    public Book addBook(CreateBookRequest createBookRequest) {
        Book book = createBookRequest.to();
        //getting Author details and saving in DB if not already exists
        Author author = authorService.createOrGet(book.getMy_author());
        book.setMy_author(author);
        return bookRepository.save(book);
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book deleteBook(Integer bookId) {
       Book book= bookRepository.findById(bookId).orElse(null);
       if(book!=null) bookRepository.deleteById(bookId);
        return book;
    }


    public List<Book> search(SearchBookRequest searchBookRequest) throws Exception {
        Boolean isValidRequest = searchBookRequest.validate();
        if(!isValidRequest){
            throw new Exception("Invalid Request");
        }
        List<Book> resultBookList = null;
        //String sql = "select b from Book b where searchKey searchVal searchOperator";
        switch (searchBookRequest.getSearchKey()){
            case "name":
                resultBookList = bookRepository.findByName(searchBookRequest.getSearchVal());
                break;
            case "genre":
                resultBookList =bookRepository.findByGenre(Genre.valueOf(searchBookRequest.getSearchVal()));
                break;
            case "id":
                Book book = bookRepository.findById(Integer.valueOf(searchBookRequest.getSearchVal())).orElse(null);
                resultBookList = Arrays.asList(book);
                break;
            default:
                throw new Exception("Invalid Search Key");
        }
        return resultBookList;
    }
}
