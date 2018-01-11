package com.thoughtworks.mstorderservice.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InvalidCredentialException.class)
    public void handleHackerException() {
        log.error("Invalid credential.");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({BadCredentialsException.class})
    public void handleBadCredentialsException(BadCredentialsException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(value = {MstException.class})
    public ResponseEntity handleCustomizedRuntimeException(MstException e) throws Exception {
        return ResponseEntity.status(e.getHttpStatus())
                             .body(new ErrorResult(e.getCode(), e.getMessage()));
    }
}

