package br.com.kantar.exceptions.handlers;

import br.com.kantar.modelMessage.ModelExceptionToConstraintViolated;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolationException;
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
public class FieldsValidationExceptionHandler extends ResponseEntityExceptionHandler   {

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<Object> inputValidationException(ConstraintViolationException e) {


   


                    List <String>InternalControlsReasonExceptions = new ArrayList();
                    List <String>InternalControlNameExceptions = new ArrayList();



                    e.getConstraintViolations().forEach(x->{


                        
                    x.getPropertyPath().forEach(name->{
                    
                        
                        
                        
                         InternalControlsReasonExceptions.add(x.getMessage());
                         InternalControlNameExceptions.add(name.getName());
                    
                    
                    
                    });
   
                    
                    
                    });



         
        
   
    	
    return new ResponseEntity<Object>(obterListaCamposInvalidos(InternalControlNameExceptions,InternalControlsReasonExceptions), HttpStatus.BAD_REQUEST);

    }
    
    
   
    public List<ModelExceptionToConstraintViolated> obterListaCamposInvalidos(List<String>p_nome,List<String>p_valor){
    
        List<ModelExceptionToConstraintViolated>InternalControlList = new ArrayList();
        

        for(int i=0;i<p_nome.size();i++)
        {
        
        InternalControlList.add(new ModelExceptionToConstraintViolated(p_nome.get(i),p_valor.get(i),HttpStatus.BAD_REQUEST.value()));
        
        
        }
        
     return InternalControlList;   
        
    }
    
    
    
}
