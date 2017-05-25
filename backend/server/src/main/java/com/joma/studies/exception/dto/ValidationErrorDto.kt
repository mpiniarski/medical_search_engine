package com.joma.studies.exception.dto


data class ValidationErrorDto(
        val field : String? = null,
        val errorMessage : String
)