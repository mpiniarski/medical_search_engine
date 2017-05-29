package com.joma.studies;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.relevance.dto.RatedAndMeasuredArticleDto;
import com.joma.studies.article.relevance.sorter.RelevanceSorter;
import com.joma.studies.article.repository.ArticleRepository;
import com.joma.studies.article.repository.exception.RepositoryException;
import com.joma.studies.measure.MeasureMap;
import com.joma.studies.measure.TfMeasureCalculator;
import com.joma.studies.term.TermAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class SearchEngine {

    private final ArticleRepository articleRepository;
    private final TfMeasureCalculator tfMeasureCalculator;
    private final TermAnalyzer termAnalyzer;

    @Autowired
    public SearchEngine(ArticleRepository articleRepository, TfMeasureCalculator tfMeasureCalculator, TermAnalyzer termAnalyzer) {
        this.articleRepository = articleRepository;
        this.tfMeasureCalculator = tfMeasureCalculator;
        this.termAnalyzer = termAnalyzer;
    }

    public SearchResultDto search(QueryAnalysisDto queryAnalysisDto, RelevanceSorter relevanceSorter) throws RepositoryException {
        Set<String> queryWordSet = queryAnalysisDto.getMeasureMap().keySet();
        List<ArticleDto> articles = articleRepository.findByWordSet(queryWordSet);
        List<RatedAndMeasuredArticleDto> sortedArticles = relevanceSorter.sort(queryAnalysisDto.getMeasureMap(), articles);
        return new SearchResultDto.Builder()
                .withArticles(sortedArticles)
                .withQueryAnalysisDto(queryAnalysisDto)
                .build();
    }

    public SearchResultDto search(String queryText, RelevanceSorter relevanceSorter) throws RepositoryException {
        MeasureMap queryMeasureMap = calculateQueryMeasureMap(queryText);

        QueryAnalysisDto queryAnalysisDto = new QueryAnalysisDto.Builder()
                .withQuery(queryText)
                .withMeasureMap(queryMeasureMap)
                .build();
        return search(queryAnalysisDto, relevanceSorter);
    }

    public SearchResultDto search(DecisionSupportQueryDto query, RelevanceSorter relevanceSorter)
            throws RepositoryException {
        MeasureMap queryMeasureMap = calculateQueryMeasureMap(query.getQueryText());

        queryMeasureMap.riseWeights(query.getWeights());

        QueryAnalysisDto queryAnalysisDto = new QueryAnalysisDto.Builder()
                .withQuery(query.getQueryText())
                .withMeasureMap(queryMeasureMap)
                .build();
        return search(queryAnalysisDto, relevanceSorter);

    }

    private MeasureMap calculateQueryMeasureMap(String query) {
        List<String> queryTerms = termAnalyzer.getTerms(query);
        return tfMeasureCalculator.calculate(queryTerms);
    }
}