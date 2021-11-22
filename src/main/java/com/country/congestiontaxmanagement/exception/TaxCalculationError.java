package com.country.congestiontaxmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TaxCalculationError extends RuntimeException{
    public TaxCalculationError(String exception) {
        super(exception);
    }
}
