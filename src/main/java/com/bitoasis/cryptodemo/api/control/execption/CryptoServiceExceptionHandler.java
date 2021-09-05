package com.bitoasis.cryptodemo.api.control.execption;

import feign.FeignException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@ControllerAdvice
public class CryptoServiceExceptionHandler extends ResponseEntityExceptionHandler{

	/**
     * Handle exception.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(CryptoServiceException.class)
    public final ResponseEntity<CryptoServiceErrorResponse> handleException(final RestException exception) {
        final RestError restError = exception.getRestError();
        final CryptoServiceErrorResponse errorResponse = CryptoServiceErrorResponse.builder().errorMessage(restError.desceription()).error(restError.error())
                .status(restError.httpStatus().name()).build();
        return new ResponseEntity<CryptoServiceErrorResponse>(errorResponse, restError.httpStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<CryptoServiceErrorResponse> handleAllException(final Exception ex, final WebRequest request) {
        ex.printStackTrace();
        final CryptoServiceError cryptoServiceError =  CryptoServiceError.INTERNAL_SERVER_ERROR;
        final CryptoServiceErrorResponse errorDetails = new CryptoServiceErrorResponse(ex.getMessage(), cryptoServiceError.toString(),
                cryptoServiceError.httpStatus().toString(), LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        return new ResponseEntity<CryptoServiceErrorResponse>(errorDetails, cryptoServiceError.httpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status,
            final WebRequest request) {
        final CryptoServiceError cryptoServiceError = CryptoServiceError.REQUEST_VALIDATION_ERROR;
        final CryptoServiceErrorResponse errorDetails = new CryptoServiceErrorResponse(ex.getMessage(), cryptoServiceError.toString(),
                cryptoServiceError.httpStatus().toString(), LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        return new ResponseEntity<Object>(errorDetails, cryptoServiceError.httpStatus());
    }
    
    
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignException(final FeignException fe) {
    	final CryptoServiceError cryptoServiceError =  CryptoServiceError.SERVICE_UN_AVAILABLE;
        final CryptoServiceErrorResponse errorDetails = new CryptoServiceErrorResponse(fe.getMessage(), cryptoServiceError.toString(),
                cryptoServiceError.httpStatus().toString(), LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        return new ResponseEntity<Object>(errorDetails, cryptoServiceError.httpStatus());
    }
}
