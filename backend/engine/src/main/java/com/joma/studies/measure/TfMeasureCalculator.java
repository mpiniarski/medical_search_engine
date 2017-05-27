package com.joma.studies.measure;

import com.joma.studies.measure.exception.MeasureCalculationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TfMeasureCalculator implements SimpleMeasureCalculator {

    private final TermAnalyzer termAnalyzer;

    @Autowired
    public TfMeasureCalculator(TermAnalyzer termAnalyzer) {
        this.termAnalyzer = termAnalyzer;
    }

    @Override
    //TODO should it be divided by term number?
    public MeasureMap calculate(String text) throws MeasureCalculationException {
        MeasureMap result = new MeasureMap();
        for (String term : termAnalyzer.getTermList(text)) {
            result.put(term, result.getOrDefault(term, 0.) + 1.);
        }
        return result;
    }
}
