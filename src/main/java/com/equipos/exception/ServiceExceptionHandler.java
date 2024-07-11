package com.equipos.exception;

import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger SERVICE_EXCEPTION_HANDLER_LOGGER = LoggerFactory.getLogger(ServiceExceptionHandler.class);

    /**
     * Handle not found exceptions
     *
     * @param ex      the {@link NotFoundException} exception
     * @param request the {@link WebRequest}
     * @return the {@link ErrorDetails} with the error information
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleNotFoundException(final NotFoundException ex, final WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setCode(HttpStatus.NOT_FOUND.value());
        errorDetails.setMessage(ex.getMessage());
        SERVICE_EXCEPTION_HANDLER_LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    /**
     * Handle generic {@link ServiceException}
     *
     * @param ex      the {@link ServiceException} exception
     * @param request the {@link WebRequest}
     * @return the {@link ErrorDetails} with the error information
     */
    @ExceptionHandler(ServiceException.class)
    public final ResponseEntity<ErrorDetails> handleServiceException(final ServiceException ex, final WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setCode(ex.getCode().value());
        errorDetails.setMessage(ex.getMessage());
        SERVICE_EXCEPTION_HANDLER_LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(errorDetails, ex.getCode());
    }

    /**
     * Handle constraint violations errors
     *
     * @param ex      the {@link ServiceException} exception
     * @param request the {@link WebRequest}
     * @return the {@link ErrorDetails} with the error information
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ErrorDetails> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

        List<String> errorList = ex.getConstraintViolations()
                .parallelStream()
                .map(e -> e.getPropertyPath().toString() + " " + e.getMessage())
                .toList();

        final ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setMessage(String.join(" - ", errorList));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @Override
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        final ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setMessage("La solicitud es inválida");
        errorDetails.setDetails(Map.of(String.join(" - ", errorList), ex));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected final ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setCode(HttpStatus.BAD_REQUEST.value());
        errorDetails.setMessage("La solicitud es inválida");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    /**
     * Handle internal authentication exceptions
     *
     * @param ex      the {@link InternalAuthenticationServiceException} exception
     * @param request the {@link WebRequest}
     * @return the {@link ErrorDetails} with the error information
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public final ResponseEntity<ErrorDetails> handleInternalAuth(InternalAuthenticationServiceException ex, WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setCode(HttpStatus.UNAUTHORIZED.value());
        errorDetails.setMessage(ex.getMessage());
        SERVICE_EXCEPTION_HANDLER_LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }


    /**
     * Handle bad credentials exceptions
     *
     * @param ex      the {@link BadCredentialsException} exception
     * @param request the {@link WebRequest}
     * @return the {@link ErrorDetails} with the error information
     */
    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ErrorDetails> handleBadCredentials(BadCredentialsException ex, WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setCode(HttpStatus.UNAUTHORIZED.value());
        errorDetails.setMessage("Credenciales inválidas");
        SERVICE_EXCEPTION_HANDLER_LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        final ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.setMessage(ex.getMessage());
        SERVICE_EXCEPTION_HANDLER_LOGGER.error(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
