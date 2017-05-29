package com.joma.studies.search.query.decision


data class DecisionSupportSearchRequest(
        @field:javax.validation.constraints.NotNull
        val query: DecisionSupportQueryDto,

        @field:javax.validation.constraints.NotNull
        val sortingAlgorithm: com.joma.studies.search.SortingAlgorithm
)

