package com.joma.studies.article.relevance;

import com.joma.studies.ArticleWithRelevanceDto;
import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.measure.MeasureMap;

import java.util.List;

public interface ArticleRelevanceSorter {
    List<ArticleWithRelevanceDto> sort(MeasureMap queryMeasureMap, List<ArticleDto> articles);
}
