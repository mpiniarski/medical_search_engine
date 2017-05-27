package com.joma.studies.measure;

import com.joma.studies.measure.exception.MeasureCalculationException;

import java.util.List;

public interface RelativeMeasureCalculator {
    MeasureMap calculate(List<String> texts) throws MeasureCalculationException;
}
