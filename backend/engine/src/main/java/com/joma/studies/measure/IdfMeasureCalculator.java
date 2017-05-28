package com.joma.studies.measure;

import com.joma.studies.measure.term.TermAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class IdfMeasureCalculator {
    private final TermAnalyzer termAnalyzer;

    @Autowired
    public IdfMeasureCalculator(TermAnalyzer termAnalyzer) {
        this.termAnalyzer = termAnalyzer;
    }

    public MeasureMap calculate(List<String> texts) {
        Map<String, Long> termInNumberOfDocuments = texts.stream()
                .map(termAnalyzer::getTermList)
                .map(HashSet::new)
                .flatMap(Set::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        MeasureMap idfMeasureMap = new MeasureMap();
        termInNumberOfDocuments.forEach((key, value) ->
                //TODO it this +1 ok?
                idfMeasureMap.put(key, Math.log(texts.size() / value.doubleValue()) + 1)
        );
        return idfMeasureMap;
    }

}
