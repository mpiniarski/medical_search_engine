package com.joma.studies.article.relevance.sorter;

import com.joma.studies.article.relevance.dto.RatedAndMeasuredArticleDto;
import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.relevance.dto.MeasuredArticleDto;
import com.joma.studies.article.relevance.sorter.distance.DocumentDistanceSorter;
import com.joma.studies.measure.MeasureMap;
import com.joma.studies.measure.TfMeasureCalculator;
import com.joma.studies.term.TermAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TfRelevanceSorter implements RelevanceSorter {
    private final DocumentDistanceSorter documentDistanceSorter;
    private final TfMeasureCalculator tfMeasureCalculator;
    private final TermAnalyzer termAnalyzer;

    @Autowired
    public TfRelevanceSorter(DocumentDistanceSorter documentDistanceSorter, TfMeasureCalculator tfMeasureCalculator, TermAnalyzer termAnalyzer) {
        this.documentDistanceSorter = documentDistanceSorter;
        this.tfMeasureCalculator = tfMeasureCalculator;
        this.termAnalyzer = termAnalyzer;
    }

    @Override
    public List<RatedAndMeasuredArticleDto> sort(MeasureMap queryMeasureMap, List<ArticleDto> articles) {
        List<MeasuredArticleDto> articleAnalysisList = articles
                .stream()
                .map(article -> {
                    List<String> terms = termAnalyzer.getTerms(article.toString());
                    MeasureMap measureMap = tfMeasureCalculator.calculate(terms);
                    return new MeasuredArticleDto.Builder()
                            .withArticleDto(article)
                            .withMeasureMap(measureMap)
                            .build();
                })
                .collect(Collectors.toList());
        return documentDistanceSorter.sort(queryMeasureMap, articleAnalysisList);
    }

}
