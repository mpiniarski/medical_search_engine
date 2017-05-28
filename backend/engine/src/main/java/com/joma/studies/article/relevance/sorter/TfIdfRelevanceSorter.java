package com.joma.studies.article.relevance.sorter;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.relevance.dto.MeasuredArticleDto;
import com.joma.studies.article.relevance.dto.RatedAndMeasuredArticleDto;
import com.joma.studies.article.relevance.sorter.distance.DocumentDistanceSorter;
import com.joma.studies.measure.IdfMeasureCalculator;
import com.joma.studies.measure.MeasureMap;
import com.joma.studies.measure.TfMeasureCalculator;
import com.joma.studies.term.TermAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TfIdfRelevanceSorter implements RelevanceSorter {

    private final DocumentDistanceSorter documentDistanceSorter;
    private final TfMeasureCalculator tfMeasureCalculator;
    private final IdfMeasureCalculator idfMeasureCalculator;
    private final TermAnalyzer termAnalyzer;

    @Autowired
    public TfIdfRelevanceSorter(DocumentDistanceSorter documentDistanceSorter, TfMeasureCalculator tfMeasureCalculator, IdfMeasureCalculator idfMeasureCalculator, TermAnalyzer termAnalyzer) {
        this.documentDistanceSorter = documentDistanceSorter;
        this.tfMeasureCalculator = tfMeasureCalculator;
        this.idfMeasureCalculator = idfMeasureCalculator;
        this.termAnalyzer = termAnalyzer;
    }

    @Override
    public List<RatedAndMeasuredArticleDto> sort(MeasureMap queryMeasureMap, List<ArticleDto> articles) {

        MeasureMap idfMeasureMap = idfMeasureCalculator.calculate(
                articles.stream()
                        .map(ArticleDto::toString)
                        .map(termAnalyzer::getTerms)
                        .collect(Collectors.toList())
        );

        List<MeasuredArticleDto> measuredArticles = articles
                .stream()
                .map(article -> {
                    List<String> terms = termAnalyzer.getTerms(article.toString());
                    MeasureMap measureMap = tfMeasureCalculator.calculate(terms);
                    return new MeasuredArticleDto.Builder()
                            .withArticleDto(article)
                            .withMeasureMap(tfIdfMeasureMap(measureMap, idfMeasureMap))
                            .build();
                })
                .collect(Collectors.toList());

        return documentDistanceSorter.sort(queryMeasureMap, measuredArticles);
    }

    private MeasureMap tfIdfMeasureMap(MeasureMap tfMeasureMap, MeasureMap idfMeasureMap) {
        MeasureMap tfIdfMeasureMap = new MeasureMap();
        tfMeasureMap.forEach(
                (k, v) -> tfIdfMeasureMap.put(k, v * idfMeasureMap.get(k))
        );
        return tfIdfMeasureMap;
    }
}
