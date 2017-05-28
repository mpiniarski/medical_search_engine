package com.joma.studies.search

import com.joma.studies.article.relevance.sorter.*
import com.joma.studies.search.SortingAlgorithm.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ArticleRelevanceSorterFactory
@Autowired constructor(
        tfArticleRelevanceSorter: TfRelevanceSorter,
        tfBoostTitleArticleRelevanceSorter: TfBoostTitleRelevanceSorter,
        tfIdfArticleRelevanceSorter: TfIdfRelevanceSorter,
        tfSquaredArticleRelevanceSorter: TfSquaredRelevanceSorter
) {
    private val map: Map<SortingAlgorithm, RelevanceSorter> = hashMapOf(
            TF to tfArticleRelevanceSorter,
            TF_BOOST_TITLE to tfBoostTitleArticleRelevanceSorter,
            TF_IDF to tfIdfArticleRelevanceSorter,
            TF_SQUARED to tfSquaredArticleRelevanceSorter
    )

    fun getSorter(sortingAlgorithm: SortingAlgorithm): RelevanceSorter {
        return map[sortingAlgorithm] ?: throw IllegalArgumentException("Unknown sorting algorithm")
    }
}
