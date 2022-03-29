package br.com.kantar.services;

import java.util.*;
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

	public List<Produtos> obterTodosProdutos() {

		return produtosRepository.findAll();

	}

	public List<Optional<Produtos>> obterProdutosPorNome(String ProdutoNome) {

		if(!verificaSeProdutoExistePorNome(ProdutoNome)) throw new NotFoundException("Produto: O nome fornecido para produto nao existe, verifique as informações e digite novamente.");
		
		return produtosRepository.findBynome(ProdutoNome);

	}

	public Optional<Produtos> obterProdutoPorId(int ProdutoId) {

		Optional<Produtos> Produto = produtosRepository.findById(ProdutoId);

		if (!Produto.isPresent())
			throw new NotFoundException("Produto: O id informado para este produto é invalido! " + ProdutoId);

		return produtosRepository.findById(ProdutoId);

	}

	public boolean verificaSeProdutoExistePorId(int ProdutoId) {

		Optional<Produtos> Produto = produtosRepository.findById(ProdutoId);

		return Produto.isPresent();

	}

	public boolean verificaSeProdutoExistePorNome(String ProdutoNome) {

		boolean ValidaProduto = produtosRepository.findAll().stream().filter(prod -> prod.getNome().equals(ProdutoNome))
				.findAny().isPresent();
		return ValidaProduto;
	}

	public boolean verificaSeProdutoExistePorObjetoPassado(Produtos Produto) {

		boolean ValidaProduto = produtosRepository.findAll().stream()
				.filter(prod -> prod.getNome().equals(Produto.getNome()))
				.filter(prod -> prod.getFrequencia().equals(Produto.getFrequencia())).findAny().isPresent();
		return ValidaProduto;
	}

	public Object deletarProduto(int ProdutoId) {

		if (!verificaSeProdutoExistePorId(ProdutoId))
			throw new NotFoundException("Produto: O Id do produto fornecido para exclusão não existe, verifique o valor fornecido e tente novamente.");

		produtosRepository.deleteById(ProdutoId);

		return new ModelMessage(200, "Produto excluido com sucesso!\n" + ProdutoId);
	}

	public Object insereProduto(Produtos Produto) {

		if (verificaSeProdutoExistePorObjetoPassado(Produto))
			throw new ExistValueException(
					"Produto: O valor fornecido para inserção ja encontra-se cadastrado, verifique as informações e e digite novamente.");

		produtosRepository.save(Produto);

		return new ModelMessage(200, "Produto inserido com sucesso!\n" + Produto);

	}

	
	public boolean verificaSeProdutoAtualizavel(int ProdutoID, Produtos ProdutoPassado) {
		
		Produtos ProdutoObtidoPorId = obterProdutoPorId(ProdutoID).get();

		boolean ValidaSeTemProduto = verificaSeProdutoExistePorNome(ProdutoPassado.getNome());

		boolean ValidaSeNomeEIgual = ProdutoPassado.getNome().equals(ProdutoObtidoPorId.getNome());

		boolean ValidaCondicaoInclusiva = !ValidaSeTemProduto || ValidaSeNomeEIgual;
		
		if (!ValidaCondicaoInclusiva)
			throw new ExistValueException(
					"Produto: O Nome fornecido para produto ja pertence a outro registro, verifique as informações e digite novamente.");


		return ValidaCondicaoInclusiva;
	}
	
	
	public Object atualizaProduto(int ProdutoID, Produtos ProdutoPassado) {

		verificaSeProdutoAtualizavel(ProdutoID, ProdutoPassado);
		
		Produtos ProdutoObtidoPorId = obterProdutoPorId(ProdutoID).get();	
		BeanUtils.copyProperties(ProdutoPassado, ProdutoObtidoPorId, "id");
		produtosRepository.save(ProdutoObtidoPorId);
		return new ModelMessage(200, "Produto : Informações para o produto foram atualizadas com sucesso \n" + ProdutoObtidoPorId);
		
	}

}
