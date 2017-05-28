package com.joma.studies.article.relevance.sorter.distance;

import com.joma.studies.article.relevance.dto.RatedAndMeasuredArticleDto;
import com.joma.studies.article.relevance.dto.MeasuredArticleDto;
import com.joma.studies.measure.MeasureMap;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.joma.studies.article.relevance.util.MathUtils.cosineMeasure;

@Component
public class DocumentDistanceSorter {
    private static final Comparator<RatedAndMeasuredArticleDto> descendingArticleComparator =
            (article1, article2) -> Double.compare(article2.getRelevance(), article1.getRelevance());

    public List<RatedAndMeasuredArticleDto> sort(MeasureMap queryMeasureMap, List<MeasuredArticleDto> articles) {
        return articles.stream()
                .map(article -> new RatedAndMeasuredArticleDto.Builder()
                        .withArticle(article.getArticleDto())
                        .withRelevance(cosineMeasure(queryMeasureMap, article.getMeasureMap()))
                        .withMeasureMapFiltered(article.getMeasureMap(), queryMeasureMap.keySet())
                        .build()
                )
                .sorted(descendingArticleComparator)
                .collect(Collectors.toList());
    }
}
