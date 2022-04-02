package br.com.kantar.controller;

import java.util.*;
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
import br.com.kantar.services.PaisesServices;
import br.com.kantar.shared.paises.PaisesDTO;

@RestController
@RequestMapping("/api/paises")
public class PaisesController {
    
@Autowired
private PaisesServices PaisService;
 	

@GetMapping
public ResponseEntity<List<PaisesDTO>> obterListaTodosObjetosPaises(){  

List<PaisesDTO> PaisesDTO = PaisService.obterTodosPaises();
 
return ResponseEntity.ok().body(PaisesDTO);

}

@GetMapping("/{PaisCodigo}")
public ResponseEntity<PaisesDTO> obterPaisPorId(@PathVariable int PaisCodigo){
	
	Optional<PaisesDTO> paisretorno = 	PaisService.obterPaisPorId(PaisCodigo);
	
	return paisretorno.isPresent()?ResponseEntity.ok(paisretorno.get()):ResponseEntity.notFound().build();
	
}

@GetMapping("buscaNome/{PaisNome}")
public ResponseEntity<PaisesDTO> obterPaisPorNome(@PathVariable String PaisNome){
	
	Optional<PaisesDTO> paisretorno = 	PaisService.obterPaisPorNome(PaisNome);
	return paisretorno.isPresent()?ResponseEntity.ok(paisretorno.get()):ResponseEntity.notFound().build();
		
}
 

@PostMapping
public ResponseEntity<Object> inserirPais(@RequestBody PaisesDTO Pais){
	
	return ResponseEntity.status(HttpStatus.CREATED).body(PaisService.inserirPais(Pais));
	
}


@PutMapping("/{PaisId}")
public ResponseEntity<Object> atualizarPais(@PathVariable int PaisId,@RequestBody PaisesDTO Pais){
	
	return ResponseEntity.ok(PaisService.atualizarPais(PaisId, Pais));
	
}


@DeleteMapping("/{PaisId}")
public ResponseEntity<Object> deletarPais(@PathVariable int PaisId){
	
	return ResponseEntity.ok(PaisService.deletarPais(PaisId));
	
}

	
}



