package com.joma.studies.article.relevance;

import com.joma.studies.ArticleWithRelevanceDto;
import com.joma.studies.article.relevance.dto.ArticleAnalysisDto;
import com.joma.studies.measure.MeasureMap;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.joma.studies.article.relevance.MathUtils.cosineMeasure;

@Component
public class DocumentDistanceSorter {
    private static final Comparator<ArticleWithRelevanceDto> descendingArticleComparator =
            (article1, article2) -> Double.compare(article2.getRelevance(), article1.getRelevance());

    public List<ArticleWithRelevanceDto> sort(MeasureMap queryMeasureMap, List<ArticleAnalysisDto> articles) {
        return articles.stream()
                .map(article -> new ArticleWithRelevanceDto.Builder()
                        .withArticle(article.getArticleDto())
                        .withRelevance(cosineMeasure(queryMeasureMap, article.getMeasureMap()))
                        .build()
                )
                .sorted(descendingArticleComparator)
                .collect(Collectors.toList());
    }
}
