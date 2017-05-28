package com.joma.studies.measure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TfWeightedMeasureCalculator {

    private final TfMeasureCalculator tfMeasureCalculator;

    @Autowired
    public TfWeightedMeasureCalculator(TfMeasureCalculator tfMeasureCalculator) {
        this.tfMeasureCalculator = tfMeasureCalculator;
    }

    public MeasureMap calculate(List<String> terms, double weight) {
        MeasureMap measureMap = tfMeasureCalculator.calculate(terms);
        if(weight != 1.0){
            measureMap.forEach((key, value) -> measureMap.replace(key, value, value * weight));
        }
        return measureMap;
    }
}
