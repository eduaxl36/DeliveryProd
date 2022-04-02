package br.com.kantar.shared.produtos;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import br.com.kantar.model.Produtos;


public class ProdutosDTOUtil {
	
	
	public static ProdutosDTO ProdutoDTOConversion(Produtos Produto) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(Produto, ProdutosDTO.class);

	}

	public static Produtos ProdutoObjConversion(ProdutosDTO Produto) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(Produto, Produtos.class);

	}
	

}
