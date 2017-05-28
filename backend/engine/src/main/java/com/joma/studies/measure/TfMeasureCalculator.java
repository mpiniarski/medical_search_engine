package com.joma.studies.measure;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TfMeasureCalculator {

    public MeasureMap calculate(List<String> terms) {
        MeasureMap result = new MeasureMap();
        for (String term : terms) {
            result.put(term, result.getOrDefault(term, 0.) + 1.);
        }
        return result;
    }
}
