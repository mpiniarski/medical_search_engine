package com.joma.studies.query.exception;

public class UnableToParseQueryException extends RuntimeException{

    public UnableToParseQueryException(String query, Throwable cause) {
        super("Unable to parse query: " + query, cause);
    }
}
