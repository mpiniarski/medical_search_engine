package com.joma.studies.article.repository.exception;

public class RepositoryException extends Exception{

    public static final String MESSAGE = "Unable to perform operation";

    public RepositoryException() {
        super(MESSAGE);
    }

    public RepositoryException(Throwable throwable) {
        super(MESSAGE, throwable);
    }
}
