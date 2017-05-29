package com.joma.studies.search.query

import com.joma.studies.DecisionSupportQueryDto
import com.joma.studies.search.SortingAlgorithm
import javax.validation.constraints.NotNull


data class DecisionSupportSearchRequest(
        @field:NotNull
        val query: DecisionSupportQueryDto,

        @field:NotNull
        val sortingAlgorithm: SortingAlgorithm
)

