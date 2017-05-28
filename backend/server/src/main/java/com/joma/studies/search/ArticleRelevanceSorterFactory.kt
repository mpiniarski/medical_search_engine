package com.joma.studies.search

import com.joma.studies.article.relevance.ArticleRelevanceSorter
import com.joma.studies.article.relevance.TfArticleRelevanceSorter
import com.joma.studies.article.relevance.TfIdfArticleRelevanceSorter
import com.joma.studies.article.relevance.TfImportantTitleArticleRelevanceSorter
import com.joma.studies.search.SortingAlgorithm.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ArticleRelevanceSorterFactory
@Autowired constructor(
        tfArticleRelevanceSorter: TfArticleRelevanceSorter,
        tfImportantTitleArticleRelevanceSorter: TfImportantTitleArticleRelevanceSorter,
        tfIdfArticleRelevanceSorter: TfIdfArticleRelevanceSorter
) {
    private val map: Map<SortingAlgorithm, ArticleRelevanceSorter> = hashMapOf(
            TF to tfArticleRelevanceSorter,
            TF_IMPORTANT_TITLE to tfImportantTitleArticleRelevanceSorter,
            TF_IDF to tfIdfArticleRelevanceSorter
    )

    fun getSorter(sortingAlgorithm: SortingAlgorithm): ArticleRelevanceSorter {
        return map[sortingAlgorithm] ?: throw IllegalArgumentException("Unknown sorting algorithm")
    }
}
