package com.gfg.minorproject.service;

import com.gfg.minorproject.dto.SearchBookRequest;
import com.gfg.minorproject.model.*;
import com.gfg.minorproject.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    StudentService studentService;
    @Autowired
    BookService bookService;

    @Value("${student.issue.number_of_days}")
    private int book_issue_days_limit;

    @Value("${student.issue.max_books}")
    private int max_book_limit_issuance;

    @Autowired
    TransactionRepository transactionRepository;

    public String return_txn(int bookId, int studentId) throws Exception {
        Book book;
        try {
             book = bookService.search(SearchBookRequest.builder()
                            .searchKey("id")
                            .searchVal(String.valueOf(bookId))
                             .operator("=")
                            .build()).get(0);
        }catch (Exception e){
            throw new Exception("Not able to fetch book details");
        }
        //validation
        //if book is available in the library or if book is not assigned to  student
        if(book.getStudent()==null && book.getStudent().getId()!=studentId){
            throw new Exception("Book is not assigned to this student!!");
        }
        //retrieve the student details
        Student student = studentService.getStudentById(studentId);
        //	2.create a transaction with pending status
        Transaction transaction = Transaction.builder()
                .externalTnxId(UUID.randomUUID().toString())
                .transactionType(TransactionType.RETURN)
                .student(student)
                .book(book)
                .transactionStatus(TransactionStatus.PENDING)
                .build();
        transaction = transactionRepository.save(transaction);

        //getting the corresponding issue transaction.....
        Transaction issueTransaction = transactionRepository.findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByTransactionTimeDesc(
                student,book,TransactionType.ISSUE,TransactionStatus.SUCCESS);
        //find fee calc
        long issueTnxInMilliSeconds = issueTransaction.getTransactionTime().getTime();
        long timeDiffInMilliSeconds = System.currentTimeMillis()-issueTnxInMilliSeconds;
        long timeDifferenceInDays = TimeUnit.DAYS.convert(timeDiffInMilliSeconds,TimeUnit.MILLISECONDS);

        Double fine = 0.0;
        if(timeDifferenceInDays>book_issue_days_limit){
            fine = (timeDifferenceInDays-book_issue_days_limit)*1.0;
        }

        // 4.un assign the book from student //UPDATE Book table set student_id null
        try {
            book.setStudent(null);
            bookService.unAssignBookToStudent(book);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            transaction.setTransactionStatus(TransactionStatus.FAILED);
        }finally {
            transaction.setFine(fine);
            transactionRepository.save(transaction);
        }
        return  transaction.getExternalTnxId();
    }

    public String issue_txn(String bookName, Integer studentId) throws Exception {
        //	1. get the book details and student details
        List<Book> bookList;
        try {
            bookList = bookService.search(SearchBookRequest.builder()
                             .searchKey("name")
                             .searchVal(bookName)
                             .operator("=")
                            .available(true)
                              .build());
        } catch (Exception e) {
            throw new RuntimeException("Book Not Found!!");
        }
        Student student = studentService.getStudentById(studentId);
        //2. validate
        if(student.getStudentBookList()!=null && student.getStudentBookList().size()>=max_book_limit_issuance) {
           throw new Exception("Maximum book limit reached!");
        }

        if(bookList.isEmpty()){
            throw new Exception("Not able to find the requested book!");
        }

        Book book = bookList.get(0);
        //3.create a transaction with pending status
        Transaction transaction = Transaction.builder()
                .externalTnxId(UUID.randomUUID().toString())
                .transactionType(TransactionType.ISSUE)
                .student(student)
                .book(book)
                .transactionStatus(TransactionStatus.PENDING)
                .build();
        transaction = transactionRepository.save(transaction);

        //	4. assign book to that perticular student //update book set student id = ?
        try {
            book.setStudent(student);
            bookService.assignBookToStudent(book,student);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            transaction.setTransactionStatus(TransactionStatus.FAILED);

        }finally {
            return transactionRepository.save(transaction).getExternalTnxId();
        }
    }
}
