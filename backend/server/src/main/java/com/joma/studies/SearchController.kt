package com.joma.studies

import com.joma.studies.search.ArticleRelevanceSorterFactory
import com.joma.studies.search.query.SearchRequest
import com.joma.studies.search.query.decision.DecisionSupportSearchRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("v1/search")
class SearchController
@Autowired constructor(
        val articleRelevanceSorterFactory: ArticleRelevanceSorterFactory,
        val searchEngine: SearchEngine
) {
    @RequestMapping(method = arrayOf(POST))
    fun searchByQuery(@Valid @RequestBody request: SearchRequest): SearchResultDto {
        val articleImportanceSorter = articleRelevanceSorterFactory
                .getSorter(request.sortingAlgorithm)
        return searchEngine.search(
                request.queryText,
                articleImportanceSorter)
    }

    @RequestMapping(value = "/decision-support", method = arrayOf(POST))
    fun search(@Valid @RequestBody request: DecisionSupportSearchRequest): SearchResultDto {
        val articleImportanceSorter = articleRelevanceSorterFactory
                .getSorter(request.sortingAlgorithm)
        return searchEngine.search(
                request.query.queryText,
                request.query.weights,
                request.query.positiveArticles,
                request.query.negativeArticles,
                articleImportanceSorter)
    }
}

