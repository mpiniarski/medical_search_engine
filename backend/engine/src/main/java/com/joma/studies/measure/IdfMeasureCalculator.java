package com.joma.studies.measure;

import com.joma.studies.measure.exception.MeasureCalculationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class IdfMeasureCalculator implements RelativeMeasureCalculator {
    private final TermAnalyzer termAnalyzer;

    @Autowired
    public IdfMeasureCalculator(TermAnalyzer termAnalyzer) {
        this.termAnalyzer = termAnalyzer;
    }

    @Override
    public MeasureMap calculate(List<String> texts) throws MeasureCalculationException {
        Map<String, Long> wordInNumberOfDocuments = texts.stream()
                .map(termAnalyzer::getTermList)
                .map(HashSet::new)
                .flatMap(Set::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        MeasureMap idfMeasureMap = new MeasureMap();
        wordInNumberOfDocuments.forEach((key, value) ->
                idfMeasureMap.put(key, Math.log10(texts.size() / value))
        );
        return idfMeasureMap;
    }

}
