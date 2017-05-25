package com.joma.studies

import com.joma.studies.article.dto.ArticleDto
import com.joma.studies.query.dto.QueryAnalysisDto

data class SearchResult (
        val query: QueryAnalysisDto,
        val articles : List<ArticleDto>
)