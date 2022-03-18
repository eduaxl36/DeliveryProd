/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.exceptions.handlers;

import br.com.kantar.modelMessage.ModelMessage;
import br.com.kantar.exception.NotFoundException;

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
public class NotFoundExceptionHandler extends ResponseEntityExceptionHandler {
    
     @ExceptionHandler(NotFoundException.class)
     public ResponseEntity<Object> inputValidationException(RuntimeException e) {
         
     return new ResponseEntity<Object>(new ModelMessage(HttpStatus.NOT_FOUND.value(),e.getMessage()), HttpStatus.NOT_FOUND);
     
     }
}
