package com.joma.studies.measure;

import com.joma.studies.measure.exception.MeasureCalculationException;

public interface SimpleMeasureCalculator {
    MeasureMap calculate(String text) throws MeasureCalculationException;
}
