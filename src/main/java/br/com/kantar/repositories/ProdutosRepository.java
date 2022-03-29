package br.com.kantar.repositories;

import java.util.*;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.kantar.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {

	List<Optional<Produtos>> findBynome(String ProdutoNome);
	
	Optional<Produtos> findByNomeAndFrequencia(String ProdutoNome,String ProdutoFrequencua);
	
	List<Produtos> findByFrequencia(String Frequencia);

	
}
