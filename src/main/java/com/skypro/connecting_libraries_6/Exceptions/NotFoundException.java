package com.skypro.connecting_libraries_6.Exceptions;

import jakarta.annotation.Nullable;

public class NotFoundException extends IllegalArgumentException {
    private static final long serialVersionUID = -6376824568L;

    public NotFoundException(@Nullable String message) {
        super(message);
    }

}
