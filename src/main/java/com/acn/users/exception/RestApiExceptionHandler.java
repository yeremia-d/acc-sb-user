package com.acn.users.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    // Handles exceptions when no entity with a parameters can be found.
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleElementNotFound(NoSuchElementException ex) {
        ApiError err = new ApiError();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.NOT_FOUND);
        err.setMessage(ex.getLocalizedMessage());

        return new ResponseEntity<Object>(err, HttpStatus.NOT_FOUND);
    }

    // Handles when an operation is performed on an entity and the entity cannot be found
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ApiError err = new ApiError();
        err.setTimestamp(LocalDateTime.now());
        err.setStatus(HttpStatus.BAD_REQUEST);
        err.setMessage(ex.getLocalizedMessage());

        return new ResponseEntity<Object>(err, HttpStatus.BAD_REQUEST);
    }
}
