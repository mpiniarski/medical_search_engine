package com.joma.studies.article.relevance;

import com.joma.studies.ArticleWithMeasureMapAndRelevanceDto;
import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.relevance.dto.ArticleAnalysisDto;
import com.joma.studies.measure.MeasureMap;
import com.joma.studies.measure.TfImportantTitleMeasureCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TfImportantTitleArticleRelevanceSorter implements ArticleRelevanceSorter {
    private static final int TITLE_WAGE = 10;

    private final DocumentDistanceSorter documentDistanceSorter;
    private final TfImportantTitleMeasureCalculator tfImportantTitleMeasureCalculator;

    @Autowired
    public TfImportantTitleArticleRelevanceSorter(DocumentDistanceSorter documentDistanceSorter,
                                                  TfImportantTitleMeasureCalculator tfImportantTitleMeasureCalculator) {
        this.documentDistanceSorter = documentDistanceSorter;
        this.tfImportantTitleMeasureCalculator = tfImportantTitleMeasureCalculator;
    }

    @Override
    public List<ArticleWithMeasureMapAndRelevanceDto> sort(MeasureMap queryMeasureMap, List<ArticleDto> articles) {
        return documentDistanceSorter.sort(
                queryMeasureMap.normalize(),
                articles.stream()
                        .map(article -> new ArticleAnalysisDto.Builder()
                                .withArticleDto(article)
                                .withMeasureMap(tfImportantTitleMeasureCalculator.calculate(article, TITLE_WAGE))
                                .build()
                        )
                        .collect(Collectors.toList()));
    }

}
