package com.joma.studies;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.importance.ArticleImportanceSorter;
import com.joma.studies.article.importance.dto.ArticleAnalysisDto;
import com.joma.studies.article.repository.ArticleRepository;
import com.joma.studies.article.repository.exception.RepositoryException;
import com.joma.studies.query.QueryParser;
import com.joma.studies.query.dto.QueryAnalysisDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SearchEngine {

    private final QueryParser queryParser;
    private final ArticleRepository articleRepository;

    @Autowired
    public SearchEngine(QueryParser queryParser, ArticleRepository articleRepository) {
        this.queryParser = queryParser;
        this.articleRepository = articleRepository;
    }

    public List<ArticleDto> search(QueryAnalysisDto queryAnalysisDto, ArticleImportanceSorter articleImportanceSorter) throws RepositoryException {
        Set<String> queryWords = queryAnalysisDto.getTermFrequency()
                .keySet();
        List<ArticleAnalysisDto> articles = articleRepository.findByQuery(queryWords)
                .stream()
                .map(article -> new ArticleAnalysisDto.Builder()
                        .withArticleDto(article)
                        .withTermFrequency(queryParser.parse(article.getAbstractText()))
                        .build())
                .collect(Collectors.toList());

        return articleImportanceSorter.sort(queryAnalysisDto, articles);
    }

    public List<ArticleDto> search(String query, ArticleImportanceSorter articleImportanceSorter) throws RepositoryException {
        QueryAnalysisDto queryAnalysisDto = new QueryAnalysisDto.Builder()
                .withQuery(query)
                .withTermFrequency(queryParser.parse(query))
                .build();
        return search(queryAnalysisDto, articleImportanceSorter);
    }
}