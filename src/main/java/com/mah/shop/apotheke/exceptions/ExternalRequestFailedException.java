package com.mah.shop.apotheke.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;

public class ExternalRequestFailedException extends RestClientException {

    private static final String REQUEST_TO_EXTERNAL_SERVICE_HAS_FAILED = "Request to external service has failed with HttpStatus code %s, and errorMessage: %s";

    public ExternalRequestFailedException(HttpStatus httpStatus, String errorMessage) {

        super(String.format(REQUEST_TO_EXTERNAL_SERVICE_HAS_FAILED, httpStatus, errorMessage));
    }
}
