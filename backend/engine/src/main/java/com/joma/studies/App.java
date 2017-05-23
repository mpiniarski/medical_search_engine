package com.joma.studies;

import com.google.inject.Inject;
import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.importance.ArticleImportanceSorter;
import com.joma.studies.article.importance.dto.ArticleAnalysisDto;
import com.joma.studies.article.repository.ArticleRepository;
import com.joma.studies.query.QueryParser;
import com.joma.studies.query.dto.QueryAnalysisDto;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class App {

    private static final Logger logger = Logger.getLogger(App.class);

    private final QueryParser queryParser;
    private final ArticleRepository articleRepository;
    private final ArticleImportanceSorter articleImportanceSorter;

    @Inject
    public App(QueryParser queryParser, ArticleRepository articleRepository, ArticleImportanceSorter articleImportanceSorter) {
        this.queryParser = queryParser;
        this.articleRepository = articleRepository;
        this.articleImportanceSorter = articleImportanceSorter;
    }

    void run(String[] args) {
        try {
            if (args.length < 1) {
                throw new IllegalArgumentException("Too few arguments");
            }
            String query = args[0];

            QueryAnalysisDto queryAnalysisDto = new QueryAnalysisDto.Builder()
                    .withQuery(query)
                    .withTermFrequency(queryParser.parse(query))
                    .build();

            Set<String> queryWords = queryAnalysisDto.getTermFrequency()
                    .keySet();
            List<ArticleAnalysisDto> articles = articleRepository.findByQuery(queryWords)
                    .stream()
                    .map(article -> new ArticleAnalysisDto.Builder()
                            .withArticleDto(article)
                            .withTermFrequency(queryParser.parse(article.getAbstractText()))
                            .build())
                    .collect(Collectors.toList());

            List<ArticleDto> result = articleImportanceSorter.sort(queryAnalysisDto, articles);

            result.forEach(System.out::println);


        } catch (Exception exception) {
            logger.fatal(exception.toString());
        }


    }

}
