package com.joma.studies.article.importance;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.importance.dto.ArticleAnalysisDto;
import com.joma.studies.query.dto.QueryAnalysisDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class TfArticleImportanceSorterTest {

    private TfArticleImportanceSorter tfSorter;
    private QueryAnalysisDto query;

    @Before
    public void setUp() throws Exception {
        tfSorter = new TfArticleImportanceSorter();
        Map<String, Integer> queryTermFrequency = new HashMap<>();
        queryTermFrequency.put("cancer", 1);
        query = new QueryAnalysisDto.Builder()
                .withQuery("cancer")
                .withTermFrequency(queryTermFrequency)
                .build();
    }

    @Test
    public void whenOneSameDocument_shouldReturnItFirst() throws Exception {
        ArticleDto article = new ArticleDto.Builder()
                .withTitle("cancer")
                .withAbstractText("cancer")
                .build();
        Map<String, Integer> articleTermFrequency = new HashMap<>();
        articleTermFrequency.put("cancer", 1);

        List<ArticleAnalysisDto> articles = new ArrayList<>();
        articles.add(new ArticleAnalysisDto.Builder()
                .withArticleDto(article)
                .withTermFrequency(articleTermFrequency)
                .build());

        List<ArticleDto> articlesSorted = new ArrayList<>();
        articlesSorted.add(article);

        Assert.assertEquals(articlesSorted, tfSorter.sort(query, articles));
    }

    @Test
    public void whenOneDifferentDocument_shouldReturnItFirst() throws Exception {
        ArticleDto article = new ArticleDto.Builder()
                .withTitle("lung")
                .withAbstractText("lung")
                .build();
        Map<String, Integer> articleTermFrequency = new HashMap<>();
        articleTermFrequency.put("lung", 1);

        List<ArticleAnalysisDto> articles = new ArrayList<>();
        articles.add(new ArticleAnalysisDto.Builder()
                .withArticleDto(article)
                .withTermFrequency(articleTermFrequency)
                .build());

        List<ArticleDto> articlesSorted = new ArrayList<>();
        articlesSorted.add(article);

        Assert.assertEquals(articlesSorted, tfSorter.sort(query, articles));
    }

    @Test
    public void whenOneSameAndOneDifferentDocument_shouldReturnFirstThenSecond() throws Exception {
        ArticleDto article1 = new ArticleDto.Builder()
                .withTitle("cancer")
                .withAbstractText("cancer")
                .build();
        Map<String, Integer> article1TermFrequency = new HashMap<>();
        article1TermFrequency.put("cancer", 1);
        ArticleDto article2 = new ArticleDto.Builder()
                .withTitle("lung")
                .withAbstractText("lung")
                .build();
        Map<String, Integer> article2TermFrequency = new HashMap<>();
        article2TermFrequency.put("lung", 1);

        List<ArticleAnalysisDto> articles = new ArrayList<>();
        articles.add(new ArticleAnalysisDto.Builder()
                .withArticleDto(article1)
                .withTermFrequency(article1TermFrequency)
                .build());
        articles.add(new ArticleAnalysisDto.Builder()
                .withArticleDto(article2)
                .withTermFrequency(article2TermFrequency)
                .build());

        List<ArticleDto> articlesSorted = new ArrayList<>();
        articlesSorted.add(article1);
        articlesSorted.add(article2);

        Assert.assertEquals(articlesSorted, tfSorter.sort(query, articles));
    }

    @Test
    public void whenOneHalfSameAndOneQuarterSameDocument_shouldReturnFirstThenSecond() throws Exception {
        ArticleDto article1 = new ArticleDto.Builder()
                .withTitle("cancer cancer lung lung")
                .withAbstractText("cancer cancer lung lung")
                .build();
        Map<String, Integer> article1TermFrequency = new HashMap<>();
        article1TermFrequency.put("cancer", 2);
        article1TermFrequency.put("lung", 2);
        ArticleDto article2 = new ArticleDto.Builder()
                .withTitle("cancer lung lung lung")
                .withAbstractText("cancer lung lung lung")
                .build();
        Map<String, Integer> article2TermFrequency = new HashMap<>();
        article2TermFrequency.put("cancer", 1);
        article2TermFrequency.put("lung", 3);

        List<ArticleAnalysisDto> articles = new ArrayList<>();
        articles.add(new ArticleAnalysisDto.Builder()
                .withArticleDto(article1)
                .withTermFrequency(article1TermFrequency)
                .build());
        articles.add(new ArticleAnalysisDto.Builder()
                .withArticleDto(article2)
                .withTermFrequency(article2TermFrequency)
                .build());

        List<ArticleDto> articlesSorted = new ArrayList<>();
        articlesSorted.add(article1);
        articlesSorted.add(article2);

        Assert.assertEquals(articlesSorted, tfSorter.sort(query, articles));
    }
}
