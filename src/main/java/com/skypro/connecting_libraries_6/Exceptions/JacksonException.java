package com.skypro.connecting_libraries_6.Exceptions;

import com.fasterxml.jackson.core.JsonLocation;

import java.io.IOException;

public abstract class JacksonException extends IOException {
    private static final long serialVersionUID = 123L;

    protected JacksonException(String msg) {
        super(msg);
    }

    protected JacksonException(Throwable throwable) {
        super(t);
    }

    protected JacksonException(String msg, Throwable rootCase) {
        super(msg, rootCase);
    }

    public abstract JsonLocation getLocation();

    public abstract String getOriginalMessage();

    public abstract Object getProcessor();
}
