package br.com.kantar.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import br.com.kantar.modelMessage.ModelMessage;

@ControllerAdvice
public class NullPointerExceptionHandler {

	@ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> inputValidationException(RuntimeException e) {
        
    return new ResponseEntity<Object>(new ModelMessage(HttpStatus.BAD_REQUEST.value(),e.getMessage()), HttpStatus.BAD_REQUEST);
    
    }
	
}
