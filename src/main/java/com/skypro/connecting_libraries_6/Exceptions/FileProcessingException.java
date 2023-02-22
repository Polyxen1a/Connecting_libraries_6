package com.skypro.connecting_libraries_6.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileProcessingException extends RuntimeException {

    public FileProcessingException() {
        super("Проблема в чтении файла.");
    }

    public FileProcessingException(String message) {
        super(message);
    }

    public FileProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileProcessingException(Throwable cause) {
        super(cause);
    }

    protected FileProcessingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
