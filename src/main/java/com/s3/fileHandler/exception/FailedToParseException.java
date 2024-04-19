package com.s3.fileHandler.exception;

public class FailedToParseException extends RuntimeException{
    public FailedToParseException(String message)
    {
        super(message);
    }
}
