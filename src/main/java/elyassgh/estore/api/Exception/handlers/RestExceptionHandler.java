package elyassgh.estore.api.Exception.handlers;

import elyassgh.estore.api.Exception.classes.ForbiddenException;
import elyassgh.estore.api.Exception.classes.NotFoundException;
import elyassgh.estore.api.Exception.classes.UnauthorizedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // Override exception response behaviour

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    // Other exception handlers below

    // Predefined Exceptions

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleEntityNotAllowed(
            EntityNotFoundException ex) {
        ApiError apiError = new ApiError(METHOD_NOT_ALLOWED);
        apiError.setMessage(ex.getMessage());
        if (apiError.getMessage().equals("")) apiError.setMessage("Access denied, method not allowed");
        return buildResponseEntity(apiError);
    }

    // Defined Exceptions

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<Object> handleUnauthorized(
            UnauthorizedException ex) {
        ApiError apiError = new ApiError(UNAUTHORIZED);
        apiError.setMessage(ex.getMessage());
        System.out.println("here");
        System.out.println(ex.getMessage());
        if(apiError.getMessage().equals("")) apiError.setMessage("Client unauthenticated");
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ForbiddenException.class)
    protected ResponseEntity<Object> handleForbidden(
            ForbiddenException ex) {
        ApiError apiError = new ApiError(FORBIDDEN);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(
            NotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
}