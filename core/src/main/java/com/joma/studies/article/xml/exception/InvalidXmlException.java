package com.joma.studies.article.xml.exception;

public class InvalidXmlException extends Exception{
    private static final String MESSAGE = "Unable to read xml - format is invalid";

    public InvalidXmlException() {
        super(MESSAGE);
    }

    public InvalidXmlException(Throwable throwable) {
        super(MESSAGE, throwable);
    }
}
