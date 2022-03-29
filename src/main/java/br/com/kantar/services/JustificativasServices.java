package br.com.kantar.services;

import java.util.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kantar.exception.ExistValueException;
import br.com.kantar.model.Justificativas;

import br.com.kantar.repositories.JustificativasRepository;

@Service
public class JustificativasServices {

@Autowired
JustificativasRepository JustificativasRepository;	
	

public List<Justificativas> obterTodasJustificativas(){
	
return JustificativasRepository.findAll();	
	
}
	

public Optional<Justificativas> obterJustificativaPorId(int IdJustificativas){
	
	if(!verificaSeJustificativaExistePorId(IdJustificativas))
		throw new ExistValueException("Justificativas: O Id informado para justificativa não existe, Id: "+IdJustificativas);
	
	return JustificativasRepository.findById(IdJustificativas);
	
}


public Optional<Justificativas> obterJustificativaPorAbreviacao(String JustificativaAbreviacao) {

	if(!verificaSeJustificativaExistePorAbreviacao(JustificativaAbreviacao))
		throw new ExistValueException("Justificativas: A abreviação informada para justificativa não existe, Abrev: "+JustificativaAbreviacao);
	
	return JustificativasRepository.findByAbreviacao(JustificativaAbreviacao);

}


public boolean verificaSeJustificativaExistePorAbreviacao(String JustificativaAbreviacao) {
	
	boolean  validaJustificativa=  JustificativasRepository.findAll()
											   .stream()
											   .anyMatch(x->x.getAbreviacao().equals(JustificativaAbreviacao));
			                    	
	return validaJustificativa;
}



public boolean verificaSeJustificativaExistePorId(int JustificativaId) {
	
	boolean  validaJustificativa=  JustificativasRepository.findAll()
											   .stream()
											   .anyMatch(x->x.getId()==JustificativaId);
			                    	
	return validaJustificativa;
}











}
