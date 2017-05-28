package com.joma.studies.article.relevance;

import com.joma.studies.ArticleWithMeasureMapAndRelevanceDto;
import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.relevance.dto.ArticleAnalysisDto;
import com.joma.studies.measure.IdfMeasureCalculator;
import com.joma.studies.measure.MeasureMap;
import com.joma.studies.measure.TfMeasureCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TfIdfArticleRelevanceSorter implements ArticleRelevanceSorter {

    private final DocumentDistanceSorter documentDistanceSorter;
    private final TfMeasureCalculator tfMeasureCalculator;
    private final IdfMeasureCalculator idfMeasureCalculator;

    @Autowired
    public TfIdfArticleRelevanceSorter(DocumentDistanceSorter documentDistanceSorter, TfMeasureCalculator tfMeasureCalculator, IdfMeasureCalculator idfMeasureCalculator) {
        this.documentDistanceSorter = documentDistanceSorter;
        this.tfMeasureCalculator = tfMeasureCalculator;
        this.idfMeasureCalculator = idfMeasureCalculator;
    }

    @Override
    public List<ArticleWithMeasureMapAndRelevanceDto> sort(MeasureMap queryMeasureMap, List<ArticleDto> articles) {

        MeasureMap idfMeasureMap = idfMeasureCalculator.calculate(
                articles.stream()
                        .map(ArticleDto::toString)
                        .collect(Collectors.toList())
        );

        List<ArticleAnalysisDto> articleAnalysisDtos = articles
                .stream()
                .map(article -> {
                    MeasureMap tfMeasureMap = tfMeasureCalculator.calculate(article.toString());
                    return new ArticleAnalysisDto.Builder()
                            .withArticleDto(article)
                            .withMeasureMap(tfIdfMeasureMap(tfMeasureMap, idfMeasureMap))
                            .build();
                })
                .collect(Collectors.toList());

        return documentDistanceSorter.sort(queryMeasureMap, articleAnalysisDtos);
    }

    private MeasureMap tfIdfMeasureMap(MeasureMap tfMeasureMap, MeasureMap idfMeasureMap) {
        MeasureMap tfIdfMeasureMap = new MeasureMap();
        tfMeasureMap.forEach(
                (k, v) -> tfIdfMeasureMap.put(k, v * idfMeasureMap.get(k))
        );
        return tfIdfMeasureMap;
    }
}
