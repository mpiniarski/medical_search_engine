package com.joma.studies.query.exception;

public class UnableToParseQueryException extends Exception{

    public UnableToParseQueryException(String query, Throwable cause) {
        super("Unable to parse query: " + query, cause);
    }
}
