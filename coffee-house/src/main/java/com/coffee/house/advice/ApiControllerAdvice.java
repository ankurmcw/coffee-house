package com.coffee.house.advice;

import com.coffee.house.exception.BadRequestException;
import com.coffee.house.exception.ExceptionResponse;
import com.coffee.house.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler({BadRequestException.class, ResourceNotFoundException.class})
    public ResponseEntity<ExceptionResponse> resourceNotFound(Exception ex) {
        ExceptionResponse response = new ExceptionResponse();
        HttpStatus status;
        if (ex instanceof BadRequestException)
            status = HttpStatus.BAD_REQUEST;

        else if (ex instanceof ResourceNotFoundException)
            status = HttpStatus.NOT_FOUND;

        else
            status = HttpStatus.INTERNAL_SERVER_ERROR;

        response.setErrorCode(status.toString());
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(response, status);
    }
}
