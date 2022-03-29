package br.com.kantar.controller;

import java.util.*;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.kantar.model.Justificativas;
import br.com.kantar.services.JustificativasServices;

@RestController
@RequestMapping("/api/justificativas/")
public class JustificativasController {


	@Autowired
	private JustificativasServices justificativasServices;
	
	@GetMapping
	public ResponseEntity<List<Justificativas>> obterTodasJustificativas(){  

	List<Justificativas> list = justificativasServices.obterTodasJustificativas();
	 
	return ResponseEntity.ok().body(list);

	}
	
	
	@GetMapping("/{JustificativaId}")
	public ResponseEntity<Optional<Justificativas>> obterJustificativaPorId(@PathVariable int JustificativaId){  

	Optional<Justificativas> Justificativa = justificativasServices.obterJustificativaPorId(JustificativaId);
	 
	return ResponseEntity.ok().body(Justificativa);

	}
	
	
	@GetMapping("/ObterPorAbreviacao/{JustificativaAbreviacao}")
	public ResponseEntity<Optional<Justificativas>> obterJustificativaPorNome(@PathVariable String JustificativaAbreviacao){  

	Optional<Justificativas> Justificativa = justificativasServices.obterJustificativaPorAbreviacao(JustificativaAbreviacao);
	 
	return ResponseEntity.ok().body(Justificativa);

	}
	
	
	
}
