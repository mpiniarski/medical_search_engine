package com.joma.studies.article.relevance.sorter;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.relevance.dto.MeasuredArticleDto;
import com.joma.studies.article.relevance.dto.RatedAndMeasuredArticleDto;
import com.joma.studies.article.relevance.sorter.distance.DocumentDistanceSorter;
import com.joma.studies.measure.MeasureMap;
import com.joma.studies.measure.TfWeightedArticleMeasureCalculator;
import com.joma.studies.term.TermAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TfBoostTitleRelevanceSorter implements RelevanceSorter {
    private static final double TITLE_WEIGHT = 10.0;
    private static final double DEFAULT_WEIGHT = 1.0;

    private final DocumentDistanceSorter documentDistanceSorter;
    private final TfWeightedArticleMeasureCalculator tfWeightedArticleMeasureCalculator;
    private final TermAnalyzer termAnalyzer;

    @Autowired
    public TfBoostTitleRelevanceSorter(DocumentDistanceSorter documentDistanceSorter,
                                       TfWeightedArticleMeasureCalculator tfWeightedArticleMeasureCalculator, TermAnalyzer termAnalyzer) {
        this.documentDistanceSorter = documentDistanceSorter;
        this.tfWeightedArticleMeasureCalculator = tfWeightedArticleMeasureCalculator;
        this.termAnalyzer = termAnalyzer;
    }

    @Override
    public List<RatedAndMeasuredArticleDto> sort(MeasureMap queryMeasureMap, List<ArticleDto> articles) {
        List<MeasuredArticleDto> measuredArticles = articles
                .stream()
                .map(article -> {
                    List<String> titleTerms = termAnalyzer.getTerms(article.getTitle());
                    List<String> abstractTerms = termAnalyzer.getTerms(article.getAbstractText());
                    MeasureMap measureMap = tfWeightedArticleMeasureCalculator.calculate(
                            titleTerms, TITLE_WEIGHT,
                            abstractTerms, DEFAULT_WEIGHT
                    );
                    return new MeasuredArticleDto.Builder()
                            .withArticleDto(article)
                            .withMeasureMap(measureMap)
                            .build();
                })
                .collect(Collectors.toList());
        return documentDistanceSorter.sort(queryMeasureMap, measuredArticles);
    }

}
