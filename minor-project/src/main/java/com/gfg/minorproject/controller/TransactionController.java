package com.gfg.minorproject.controller;

import com.gfg.minorproject.model.Student;
import com.gfg.minorproject.model.TransactionType;
import com.gfg.minorproject.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction/issue")
    public String issueTrans(@RequestParam("bookName") String bookName
                             ) throws Exception {
        int studentId = 0;
        return transactionService.issue_txn(bookName,studentId);
    }

    @PostMapping("/transaction/return")
    public String returnTxn(@RequestParam("bookId") Integer bookId) throws Exception {
        int studentId = 0;
        return transactionService.return_txn(bookId,studentId);
    }
}
