package com.skypro.connecting_libraries_6.controller;

import com.skypro.connecting_libraries_6.Exceptions.FileProcessingException;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.io.FileNotFoundException;

public interface HandleMethodArgumentNotValid extends MessageSourceAware {
    ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                        HttpHeaders headers,
                                                        HttpStatus status,
                                                        WebRequest request);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileNotFoundException.class)
    String handleFileNotfoundExceptions(FileProcessingException ex);

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileProcessingException.class)
    String handleFileProcessingException(FileProcessingException ex);
}
