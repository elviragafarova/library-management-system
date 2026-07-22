package com.example.mslibrarymanagementsystem.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(AuthorNotFoundException.class)
    public ErrorResponse handleException(AuthorNotFoundException ex) {
        return new ErrorResponse("author.not.found", ex.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public ErrorResponse handleException(BookNotFoundException ex) {
        return new ErrorResponse("book.not.found", ex.getMessage());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(MemberNotFoundException.class)
    public ErrorResponse handleException(MemberNotFoundException ex) {
        return new ErrorResponse("member.not.found", ex.getMessage());
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(BookAlreadyBorrowedException.class)
    public ErrorResponse handleException(BookAlreadyBorrowedException ex) {
        return new ErrorResponse("book.already.borrowed", ex.getMessage());
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(BookAlreadyReturnedException.class)
    public ErrorResponse handleException(BookAlreadyReturnedException ex) {
        return new ErrorResponse("book.already.returned", ex.getMessage());
    }
}