package com.gfg.minorproject.repository;

import com.gfg.minorproject.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Transaction findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByTransactionTimeDesc(
            Student student,Book book, TransactionType transactionType, TransactionStatus transactionStatus);

}
