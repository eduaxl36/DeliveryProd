package br.com.kantar.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kantar.exception.ExistValueException;
import br.com.kantar.exception.NotFoundException;
import br.com.kantar.model.Paises;
import br.com.kantar.modelMessage.ModelMessage;
import br.com.kantar.repositories.PaisesRepository;

@Service
public class PaisesServices {

	@Autowired
	private PaisesRepository paisRepository;

	public boolean isValidoAtualizar(int PaisIdParametro, Paises Pais) {

		Paises PaisId = obterPaisPorId(PaisIdParametro).get();
		Optional<Paises> PaisNome = obterPaisPorNome(Pais.getNome());

		boolean validaNomesIguais = PaisId.getNome().equals(Pais.getNome());
		boolean validaSeNomeEstaNaBase = PaisNome.isPresent();
		boolean validaSeJuncaoIsValida = !validaSeNomeEstaNaBase || validaNomesIguais;

		if (validaSeJuncaoIsValida) {
			return true;
		}

		return false;
	}

	public List<Paises> obterTodosPaises() {

		return paisRepository.findAll();

	}

	public Optional<Paises> obterPaisPorId(int PaisId) {

		Optional<Paises> Pais = paisRepository.findById(PaisId);

		if (!Pais.isPresent()) {

			throw new NotFoundException("O id informado é invalido!!");

		}

		return paisRepository.findById(PaisId);

	}

	public Optional<Paises> obterPaisPorNome(String nome) {

		return paisRepository.findBynome(nome);

	}

	public Object inserirPais(Paises pais) {

		Optional<Paises> VarPaisInternalScope = obterPaisPorNome(pais.getNome());

		if (VarPaisInternalScope.isPresent()) {

			throw new ExistValueException(
					"FALHA NA INSERÇÃO : O pais mencionado ja encontra-se na base dados, verifique os dados e digite novamente.");
		}

		paisRepository.save(pais);

		return new ModelMessage(200, "Dados inseridos com sucesso!");

	}

	public Object atualizarPais(int Id, Paises Pais) {

		if (isValidoAtualizar(Id, Pais)) {

			Paises PaisId = obterPaisPorId(Id).get();
			BeanUtils.copyProperties(Pais, PaisId, "id");
			paisRepository.save(PaisId);
			return new ModelMessage(200, "Dados atualizados com sucesso!");

		} else {

			throw new ExistValueException(
					"FALHA NA ATUALIZAÇÃO : --> O pais mencionado ja encontra-se na base dados, verifique os dados e digite novamente.");

		}

	}

	public void deletarPais(int codigo) {

		paisRepository.deleteById(codigo);

	}

}
