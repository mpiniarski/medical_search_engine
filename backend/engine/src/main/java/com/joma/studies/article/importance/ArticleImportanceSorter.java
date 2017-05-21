package com.joma.studies.article.importance;

import com.joma.studies.article.dto.ArticleDto;
import com.joma.studies.article.importance.dto.ArticleAnalysisDto;
import com.joma.studies.query.dto.QueryAnalysisDto;

import java.util.List;

public interface ArticleImportanceSorter {
    List<ArticleDto> sort(QueryAnalysisDto query, List<ArticleAnalysisDto> articles);
}
