package com.joma.studies
import com.google.inject.Inject
import com.joma.studies.search.SortingAlgorithm
import com.joma.studies.search.SortingAlgorithmDispatcher
import com.joma.studies.search.query.QuerySearchRequest
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("v1/search")
class SearchController
@Inject constructor(
        val sortingAlgorithmDispatcher: SortingAlgorithmDispatcher,
        val searchEngine: SearchEngine
) {
    @RequestMapping(method = arrayOf(POST))
    fun search(@Valid @RequestBody request: QuerySearchRequest) : SearchResult {
        val articleImportanceSorter = sortingAlgorithmDispatcher.getArticleImportanceSorter(request.sortingAlgorithm ?: SortingAlgorithm.TF)
        val sortedArticles = searchEngine.search(request.query, articleImportanceSorter)
        return SearchResult(sortedArticles)
    }
}

