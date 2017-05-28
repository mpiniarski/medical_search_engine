package com.joma.studies.measure;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class IdfMeasureCalculator {

    public MeasureMap calculate(List<List<String>> termLists) {
        Map<String, Long> termInNumberOfDocuments = termLists.stream()
                .map(HashSet::new)
                .flatMap(Set::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        MeasureMap idfMeasureMap = new MeasureMap();
        termInNumberOfDocuments.forEach((key, value) ->
                idfMeasureMap.put(key, Math.log(termLists.size() / value.doubleValue()))
        );
        return idfMeasureMap;
    }

}
