package com.cs4523groupb11.Motify.exceptions;


public class UserExistsException extends Exception{
    public UserExistsException(String msg){
        super(msg);
    }
}