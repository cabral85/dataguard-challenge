package com.dataguard.challenge.exception;

public class NotFoundException extends Exception{
    public NotFoundException(String msg){
        super(msg);
    }
    public NotFoundException(){
        super();
    }
}
