package com.joma.studies.term.exception;

public class TermAnalyzerException extends RuntimeException{

    public TermAnalyzerException(String text, Throwable cause) {
        super("Unable to calculate measure for: " + text, cause);
    }
}
