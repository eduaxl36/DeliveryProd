package br.com.kantar.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.kantar.model.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {

	List<Optional<Produtos>> findBynome(String nome);
	
	Optional<Produtos> findByNomeAndFrequencia(String nome,String frequencia);
	
	List<String> findByFrequencia(String frequencia);

	
}
