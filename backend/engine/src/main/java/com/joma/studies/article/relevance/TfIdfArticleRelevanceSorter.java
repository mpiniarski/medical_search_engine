package com.joma.studies.article.relevance;

import com.joma.studies.ArticleWithRelevanceDto;
import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.relevance.dto.ArticleAnalysisDto;
import com.joma.studies.measure.MeasureMap;
import com.joma.studies.measure.TfMeasureCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TfIdfArticleRelevanceSorter implements ArticleRelevanceSorter {

    private final DocumentDistanceSorter documentDistanceSorter;
    private final TfMeasureCalculator articleMeasureCalculator;

    @Autowired
    public TfIdfArticleRelevanceSorter(DocumentDistanceSorter documentDistanceSorter, TfMeasureCalculator articleMeasureCalculator) {
        this.documentDistanceSorter = documentDistanceSorter;
        this.articleMeasureCalculator = articleMeasureCalculator;
    }

    @Override
    public List<ArticleWithRelevanceDto> sort(MeasureMap queryMeasureMap, List<ArticleDto> articles) {

        //TODO move to some RelativeMeasureCalculator (tf-idf)
        List<ArticleAnalysisDto> articleAnalysisDtos = articles.stream()
                .map(article -> new ArticleAnalysisDto.Builder()
                        .withArticleDto(article)
                        .withTermFrequency(articleMeasureCalculator.calculate(article.toString()))
                        .build()
                )
                .collect(Collectors.toList());

        MeasureMap dictionary = new MeasureMap(
                articleAnalysisDtos.stream()
                        .map(ArticleAnalysisDto::getMeasureMap)
                        .map(Map::entrySet)
                        .flatMap(Set::stream)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (a, b) -> a + b
                        ))
        );

        articleAnalysisDtos.forEach(
                article -> article.getMeasureMap().replaceAll(
                        (k, v) -> v / dictionary.get(k)
                ));

        return documentDistanceSorter.sort(queryMeasureMap, articleAnalysisDtos);
    }
}
