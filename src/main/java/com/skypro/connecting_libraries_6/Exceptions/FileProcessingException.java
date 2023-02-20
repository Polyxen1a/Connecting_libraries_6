package com.skypro.connecting_libraries_6.Exceptions;

public class FileProcessingException extends RuntimeException {
    public FileProcessingException() {
        super("Проблема в чтении файла");
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

    protected FileProcessingException(String message, Throwable cause, boolean enableSupression, boolean writableStackTrace) {
        super(message, cause, enableSupression, writableStackTrace);
    }
}
