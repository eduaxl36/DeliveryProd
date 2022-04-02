package br.com.kantar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kantar.services.JustificativasServices;
import br.com.kantar.shared.justificativas.JustificativasDTO;


@RestController
@RequestMapping("/api/justificativas/")
public class JustificativasController {

	@Autowired
	private JustificativasServices justificativasServices;

	@GetMapping
	public ResponseEntity<List<JustificativasDTO>> obterTodasJustificativas() {

		List<JustificativasDTO> Justificativas = justificativasServices.obterTodasJustificativas();

		return ResponseEntity.ok().body(Justificativas);

	}

	@GetMapping("/{JustificativaId}")
	public ResponseEntity<JustificativasDTO> obterPaisPorId(@PathVariable int JustificativaId) {

		Optional<JustificativasDTO> Justificativa = justificativasServices.obterJustificativaPorId(JustificativaId);

		return Justificativa.isPresent() ? ResponseEntity.ok(Justificativa.get()) : ResponseEntity.notFound().build();

	}



	@PostMapping
	public ResponseEntity<Object> inserirJustificativa(@RequestBody JustificativasDTO Pais){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(justificativasServices.insereJustificativa(Pais));
		
	}
	
	
	@DeleteMapping("/{JustificativaId}")
	public ResponseEntity<Object> deletarJustificativa(@PathVariable int JustificativaId){
		
		return ResponseEntity.ok(justificativasServices.deletarJustificativa(JustificativaId));
		
	}

	
	@PutMapping("/{JustificativaId}")
	public ResponseEntity<Object> atualizarJustificativa(@PathVariable int JustificativaId,@RequestBody JustificativasDTO Justificativa){
		
		return ResponseEntity.ok(justificativasServices.atualizarJustificativa(JustificativaId, Justificativa));
		
	}
	

}
