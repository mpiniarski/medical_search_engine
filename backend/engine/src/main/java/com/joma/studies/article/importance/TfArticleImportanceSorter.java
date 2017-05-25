package com.joma.studies.article.importance;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.importance.dto.ArticleAnalysisDto;
import com.joma.studies.query.dto.QueryAnalysisDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.joma.studies.article.importance.MathUtils.angle;

@Component
public class TfArticleImportanceSorter implements ArticleImportanceSorter {
    @Override
    public List<ArticleDto> sort(QueryAnalysisDto query, List<ArticleAnalysisDto> articles) {

        return articles.stream()
                .sorted(Comparator.comparingDouble(
                        article -> angle(query.getTermFrequency(), article.getTermFrequency()))
                )
                .map(ArticleAnalysisDto::getArticleDto)
                .collect(Collectors.toList());

    }
}
