package com.joma.studies.measure.exception;

public class MeasureCalculationException extends RuntimeException{

    public MeasureCalculationException(String text, Throwable cause) {
        super("Unable to calculate measure for: " + text, cause);
    }
}
