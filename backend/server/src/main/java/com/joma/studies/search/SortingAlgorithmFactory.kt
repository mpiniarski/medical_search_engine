package com.joma.studies.search

import com.joma.studies.article.importance.ArticleImportanceSorter
import com.joma.studies.article.importance.TfArticleImportanceSorter
import com.joma.studies.search.SortingAlgorithm.TF
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SortingAlgorithmFactory
@Autowired constructor(
        tfArticleImportanceSorter: TfArticleImportanceSorter
) {
    private val map: Map<SortingAlgorithm, ArticleImportanceSorter> = hashMapOf(
            TF to tfArticleImportanceSorter
    )

    fun getArticleImportanceSorter(sortingAlgorithm: SortingAlgorithm): ArticleImportanceSorter {
        return map[sortingAlgorithm] ?: throw IllegalArgumentException("Unknown sorting algorithm")
    }
}