package com.joma.studies.article.relevance.sorter;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.relevance.dto.MeasuredArticleDto;
import com.joma.studies.article.relevance.dto.RatedAndMeasuredArticleDto;
import com.joma.studies.article.relevance.sorter.distance.DocumentDistanceSorter;
import com.joma.studies.measure.MeasureMap;
import com.joma.studies.measure.TfSquaredMeasureCalculator;
import com.joma.studies.term.TermAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TfSquaredRelevanceSorter implements RelevanceSorter {
    private final DocumentDistanceSorter documentDistanceSorter;
    private final TfSquaredMeasureCalculator tfSquaredMeasureCalculator;
    private final TermAnalyzer termAnalyzer;

    @Autowired
    public TfSquaredRelevanceSorter(DocumentDistanceSorter documentDistanceSorter,
                                    TfSquaredMeasureCalculator tfSquaredMeasureCalculator, TermAnalyzer termAnalyzer) {
        this.documentDistanceSorter = documentDistanceSorter;
        this.tfSquaredMeasureCalculator = tfSquaredMeasureCalculator;
        this.termAnalyzer = termAnalyzer;
    }

    @Override
    public List<RatedAndMeasuredArticleDto> sort(MeasureMap queryMeasureMap, List<ArticleDto> articles) {
        List<MeasuredArticleDto> measuredArticles = articles.stream()
                .map(article -> {
                    List<String> terms = termAnalyzer.getTerms(article.toString());
                    MeasureMap measureMap = tfSquaredMeasureCalculator.calculate(terms);
                    return new MeasuredArticleDto.Builder()
                            .withArticleDto(article)
                            .withMeasureMap(measureMap)
                            .build();
                })
                .collect(Collectors.toList());
        return documentDistanceSorter.sort(queryMeasureMap, measuredArticles);
    }

}
