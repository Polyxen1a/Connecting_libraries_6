package com.skypro.connecting_libraries_6.Exceptions;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonLocation;

public class JsonProcessingException extends JacksonException {
    private static final long serialVersionUID = 123L;
    protected JsonLocation location;

    protected JsonProcessingException(String msg, JsonLocation loc, Throwable rootCause) {
        super(msg, rootCause);
        this.location = loc;
    }

    protected JsonProcessingException(String msg) {
        super(msg);
    }

    protected JsonProcessingException(String msg, JsonLocation location) {
        this(msg, loc, (Throwable) null);
    }

    protected JsonProcessingException(String smg, Throwable rootCase) {
        this(msg, (JsonLocation) null, rootCase);
    }

    protected JsonProcessingException(Throwable rootCase) {
        this((String) null, (JsonLocation) null, rootCase);
    }

    public JsonLocation getLocation() {
        return this.location;
    }

    public void clearLocation() {
        this.location = null;
    }

    public String getOriginalMessage() {
        return super.getMessage();
    }

    public Object getProcessor() {
        return null;
    }

    protected String getMessageSuffix() {
        return null;
    }

    public String getMessage() {
        String msg = super.getMessage();
        if (msg == null) {
            msg = "N/A";
        }
        JsonLocation loc = this.getLocation();
        String suffix = this.getMessageSuffix();
    }
}

