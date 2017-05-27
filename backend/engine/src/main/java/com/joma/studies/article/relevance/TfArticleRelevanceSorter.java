package com.joma.studies.article.relevance;

import com.joma.studies.ArticleWithRelevanceDto;
import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.relevance.dto.ArticleAnalysisDto;
import com.joma.studies.measure.MeasureMap;
import com.joma.studies.measure.TfMeasureCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TfArticleRelevanceSorter implements ArticleRelevanceSorter {
    private final DocumentDistanceSorter documentDistanceSorter;
    private final TfMeasureCalculator tfMeasureCalculator;

    @Autowired
    public TfArticleRelevanceSorter(DocumentDistanceSorter documentDistanceSorter, TfMeasureCalculator tfMeasureCalculator) {
        this.documentDistanceSorter = documentDistanceSorter;
        this.tfMeasureCalculator = tfMeasureCalculator;
    }

    @Override
    public List<ArticleWithRelevanceDto> sort(MeasureMap queryMeasureMap, List<ArticleDto> articles) {
        return documentDistanceSorter.sort(
                queryMeasureMap,
                articles.stream()
                        .map(article -> new ArticleAnalysisDto.Builder()
                                .withArticleDto(article)
                                .withMeasureMap(tfMeasureCalculator.calculate(article.toString()))
                                .build()
                        )
                        .collect(Collectors.toList()));
    }

}
