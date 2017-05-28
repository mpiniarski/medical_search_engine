package com.joma.studies.article.xml.exception;

public class InvalidArticleXmlFormat extends RuntimeException {
    private static final String MESSAGE = "Unable to parse article - xml format is invalid";
    public InvalidArticleXmlFormat() {
        super(MESSAGE);
    }

    public InvalidArticleXmlFormat(Throwable throwable) {
        super(MESSAGE, throwable);
    }
}
