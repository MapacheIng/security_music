package com.spacecodee.spring_security_music_p.exceptions;

import com.spacecodee.spring_security_music_p.data.pojo.ApiErrorDataPojo;
import com.spacecodee.spring_security_music_p.data.pojo.ApiErrorPojo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ApiErrorPojo apiErrorPojo = new ApiErrorPojo();

    private final ApiErrorDataPojo<List<String>> apiErrorDataPojo = new ApiErrorDataPojo<>();


    @ExceptionHandler(NoDisabledException.class)
    public ResponseEntity<ApiErrorPojo> noDisabledHException(
            NoDisabledException exception,
            HttpServletRequest request
    ) {
        return this.getApiErrorPojoResponseEntity(request, exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoUpdatedException.class)
    public ResponseEntity<ApiErrorPojo> noUpdatedHException(
            NoUpdatedException exception,
            HttpServletRequest request
    ) {
        return this.getApiErrorPojoResponseEntity(request, exception, HttpStatus.NOT_MODIFIED);
    }

    @ExceptionHandler(NoCreatedException.class)
    public ResponseEntity<ApiErrorPojo> noCreatedHException(
            NoCreatedException exception,
            HttpServletRequest request
    ) {
        return this.getApiErrorPojoResponseEntity(request, exception, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ApiErrorPojo> noContent(
            NoContentException exception,
            HttpServletRequest request
    ) {
        return this.getApiErrorPojoResponseEntity(request, exception, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(CannotSaveUpdateException.class)
    public ResponseEntity<ApiErrorPojo> cannotSaveUpdateException(
            CannotSaveUpdateException exception,
            HttpServletRequest request
    ) {
        return this.getApiErrorPojoResponseEntity(request, exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiErrorPojo> invalidPasswordException(
            InvalidPasswordException exception,
            HttpServletRequest request
    ) {
        return this.getApiErrorPojoResponseEntity(request, exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ApiErrorPojo> objectNotFoundException(
            ObjectNotFoundException exception,
            HttpServletRequest request
    ) {
        return this.getApiErrorPojoResponseEntity(request, exception, HttpStatus.NOT_FOUND);
    }

    @NotNull
    private ResponseEntity<ApiErrorPojo> getApiErrorPojoResponseEntity(
            HttpServletRequest request,
            RuntimeException exception,
            HttpStatus httpStatus
    ) {
        this.apiErrorPojo.setBackendMessage(exception.getLocalizedMessage());
        this.apiErrorPojo.setMessage("An error occurred while processing your request");
        this.apiErrorPojo.setTimestamp(LocalDate.now());
        this.apiErrorPojo.setPath(request.getRequestURI());
        this.apiErrorPojo.setMethod(request.getMethod());
        return ResponseEntity.status(httpStatus).body(this.apiErrorPojo);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorPojo> handlerGenericException(
            Exception exception,
            HttpServletRequest request
    ) {
        this.apiErrorPojo.setBackendMessage(exception.getLocalizedMessage());
        this.apiErrorPojo.setMessage("An error occurred while processing your request");
        this.apiErrorPojo.setTimestamp(LocalDate.now());
        this.apiErrorPojo.setPath(request.getRequestURI());
        this.apiErrorPojo.setMethod(request.getMethod());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(this.apiErrorPojo);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorPojo> handlerAccessDeniedException(
            AccessDeniedException exception,
            HttpServletRequest request
    ) {
        this.apiErrorPojo.setBackendMessage(exception.getLocalizedMessage());
        this.apiErrorPojo.setMessage("Access denied, you don't have permission to access this resource, " +
                "please contact the administrator for more information");
        this.apiErrorPojo.setTimestamp(LocalDate.now());
        this.apiErrorPojo.setPath(request.getRequestURI());
        this.apiErrorPojo.setMethod(request.getMethod());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(this.apiErrorPojo);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDataPojo<List<String>>> handlerMethodArgumentNotValidException(
            HttpServletRequest request,
            MethodArgumentNotValidException exception
    ) {
        List<String> errors = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        this.apiErrorDataPojo.setBackendMessage(exception.getLocalizedMessage());
        this.apiErrorDataPojo.setMessage("An error while we were trying to send your request");
        this.apiErrorDataPojo.setTimestamp(LocalDate.now());
        this.apiErrorDataPojo.setPath(request.getRequestURI());
        this.apiErrorDataPojo.setMethod(request.getMethod());
        this.apiErrorDataPojo.setData(errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.apiErrorDataPojo);
    }

}
