package com.joma.studies.measure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TfSquaredMeasureCalculator {

    private final TfMeasureCalculator tfMeasureCalculator;

    @Autowired
    public TfSquaredMeasureCalculator(TfMeasureCalculator tfMeasureCalculator) {
        this.tfMeasureCalculator = tfMeasureCalculator;
    }

    public MeasureMap calculate(List<String> terms) {
        MeasureMap result = tfMeasureCalculator.calculate(terms);
        result.replaceAll((key, value) -> value * value);
        return result;
    }
}
