package com.gfg.minorproject;

import com.gfg.minorproject.dto.SearchBookRequest;
import com.gfg.minorproject.exception.BookLimitExceededException;
import com.gfg.minorproject.exception.BookNotFoundException;
import com.gfg.minorproject.model.Book;
import com.gfg.minorproject.model.Student;
import com.gfg.minorproject.model.Transaction;
import com.gfg.minorproject.repository.TransactionRepository;
import com.gfg.minorproject.service.BookService;
import com.gfg.minorproject.service.StudentService;
import com.gfg.minorproject.service.TransactionService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    @Mock
    StudentService studentService;

    @Mock
    BookService bookService;

    @Mock
    TransactionRepository transactionRepository;


    @Test
    public void issueTnx_test() throws Exception {
        int studentId =1;
        String bookName = "ABC";
        String externalTnxId = UUID.randomUUID().toString();
        List<Book> bookList = Arrays.asList(Book.builder()
                .id(1)
                .name(bookName)
                .build());

        Student student = Student.builder()
                        .id(1)
                        .name("Hemanth")
                        .build();
        Transaction transaction = Transaction.builder()
                .externalTnxId(externalTnxId)
                .book(bookList.get(0))
                .student(student)
                    .build();
        when(bookService.search(any())).thenReturn(bookList);
        when(studentService.getStudentById(studentId)).thenReturn(student);

        when(transactionRepository.save(any())).thenReturn(transaction);
       // Mockito.doNothing().when(bookService.assignBookToStudent(Mockito.any(),Mockito.any()));
        String tnxIdReturned = transactionService.issue_txn(bookName,studentId);

        //expected result = actual result
        Assert.assertEquals(externalTnxId,tnxIdReturned);

        //1)Assertions
        //2)verifications

        verify(studentService,times(1)).getStudentById(studentId);
        verify(transactionRepository,times(2)).save(any());

        verify(bookService,times(1))
                .assignBookToStudent(any(),any());

    }

    //failure scenario
    @Test(expected = BookNotFoundException.class)
    public void issueTnx_bookNotFound() throws Exception {
        String bookName = "ABC";
        int studentId = 1;

        Student student = Student.builder()
                .id(1)
                .name("Hemanth")
                .build();

        when(bookService.search(any())).thenReturn(new ArrayList<>());
        when(studentService.getStudentById(studentId)).thenReturn(student);
        String txnIdReturned = transactionService.issue_txn(bookName,studentId);
    }

    @Test(expected = BookLimitExceededException.class)
    public void issueTnx_BookLimitExceed() throws Exception{
        String bookName = "ABC";
        int studentId = 1;

        List<Book> bookList = Arrays.asList(Book.builder()
                .id(1)
                .name(bookName)
                .build());

        Student student = Student.builder()
                .id(1)
                .name("Hemanth")
                .studentBookList(Arrays.asList(
                        Book.builder().id(1).build(),
                        Book.builder().id(2).build(),
                        Book.builder().id(3).build()
                ))
                .build();

        when(bookService.search(any())).thenReturn(bookList);
        when(studentService.getStudentById(studentId)).thenReturn(student);
        String txnIdReturned = transactionService.issue_txn(bookName,studentId);

        verify(transactionRepository,times(0)).save(any());
        verify(bookService,times(0)).assignBookToStudent(any(),any());
    }
}
