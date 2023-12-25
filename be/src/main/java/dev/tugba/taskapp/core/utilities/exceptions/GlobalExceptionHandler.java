package dev.tugba.taskapp.core.utilities.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // TODO: create enum to show statusCode

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        // TODO: ASK statuscode is right?
        return new ResponseEntity<>(getErrorsMap(errors, "Validation.Exception", 422), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsUserException.class)
    public ResponseEntity<Map<String, Object>> handleAlreadyExistsUserWithSameTurkishId(AlreadyExistsUserException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors, "Validation.Exception", 422), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<Map<String, Object>> handleGeneralExceptions(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        // TODO: ASK statuscode is right?
        return new ResponseEntity<>(getErrorsMap(errors, "General.Exception", 500), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, Object>> handleRuntimeExceptions(RuntimeException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        // TODO: ASK statuscode is right?
        return new ResponseEntity<>(getErrorsMap(errors, "Runtime.Exception", 500), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> getErrorsMap(List<String> errors, String message, int statusCode) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("success", message != null ? false : true);
        errorResponse.put("errors", errors);
        errorResponse.put("message", message);
        errorResponse.put("date", LocalDateTime.now());
        errorResponse.put("statusCode", statusCode);
        return errorResponse;
    }
}