package com.gfg.minorproject.exception;

public class BookLimitExceededException extends Exception{

    public BookLimitExceededException(String msg){
        super(msg);
    }
}
