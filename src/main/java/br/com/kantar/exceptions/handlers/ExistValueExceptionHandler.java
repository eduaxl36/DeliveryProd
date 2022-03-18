/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.exceptions.handlers;

import br.com.kantar.modelMessage.ModelMessage;
import br.com.kantar.exception.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 *
 * @author eduardo
 */
@ControllerAdvice
public class ExistValueExceptionHandler extends ResponseEntityExceptionHandler {
    
     @ExceptionHandler(ExistValueException.class)
     public ResponseEntity<Object> inputValidationException(RuntimeException e) {
         
     return new ResponseEntity<Object>(new ModelMessage(HttpStatus.CONFLICT.value(),e.getMessage()), HttpStatus.UNAUTHORIZED);
     
     }
}
