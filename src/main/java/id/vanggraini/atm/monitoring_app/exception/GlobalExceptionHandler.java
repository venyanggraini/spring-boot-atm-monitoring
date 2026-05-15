package id.vanggraini.atm.monitoring_app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import tools.jackson.databind.exc.InvalidFormatException;

import id.vanggraini.atm.monitoring_app.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ATMStatusNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleATMNotFound(ATMStatusNotFoundException ex) {
        ErrorResponse response = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEnum(HttpMessageNotReadableException ex) {

        String message = "Invalid request body";

        Throwable cause = ex.getMostSpecificCause();

        if (cause instanceof InvalidFormatException ife) {
            String fieldName = ife.getPath().get(0).getPropertyName();
            String invalidValue = ife.getValue().toString();

            message = String.format(
                "Invalid value '%s' for field '%s'", invalidValue, fieldName
            );
        }

        ErrorResponse response = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(), message, LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
