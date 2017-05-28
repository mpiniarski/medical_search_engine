package com.joma.studies;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.relevance.ArticleRelevanceSorter;
import com.joma.studies.article.repository.ArticleRepository;
import com.joma.studies.article.repository.exception.RepositoryException;
import com.joma.studies.measure.TfMeasureCalculator;
import com.joma.studies.query.dto.QueryAnalysisDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class SearchEngine {

    private final ArticleRepository articleRepository;
    private final TfMeasureCalculator tfMeasureCalculator;

    @Autowired
    public SearchEngine(ArticleRepository articleRepository, TfMeasureCalculator tfMeasureCalculator) {
        this.articleRepository = articleRepository;
        this.tfMeasureCalculator = tfMeasureCalculator;
    }

    public SearchResultDto search(QueryAnalysisDto queryAnalysisDto, ArticleRelevanceSorter articleRelevanceSorter) throws RepositoryException {
        Set<String> queryWordSet = queryAnalysisDto.getMeasureMap().keySet();
        List<ArticleDto> articles = articleRepository.findByWordSet(queryWordSet);
        List<ArticleWithMeasureMapAndRelevanceDto> sortedArticles = articleRelevanceSorter.sort(queryAnalysisDto.getMeasureMap(), articles);
        return new SearchResultDto.Builder()
                .withArticles(sortedArticles)
                .withQueryAnalysisDto(queryAnalysisDto)
                .build();
    }

    public SearchResultDto search(String query, ArticleRelevanceSorter articleRelevanceSorter) throws RepositoryException {
        return search(new QueryAnalysisDto.Builder()
                        .withQuery(query)
                        .withMeasureMap(tfMeasureCalculator.calculate(query))
                        .build(),
                articleRelevanceSorter);
    }
}