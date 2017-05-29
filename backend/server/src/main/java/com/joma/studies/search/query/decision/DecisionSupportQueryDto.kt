package com.joma.studies.search.query.decision

import com.joma.studies.measure.MeasureMap
import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.NotNull

data class DecisionSupportQueryDto(
        @NotNull
        @NotBlank
        val queryText: String,

        @NotNull
        val weights: Map<String, Int>,

        @NotNull
        val positiveArticles: List<MeasureMap>,

        @NotNull
        val negativeArticles: List<MeasureMap>
)
