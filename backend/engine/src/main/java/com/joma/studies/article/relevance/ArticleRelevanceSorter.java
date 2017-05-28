package com.joma.studies.article.relevance;

import com.joma.studies.ArticleWithMeasureMapAndRelevanceDto;
import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.measure.MeasureMap;

import java.util.List;

public interface ArticleRelevanceSorter {
    List<ArticleWithMeasureMapAndRelevanceDto> sort(MeasureMap queryMeasureMap, List<ArticleDto> articles);
}
