package com.joma.studies.search.query

import com.joma.studies.query.dto.QueryAnalysisDto
import com.joma.studies.search.SortingAlgorithm
import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.NotNull


data class TermSearchRequest(
        @field:NotNull
        @field:NotBlank
        val query: QueryAnalysisDto,
        @field:NotNull
        val sortingAlgorithm: SortingAlgorithm)

