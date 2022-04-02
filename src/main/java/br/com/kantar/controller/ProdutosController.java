package br.com.kantar.controller;

import java.util.*;
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
import br.com.kantar.model.Produtos;
import br.com.kantar.services.ProdutosServices;
import br.com.kantar.shared.produtos.ProdutosDTO;

@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {

	@Autowired
	private ProdutosServices ProdutosService;
	
	@GetMapping
	public ResponseEntity<List<ProdutosDTO>> obterTodosProdutos(){  

	List<ProdutosDTO> ProdutosDTO = ProdutosService.obterTodosProdutos();
	 
	return ResponseEntity.ok().body(ProdutosDTO);

	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<ProdutosDTO> obterProdutoPorId(@PathVariable int codigo){
		
		Optional<ProdutosDTO> produtoRetorno = ProdutosService.obterProdutoPorId(codigo);
		return produtoRetorno.isPresent()?ResponseEntity.ok(produtoRetorno.get()):ResponseEntity.notFound().build();
			
	}

	
	@GetMapping("nome/{nome}")
	public List<Optional<Produtos>> obterProdutoPorNome(@PathVariable String nome){
		
		List<Optional<Produtos>> produtoRetorno = ProdutosService.obterProdutosPorNome(nome);
		return produtoRetorno;	
	
	}
	
	
	@PostMapping
	public ResponseEntity<Object> insereProduto(@RequestBody ProdutosDTO Produto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ProdutosService.insereProduto(Produto));
		
	}
	
	
	@DeleteMapping("/{Id}")
	public ResponseEntity<Object> deletaProduto(@PathVariable int Id){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ProdutosService.deletarProduto(Id));
		
	}
	

	@PutMapping("/{Id}")
	public ResponseEntity<Object> atualizarProduto(@PathVariable int Id,@RequestBody ProdutosDTO Produto){
		
		return ResponseEntity.ok(ProdutosService.atualizarProduto(Id, Produto));
		
	}
	
	
	
	
}
