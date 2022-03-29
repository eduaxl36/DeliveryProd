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
public class FieldsValidationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	public ResponseEntity<Object> inputValidationException(ConstraintViolationException ExcecaoMensagem) {

		List<String> Motivos = new ArrayList<String>();
		List<String> Campos = new ArrayList<String>();

		ExcecaoMensagem.getConstraintViolations().forEach(x -> {

			x.getPropertyPath().forEach(name -> {

				Motivos.add(x.getMessage());
				Campos.add(name.getName());

			});

		});

		return new ResponseEntity<Object>(
				obterListaCamposInvalidos(Motivos, Campos),
				HttpStatus.BAD_REQUEST);

	}

	public List<ModelExceptionToConstraintViolated> obterListaCamposInvalidos(List<String> Nome,
			List<String> Valor) {

		List<ModelExceptionToConstraintViolated> Excecoes = new ArrayList<ModelExceptionToConstraintViolated>();

		for (int i = 0; i < Nome.size(); i++) {

			Excecoes.add(new ModelExceptionToConstraintViolated(Nome.get(i), Valor.get(i),
					HttpStatus.BAD_REQUEST.value()));

		}

		return Excecoes;

	}

}
