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

import br.com.kantar.model.Paises;
import br.com.kantar.services.PaisesServices;

@RestController
@RequestMapping("/api/paises")
public class PaisesController {
    
@Autowired
private PaisesServices PaisService;
 	

@GetMapping
public ResponseEntity<List<Paises>> obterListaTodosObjetosPaises(){  

List<Paises> Paises = PaisService.obterTodosPaises();
 
return ResponseEntity.ok().body(Paises);

}

@GetMapping("/{PaisCodigo}")
public ResponseEntity<Paises> obterPaisPorId(@PathVariable int PaisCodigo){
	
	Optional<Paises> paisretorno = 	PaisService.obterPaisPorId(PaisCodigo);
	return paisretorno.isPresent()?ResponseEntity.ok(paisretorno.get()):ResponseEntity.notFound().build();
	
}

@GetMapping("buscaNome/{PaisNome}")
public ResponseEntity<Paises> obterPaisPorNome(@PathVariable String PaisNome){
	
	Optional<Paises> paisretorno = 	PaisService.obterPaisPorNome(PaisNome);
	return paisretorno.isPresent()?ResponseEntity.ok(paisretorno.get()):ResponseEntity.notFound().build();
		
}
 

@PostMapping
public ResponseEntity<Object> inserirPais(@RequestBody Paises Pais){
	
	return ResponseEntity.status(HttpStatus.CREATED).body(PaisService.inserirPais(Pais));
	
}


@PutMapping("/{PaisId}")
public ResponseEntity<Object> atualizarPais(@PathVariable int PaisId,@RequestBody Paises Pais){
	
	return ResponseEntity.ok(PaisService.atualizarPais(PaisId, Pais));
	
}


@DeleteMapping("/{PaisId}")
public void deletarPais(@PathVariable int PaisId){
	
	PaisService.deletarPais(PaisId);
	
}

	
}



