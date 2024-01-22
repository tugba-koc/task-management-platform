package dev.tugba.taskapp.core.utilities.exceptions;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "Validation.Exception"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AlreadyExistsUserException.class)
    public ResponseEntity<Map<String, Object>> handleAlreadyExistsUserWithSameTurkishId(AlreadyExistsUserException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "Validation.Exception"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleTaskNotFound(TaskNotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "Runtime.Exception"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UserNotFoundException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "Runtime.Exception"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AuthenticationServiceException.class)
    public ResponseEntity<Map<String, Object>> handleAuthenticationServiceException(AuthenticationServiceException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "Authentication.Exception"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<Map<String, Object>> handleGeneralExceptions(Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "General.Exception"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, Object>> handleRuntimeExceptions(RuntimeException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<Map<String, Object>>(getErrorsMap(errors, "Runtime.Exception"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> getErrorsMap(List<String> errors, String message) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("success", message != null ? false : true);
        errorResponse.put("errors", errors);
        errorResponse.put("message", message);
        errorResponse.put("date", LocalDateTime.now());
        return errorResponse;
    }
}