package com.joma.studies
import com.joma.studies.measure.TfMeasureCalculator
import com.joma.studies.search.ArticleRelevanceSorterFactory
import com.joma.studies.search.query.QuerySearchRequest
import com.joma.studies.search.query.TermSearchRequest
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
        val measureCalculator: TfMeasureCalculator,
        val searchEngine: SearchEngine
) {
    @RequestMapping(value = "/query", method = arrayOf(POST))
    fun searchByQuery(@Valid @RequestBody request: QuerySearchRequest) : SearchResultDto {
        val articleImportanceSorter = articleRelevanceSorterFactory.getSorter(request.sortingAlgorithm)
        return searchEngine.search(request.query, articleImportanceSorter)
    }

    @RequestMapping(value = "/term", method = arrayOf(POST))
    fun search(@Valid @RequestBody request: TermSearchRequest) : SearchResultDto {
        val articleImportanceSorter = articleRelevanceSorterFactory.getSorter(request.sortingAlgorithm)
        return searchEngine.search(request.query, articleImportanceSorter)
    }
}

