package com.joma.studies.measure;

import com.joma.studies.article.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TfImportantTitleMeasureCalculator {

    private final TfMeasureCalculator tfMeasureCalculator;

    @Autowired
    public TfImportantTitleMeasureCalculator(TfMeasureCalculator tfMeasureCalculator) {
        this.tfMeasureCalculator = tfMeasureCalculator;
    }

    public MeasureMap calculate(ArticleDto article, int titleWage) {
        MeasureMap titleMeasureMap = tfMeasureCalculator.calculate(article.getTitle());
        titleMeasureMap.forEach((key, value) -> titleMeasureMap.replace(key, value, value * titleWage));
        MeasureMap abstractMeasureMap = tfMeasureCalculator.calculate(article.getAbstractText());
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
