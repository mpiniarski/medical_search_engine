package com.joma.studies.exception

import com.joma.studies.exception.dto.ValidationErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.stream.Collectors


@ControllerAdvice
class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseBody
    fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException): List<ValidationErrorDto> {
        return arrayListOf(
                ValidationErrorDto(errorMessage = exception.localizedMessage.substringBefore("\n"))
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseBody
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): List<ValidationErrorDto> {
        return exception.bindingResult
                .fieldErrors
                .stream()
                .map { error -> ValidationErrorDto(error.field, error.defaultMessage) }
                .collect(Collectors.toList())
    }

}