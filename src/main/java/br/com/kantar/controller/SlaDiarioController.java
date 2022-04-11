package br.com.kantar.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.kantar.services.SlaDiarioServices;
import br.com.kantar.shared.SlaDiario.SlaDiarioDTO;



@RestController
@RequestMapping("/api/slaDiario/")
public class SlaDiarioController {

	@Autowired
	private SlaDiarioServices slaDiarioService;
	

	@GetMapping
	public List<SlaDiarioDTO> obterTodosSlas(){
		
		
		return slaDiarioService.ObterTodosSlas();
		
	}
	
	
	@GetMapping("obterMesPorNOMEMES/{PaisNome}/{Ano}/{Mes}")
	public List<SlaDiarioDTO> obterSlaPorPaisNomeMes(@PathVariable String PaisNome,@PathVariable int Mes,@PathVariable int Ano) {
		
		List<SlaDiarioDTO> slasDiarios = 	slaDiarioService.obterSlaPorRangeMes(PaisNome, Ano,Mes);
		
		return slasDiarios;
		
	}

	
	@GetMapping("obterMesPorIDANO/{PaisId}/{Ano}/{Mes}")
	public List<SlaDiarioDTO> obterSlaPorPaisIdMes(@PathVariable int PaisId,@PathVariable int Mes,@PathVariable int Ano) {
		
		List<SlaDiarioDTO> slasDiarios = 	slaDiarioService.obterSlaPorRangeMes(PaisId, Ano,Mes);
		
		return slasDiarios;
		
	}

	
	
	@GetMapping("teste/{PaisId}/{DataInicial}/{DataFinal}/{ProdutoId}")
	public List<SlaDiarioDTO> obterSlaPorRangeDatas(@PathVariable  int PaisId,@PathVariable  String DataInicial,@PathVariable  String DataFinal,@PathVariable  int ProdutoId) {
		

		return slaDiarioService.obterSlaPorRangeDatas(PaisId,LocalDate.parse(DataInicial),LocalDate.parse(DataFinal),ProdutoId);
		
	}		

	
	@GetMapping("teste1/{PaisNome}/{DataInicial}/{DataFinal}/{ProdutoNome}")
	public List<SlaDiarioDTO> obterSlaPorRangeDatas(@PathVariable  String PaisNome,@PathVariable  String DataInicial,@PathVariable  String DataFinal,@PathVariable  String ProdutoNome) {
		
	
		return slaDiarioService.obterSlaPorRangeDatas(PaisNome,LocalDate.parse(DataInicial),LocalDate.parse(DataFinal),ProdutoNome);
		
	}	
	

	
	@PostMapping
	public ResponseEntity<Object> insereSlaDiario(@RequestBody SlaDiarioDTO slaDiario){
		
		return ResponseEntity.ok(slaDiarioService.inserirSla(slaDiario));
		
	}
	
	
	
	@PutMapping("/{SlaDiarioId}")
	public ResponseEntity<Object> atualizarSla(@PathVariable int SlaDiarioId,@RequestBody SlaDiarioDTO slaDiario){
		
		return ResponseEntity.ok(slaDiarioService.atualizaSla(SlaDiarioId, slaDiario));
		
	}
	
	
	
	@DeleteMapping("/{SlaDiarioId}")
	public ResponseEntity<Object> deletaSla(@PathVariable int SlaDiarioId){
		
		return ResponseEntity.ok(slaDiarioService.deletaSla(SlaDiarioId));
		
	}
		
	
	
	
}
