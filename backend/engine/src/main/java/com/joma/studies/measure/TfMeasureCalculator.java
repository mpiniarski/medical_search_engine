package com.joma.studies.measure;

import com.joma.studies.measure.term.TermAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TfMeasureCalculator {

    private final TermAnalyzer termAnalyzer;

    @Autowired
    public TfMeasureCalculator(TermAnalyzer termAnalyzer) {
        this.termAnalyzer = termAnalyzer;
    }

    public MeasureMap calculate(String text) {
        MeasureMap result = new MeasureMap();
        for (String term : termAnalyzer.getTermList(text)) {
            result.put(term, result.getOrDefault(term, 0.) + 1.);
        }
        return result;
    }
}
