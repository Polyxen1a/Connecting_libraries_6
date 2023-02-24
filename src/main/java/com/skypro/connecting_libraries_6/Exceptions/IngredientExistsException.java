package com.skypro.connecting_libraries_6.Exceptions;

import com.skypro.connecting_libraries_6.model.Ingredient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class IngredientExistsException extends RuntimeException {
    public IngredientExistsException() {
        super("Книга ингредиентов уже содержит этот ингредиент.");
    }

    public IngredientExistsException(String message) {
        super(message);
    }

    public IngredientExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public IngredientExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
