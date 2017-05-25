package com.joma.studies
import com.joma.studies.query.QueryParser
import com.joma.studies.query.dto.QueryAnalysisDto
import com.joma.studies.search.ArticleImportanceSorterFactory
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
        val articleImportanceSorterFactory: ArticleImportanceSorterFactory,
        val queryParser: QueryParser,
        val searchEngine: SearchEngine
) {
    @RequestMapping(value = "/query", method = arrayOf(POST))
    fun searchByQuery(@Valid @RequestBody request: QuerySearchRequest) : SearchResult {
        val articleImportanceSorter = articleImportanceSorterFactory.getSorter(request.sortingAlgorithm)
        val queryAnalysisDto = QueryAnalysisDto.Builder()
                .withQuery(request.query)
                .withTermFrequency(queryParser.parse(request.query))
                .build()
        val sortedArticles = searchEngine.search(queryAnalysisDto, articleImportanceSorter)
        return SearchResult(queryAnalysisDto, sortedArticles)
    }

    @RequestMapping(value = "/term", method = arrayOf(POST))
    fun search(@Valid @RequestBody request: TermSearchRequest) : SearchResult {
        val articleImportanceSorter = articleImportanceSorterFactory.getSorter(request.sortingAlgorithm)
        val sortedArticles = searchEngine.search(request.query, articleImportanceSorter)
        return SearchResult(request.query, sortedArticles)
    }
}

