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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kantar.model.Produtos;
import br.com.kantar.services.ProdutosServices;

@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {

	@Autowired
	private ProdutosServices ProdutosService;
	
	@GetMapping
	public ResponseEntity<List<Produtos>> obterTodosProdutos(){  

	List<Produtos> list = ProdutosService.obterTodosProdutos();
	 
	return ResponseEntity.ok().body(list);

	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Produtos> obterProdutoPorId(@PathVariable int codigo){
		
		Optional<Produtos> produtoRetorno = 	ProdutosService.obterPorId(codigo);
		return produtoRetorno.isPresent()?ResponseEntity.ok(produtoRetorno.get()):ResponseEntity.notFound().build();
			
	}

	
	@GetMapping("nome/{nome}")
	public List<Optional<Produtos>> obterProdutoPorNome(@PathVariable String nome){
		
		List<Optional<Produtos>> produtoRetorno = 	ProdutosService.obterProdutosPorNome(nome);
		return produtoRetorno;	
	
	}
	
	@GetMapping("obterId/{nome}/{frequencia}")
	public int obterIdProduto(@PathVariable String nome,@PathVariable String frequencia) {
		
		return ProdutosService.obterIdProduto(nome, frequencia);
		
	}
	
	
	@GetMapping("obterListaNomes/{frequencia}")
	public List<String> obterIdProduto(@PathVariable String frequencia) {
		
		return ProdutosService.obterListaNomesPorFrequencia(frequencia);

	}
	
	@PostMapping
	public ResponseEntity<Object> insereProduto(@RequestBody Produtos Produto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ProdutosService.insereProduto(Produto));
		
	}
	
	
	@DeleteMapping("/{Id}")
	public ResponseEntity<Object> insereProduto(@PathVariable int Id){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ProdutosService.deletarProduto(Id));
		
	}
	


	
}