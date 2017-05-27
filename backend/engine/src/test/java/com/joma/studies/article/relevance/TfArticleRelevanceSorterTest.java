package com.joma.studies.article.relevance;

import com.joma.studies.ArticleWithRelevanceDto;
import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.measure.MeasureMap;
import com.joma.studies.measure.TermAnalyzer;
import com.joma.studies.measure.TfMeasureCalculator;
import com.joma.studies.query.dto.QueryAnalysisDto;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TfArticleRelevanceSorterTest {

    private TfArticleRelevanceSorter tfSorter;
    private QueryAnalysisDto query;

    @Before
    public void setUp() throws Exception {
        tfSorter = new TfArticleRelevanceSorter(new DocumentDistanceSorter(),
                new TfMeasureCalculator(new TermAnalyzer(new EnglishAnalyzer()))
        );
        MeasureMap queryTermFrequency = new MeasureMap();
        queryTermFrequency.put("cancer", 1.);
        query = new QueryAnalysisDto.Builder()
                .withQuery("cancer")
                .withTermFrequency(queryTermFrequency)
                .build();
    }

    private List<ArticleDto> getArticles(List<ArticleWithRelevanceDto> articlesWithRelevance) {
        return articlesWithRelevance
                .stream()
                .map(ArticleWithRelevanceDto::getArticle)
                .collect(Collectors.toList());
    }

    @Test
    public void whenOneSameDocument_shouldReturnItFirst() throws Exception {
        ArticleDto article = new ArticleDto.Builder()
                .withTitle("cancer")
                .withAbstractText("cancer")
                .build();

        Assert.assertEquals(Arrays.asList(article),
                getArticles(tfSorter.sort(query.getMeasureMap(), Arrays.asList(article)))
        );
    }

    @Test
    public void whenOneDifferentDocument_shouldReturnItFirst() throws Exception {
        ArticleDto article = new ArticleDto.Builder()
                .withTitle("lung")
                .withAbstractText("lung")
                .build();
        List<ArticleDto> articlesSorted = new ArrayList<>();
        articlesSorted.add(article);

        Assert.assertEquals(articlesSorted,
                getArticles(tfSorter.sort(query.getMeasureMap(), Arrays.asList(article)))
        );
    }

    @Test
    public void whenOneSameAndOneDifferentDocument_shouldReturnFirstThenSecond() throws Exception {
        ArticleDto article1 = new ArticleDto.Builder()
                .withTitle("cancer")
                .withAbstractText("cancer")
                .build();
        ArticleDto article2 = new ArticleDto.Builder()
                .withTitle("lung")
                .withAbstractText("lung")
                .build();

        Assert.assertEquals(Arrays.asList(article1, article2),
                getArticles(tfSorter.sort(query.getMeasureMap(), Arrays.asList(article2, article1)))
        );
    }

    @Test
    public void whenOneHalfSameAndOneQuarterSameDocument_shouldReturnFirstThenSecond() throws Exception {
        ArticleDto article1 = new ArticleDto.Builder()
                .withTitle("cancer cancer lung lung")
                .withAbstractText("cancer cancer lung lung")
                .build();
        ArticleDto article2 = new ArticleDto.Builder()
                .withTitle("cancer lung lung lung")
                .withAbstractText("cancer lung lung lung")
                .build();

        Assert.assertEquals(Arrays.asList(article1, article2),
                getArticles(tfSorter.sort(query.getMeasureMap(), Arrays.asList(article2, article1)))
        );
    }
}
