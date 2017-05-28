package com.joma.studies.measure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TfWeightedArticleMeasureCalculator {

    private final TfWeightedMeasureCalculator tfWeightedMeasureCalculator;

    @Autowired
    public TfWeightedArticleMeasureCalculator(TfWeightedMeasureCalculator tfWeightedMeasureCalculator) {
        this.tfWeightedMeasureCalculator = tfWeightedMeasureCalculator;
    }

    public MeasureMap calculate(List<String> titleTerms, double titleWeight,
                                List<String> abstractTerms, double abstractWeight) {
        MeasureMap titleMeasureMap = tfWeightedMeasureCalculator.calculate(titleTerms, titleWeight);
        MeasureMap abstractMeasureMap = tfWeightedMeasureCalculator.calculate(abstractTerms, abstractWeight);
        return new MeasureMap(
                Stream.of(titleMeasureMap, abstractMeasureMap)
                        .map(Map::entrySet)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                Double::sum
                        ))
        );
    }
}
