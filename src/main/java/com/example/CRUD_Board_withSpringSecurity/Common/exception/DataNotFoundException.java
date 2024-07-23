package com.example.CRUD_Board_withSpringSecurity.Common.exception;

public class DataNotFoundException extends Exception{
    public DataNotFoundException(String msg)
    {
        super(msg);
    }
}
