package com.joma.studies.search.query

import com.joma.studies.search.SortingAlgorithm
import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.NotNull


data class QuerySearchRequest(
        @field:NotNull
        @field:NotBlank
        val query: String,
        @field:NotNull
        val sortingAlgorithm: SortingAlgorithm)

