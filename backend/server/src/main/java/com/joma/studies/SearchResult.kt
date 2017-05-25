package com.joma.studies

import com.joma.studies.article.dto.ArticleDto

data class SearchResult (
        val queryTermFrequency : Map<String, Int>,
        val articles : List<ArticleDto>
)