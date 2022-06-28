package com.mah.shop.apotheke.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalError> handleMethodArgumentNotValidException(final MethodArgumentNotValidException methodArgumentNotValidException) {

        final List<String> errors = methodArgumentNotValidException.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        var globalError = GlobalError.builder()
                .errorMessages(errors)
                .timestamp(LocalDate.now())
                .build();

        log.info(String.format("MethodArgumentNotValidException with error: %s", String.join(",", errors)));

        return new ResponseEntity<>(globalError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ExternalRequestFailedException.class)
    public ResponseEntity<GlobalError> handleExternalRequestFailedException(final ExternalRequestFailedException exception) {

        var globalError = GlobalError.builder()
                .errorMessage("Request to external service has failed, please try again later.")
                .timestamp(LocalDate.now())
                .build();

        log.error(String.format("ExternalRequestFailedException with error: %s", exception.getMessage()));

        return new ResponseEntity<>(globalError, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
    }


    @ExceptionHandler(value = RestClientException.class)
    public ResponseEntity<GlobalError> handleRestClientException(final RestClientException exception) {

        var globalError = GlobalError.builder()
                .errorMessage("Request to external service has failed, please try again later.")
                .timestamp(LocalDate.now())
                .build();

        log.error(String.format("RestClientException with error: %s", exception.getMessage()));

        return new ResponseEntity<>(globalError, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<GlobalError> handleRuntimeException(final RuntimeException exception) {

        var globalError = GlobalError.builder()
                .errorMessage("Something went wrong, please try again later.")
                .timestamp(LocalDate.now())
                .build();

        log.error(String.format("RuntimeException with error: %s", exception.getMessage()));

        return new ResponseEntity<>(globalError, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
