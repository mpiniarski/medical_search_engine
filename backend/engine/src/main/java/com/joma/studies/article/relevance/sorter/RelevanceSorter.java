package com.joma.studies.article.relevance.sorter;

import com.joma.studies.article.relevance.dto.RatedAndMeasuredArticleDto;
import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.measure.MeasureMap;

import java.util.List;

public interface RelevanceSorter {
    List<RatedAndMeasuredArticleDto> sort(MeasureMap queryMeasureMap, List<ArticleDto> articles);
}
