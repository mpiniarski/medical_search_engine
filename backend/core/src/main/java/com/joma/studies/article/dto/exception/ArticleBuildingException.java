package com.joma.studies.article.dto.exception;

public class ArticleBuildingException extends RuntimeException{
    private static final String MESSAGE = "Unable to build article";

    public ArticleBuildingException() {
        super(MESSAGE);
    }

    public ArticleBuildingException(Throwable throwable) {
        super(MESSAGE, throwable);
    }
}
