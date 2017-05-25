package com.joma.studies;

import com.google.inject.Inject;
import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.importance.ArticleImportanceSorter;
import org.apache.log4j.Logger;

import java.util.List;

public class App {

    private static final Logger logger = Logger.getLogger(App.class);

    private final SearchEngine searchEngine;
    private final ArticleImportanceSorter articleImportanceSorter;

    @Inject
    public App(SearchEngine searchEngine, ArticleImportanceSorter articleImportanceSorter) {
        this.searchEngine = searchEngine;
        this.articleImportanceSorter = articleImportanceSorter;
    }

    void run(String[] args) {
        try {
            if (args.length < 1) {
                throw new IllegalArgumentException("Too few arguments");
            }
            String query = args[0];

            List<ArticleDto> result = searchEngine.search(query, articleImportanceSorter);

            result.forEach(System.out::println);

        } catch (Exception exception) {
            logger.fatal(exception.toString());
        }


    }

}
