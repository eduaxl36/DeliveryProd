package br.com.kantar.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.kantar.exception.ExistValueException;
import br.com.kantar.exception.NotFoundException;
import br.com.kantar.model.Produtos;
import br.com.kantar.modelMessage.ModelMessage;
import br.com.kantar.repositories.ProdutosRepository;
import br.com.kantar.shared.produtos.ProdutosDTO;
import br.com.kantar.shared.produtos.ProdutosDTOUtil;

@Service
public class ProdutosServices {

	@Autowired
	private ProdutosRepository produtosRepository;
	
	
	public List<ProdutosDTO> obterTodosProdutos() {

		List<Produtos> produtos = produtosRepository.findAll();

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		return produtos.stream().map(produto -> mapper.map(produto, ProdutosDTO.class)).collect(Collectors.toList());

	}

	public boolean verificaSeNomeEstaNaBase(ProdutosDTO ProdutoDTO) {

		return produtosRepository.findAll().stream()
				.anyMatch(produto -> produto.getNome().toLowerCase().equals(ProdutoDTO.getNome().toLowerCase()));

	}

	public boolean verificaSeIdEstaNaBase(int ProdutoDTO) {

		return produtosRepository.findAll().stream().anyMatch(produto -> produto.getId() == ProdutoDTO);
	}

	public boolean verificaSeProdutoInserivel(ProdutosDTO Produto) {


		boolean ValidaSeNomeEstaNaBase = !verificaSeNomeEstaNaBase(Produto);

		if (!ValidaSeNomeEstaNaBase)
			throw new ExistValueException("FALHA DE INSERÇÃO: O Nome para o produto Fornecido ja encontra-se cadastrado");

		return true;

	}

	public boolean verificaSeProdutoAtualizavel(int ProdutoDTOID, ProdutosDTO ProdutoParametro) {

		Optional<ProdutosDTO> Produto = obterProdutoPorId(ProdutoDTOID);

		boolean ValidaSeNomeEstaNaBase = !verificaSeNomeEstaNaBase(ProdutoParametro);

		boolean validaSeIdExiste = verificaSeIdEstaNaBase(ProdutoDTOID);

		boolean validaSeNomeIgualParametro = ProdutoParametro.getNome().equals(Produto.get().getNome());

		boolean PermiteInsercao = (validaSeIdExiste && validaSeNomeIgualParametro) || ValidaSeNomeEstaNaBase;

		if (!PermiteInsercao)
			throw new ExistValueException(
					"FALHA DE ATUALIZAÇÃO: O nome para o produto fornecido ja encontra-se cadastrado");

		return true;

	}

	public Optional<ProdutosDTO> obterProdutoPorId(int ProdutoId) {

		Optional<Produtos> Produto = produtosRepository.findById(ProdutoId);

		Produto.orElseThrow(() -> new NotFoundException("FALHA NA OBTENÇÃO: --> O id fornecido para o produto é invalido."));

		ProdutosDTO ProdutoDTO = ProdutosDTOUtil.ProdutoDTOConversion(Produto.get());

		Optional<ProdutosDTO> OptProdutoDTO = Optional.ofNullable(ProdutoDTO);

		return OptProdutoDTO;

	}

	public List<Optional<Produtos>> obterProdutosPorNome(String ProdutoDTONome) {

		return produtosRepository.findBynome(ProdutoDTONome);

	}

	public Object insereProduto(ProdutosDTO ProdutoParam) {

		verificaSeProdutoInserivel(ProdutoParam);

		Produtos produto = ProdutosDTOUtil.ProdutoObjConversion(ProdutoParam);

		produtosRepository.save(produto);

		return new ModelMessage(201, "Produto inserido com sucesso!");

	}

	public Object deletarProduto(int ProdutoId) {
		Optional<ProdutosDTO> Produto = obterProdutoPorId(ProdutoId);
		Produto.orElseThrow(() -> new NotFoundException(
				"FALHA NA DELEÇÃO : O Codigo do produto informado não existe, verfique as informações e digite novamente."));
		produtosRepository.deleteById(ProdutoId);
		return new ModelMessage(200, "Produto deletado com sucesso.");

	}

	public Object atualizarProduto(int ProdutoIdParam, ProdutosDTO Produtos) {

		ProdutosDTO ProdutoID = obterProdutoPorId(ProdutoIdParam).get();

		verificaSeProdutoAtualizavel(ProdutoIdParam, Produtos);

		BeanUtils.copyProperties(Produtos, ProdutoID, "id");
		produtosRepository.save(ProdutosDTOUtil.ProdutoObjConversion(ProdutoID));
		return new ModelMessage(200, "Dados do produto atualizados com sucesso");

	}
}
