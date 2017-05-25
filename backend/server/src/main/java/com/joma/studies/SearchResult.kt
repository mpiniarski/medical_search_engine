package com.joma.studies

import com.joma.studies.article.dto.ArticleDto

data class SearchResult (
        val articles : List<ArticleDto>
//        val queryTermFrequency : Map<String, Int>
)