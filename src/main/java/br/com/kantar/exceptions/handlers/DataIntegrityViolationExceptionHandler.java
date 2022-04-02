package br.com.kantar.exceptions.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.kantar.modelMessage.ModelMessage;

@ControllerAdvice
public class DataIntegrityViolationExceptionHandler {

	
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException ex) {
        
    return new ResponseEntity<Object>(new ModelMessage(HttpStatus.UNAUTHORIZED.value(),ex.getMostSpecificCause().getMessage()), HttpStatus.UNAUTHORIZED);
    
    }
	
	
}
