package br.com.kantar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kantar.exception.ExistValueException;
import br.com.kantar.exception.NotFoundException;
import br.com.kantar.model.Produtos;
import br.com.kantar.modelMessage.ModelMessage;
import br.com.kantar.repositories.ProdutosRepository;

@Service
public class ProdutosServices {

	
	@Autowired
	private ProdutosRepository produtosRepository;

	
	/**
	 * retorna optional de produto por nome
	 * @param nome
	 * @return Optional<Paises>
	 */
	
	public List<Optional<Produtos>> obterProdutosPorNome(String Nome) {

		return produtosRepository.findBynome(Nome);

	}
	
	public int obterIdProduto(String Nome,String Frequencia) {
		
		Optional<Produtos> Produto = produtosRepository.findByNomeAndFrequencia(Nome, Frequencia);		
		return Produto.isPresent()?Produto.get().getId():0;
		
	}
	
	public List<String> obterListaNomesPorFrequencia(String Frequencia){
		
		return produtosRepository.findByFrequencia(Frequencia);
		
	}
	
	
	/**
	* Retorna a lista de todos os produtos
	* @return Produtos
	*/
	public List<Produtos> obterTodosProdutos() {

		return produtosRepository.findAll();

	}


	public Optional<Produtos> obterPorId(int IdProduto) {

		Optional<Produtos> Produto = produtosRepository.findById(IdProduto);

		if (!Produto.isPresent()) {

			throw new NotFoundException("O id informado é invalido!!");

		}

		return produtosRepository.findById(IdProduto);

	}
	
	
	public boolean checarSeProdutoExistePorId(int Id) {
		
	Optional<Produtos> Produto= produtosRepository.findById(Id);
		
	return Produto.isPresent();
		
	}
	
	public boolean checarSeProdutoExistePorNome(String Nome) {
	    
		boolean ValidaProduto =  produtosRepository.findAll()
				                .stream()
				                .filter(prod->prod.getNome().equals(Nome))
				                .findAny()
				                .isPresent();		                
		return ValidaProduto;
	}
	
	public boolean checarSeProdutoExiste(Produtos Produto) {
		    
		boolean ValidaProduto =  produtosRepository.findAll()
				                .stream()
				                .filter(prod->prod.getNome().equals(Produto.getNome()))
				                .filter(prod->prod.getFrequencia().equals(Produto.getFrequencia()))
				                .findAny()
				                .isPresent();		                
		return ValidaProduto;
	}
	

	public Object deletarProduto(int Id) {
		
		if(!checarSeProdutoExistePorId(Id))
			throw new NotFoundException("O id do produto informado não existe!");	
		
		produtosRepository.deleteById(Id);
		
		return "";
	}

	public Object insereProduto(Produtos produto) {
		
		if(checarSeProdutoExiste(produto))
			throw new ExistValueException("Produto ja encontra-se cadastrado.");
		
		produtosRepository.save(produto);
		
		return new ModelMessage(200, "Dados inseridos com sucesso!");
		
	}


	public Object atualizaProduto(int Id,Produtos ProdutoEntrada) {
		
	    Produtos Produto = obterPorId(Id).get();
	    
	    boolean ValidaSeTemProduto = checarSeProdutoExiste(ProdutoEntrada);
	    
	   
		BeanUtils.copyProperties(ProdutoEntrada,Produto, "id");
		produtosRepository.save(Produto);
		
		return new ModelMessage(200, "Dados inseridos com sucesso!");
		
	}


}


