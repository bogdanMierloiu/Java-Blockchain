package ro.bogdan_mierloiu.javablockchain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ro.bogdan_mierloiu.javablockchain.exception.InvalidBlockChainException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidBlockChainException.class)
    ResponseEntity<String> handleInvalidBlockChainException(InvalidBlockChainException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
