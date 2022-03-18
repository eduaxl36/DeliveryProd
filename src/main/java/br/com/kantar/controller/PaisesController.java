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
public ResponseEntity<List<Paises>> obterTodosPaises(){  

List<Paises> list = PaisService.obterTodosPaises();
 
return ResponseEntity.ok().body(list);

}

@GetMapping("/{codigo}")
public ResponseEntity<Paises> obterPaisPorId(@PathVariable int codigo){
	
	Optional<Paises> paisretorno = 	PaisService.obterPorId(codigo);
	return paisretorno.isPresent()?ResponseEntity.ok(paisretorno.get()):ResponseEntity.notFound().build();
	
	
}


@GetMapping("buscaNome/{nome}")
public ResponseEntity<Paises> obterPaisPorNome(@PathVariable String nome){
	
	Optional<Paises> paisretorno = 	PaisService.obterPaisPorNome(nome);
	return paisretorno.isPresent()?ResponseEntity.ok(paisretorno.get()):ResponseEntity.notFound().build();
	
	
}
 

@PostMapping
public ResponseEntity<Object> inserirPais(@RequestBody Paises pais){
	
	return ResponseEntity.status(HttpStatus.CREATED).body(PaisService.inserirPais(pais));
	
}


@PutMapping("/{id}")
public ResponseEntity<Object> atualizarPais(@PathVariable int id,@RequestBody Paises pais){
	
	
	return ResponseEntity.ok(PaisService.atualizarPais(id, pais));
	
}

@DeleteMapping("/{id}")
public void deletarPais(@PathVariable int id){
	
	PaisService.deletarPais(id);
	
}

	
}



