package br.com.kantar.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kantar.model.Justificativas;

@Repository
public interface JustificativasRepository extends JpaRepository<Justificativas, Integer> {

	
	Optional<Justificativas>findByAbreviacao(String JustificativaAbreviacao);
	Optional<Justificativas>findByDescricao(String JustificativaDescricao);
	
}
