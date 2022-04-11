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
import br.com.kantar.model.Paises;
import br.com.kantar.modelMessage.ModelMessage;
import br.com.kantar.repositories.PaisesRepository;
import br.com.kantar.shared.paises.PaisDTOUtil;
import br.com.kantar.shared.paises.PaisesDTO;

@Service
public class PaisesServices {

	@Autowired
	private PaisesRepository paisRepository;

	public List<PaisesDTO> obterTodosPaises() {

		List<Paises> Paises = paisRepository.findAll();

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		return Paises.stream().map(pais -> mapper.map(pais, PaisesDTO.class)).collect(Collectors.toList());

	}

	public boolean verificaSeNomeEstaNaBase(PaisesDTO PaisDTO) {

		return paisRepository.findAll().stream()
				.anyMatch(pais -> pais.getNome().toLowerCase().equals(PaisDTO.getNome().toLowerCase()));

	}

	public boolean verificaSeIdEstaNaBase(int PaisId) {

		return paisRepository.findAll().stream().anyMatch(pais -> pais.getId() == PaisId);
	}

	public boolean verificaSePaisInserivel(PaisesDTO Pais) {

		// verifica se o nome não esta base, permite atualizar
		boolean ValidaSeNomeEstaNaBase = !verificaSeNomeEstaNaBase(Pais);

		System.out.println(Pais.getNome().toLowerCase() + " - " + ValidaSeNomeEstaNaBase);

		if (!ValidaSeNomeEstaNaBase)
			throw new ExistValueException("FALHA DE INSERÇÃO: O NOME para o PAIS Fornecido ja encontra-se cadastrado");

		return true;

	}

	public boolean verificaSePaisAtualizavel(int PaisDTOId, PaisesDTO PaisParametro) {

		Optional<PaisesDTO> Pais = obterPaisPorId(PaisDTOId);

		// verifica se o nome não esta base permite atualizar
		boolean ValidaSeNomeEstaNaBase = !verificaSeNomeEstaNaBase(PaisParametro);

		/// se existir id, permite atualizar
		boolean validaSeIdExiste = verificaSeIdEstaNaBase(PaisDTOId);

		/// se os nomes forem iguais, permite atualizar
		boolean validaSeNomeIgualParametro = PaisParametro.getNome().equals(Pais.get().getNome());

		boolean PermiteInsercao = (validaSeIdExiste && validaSeNomeIgualParametro) || ValidaSeNomeEstaNaBase;

		if (!PermiteInsercao)
			throw new ExistValueException(
					"FALHA DE ATUALIZAÇÃO: O NOME para o PAIS Fornecido ja encontra-se cadastrado");

		return true;

	}

	public Optional<PaisesDTO> obterPaisPorId(int PaisIdDTO) {

		Optional<Paises> Pais = paisRepository.findById(PaisIdDTO);

		Pais.orElseThrow(() -> new NotFoundException("FALHA NA OBTENÇÃO: --> O ID fornecido para o PAIS é invalido."));

		PaisesDTO PaisDTO = PaisDTOUtil.PaisDTOConversion(Pais.get());

		Optional<PaisesDTO> OptPaisDTO = Optional.ofNullable(PaisDTO);

		return OptPaisDTO;

	}

	public Optional<PaisesDTO> obterPaisPorNome(String nome) {

		Optional<Paises> Pais = paisRepository.findBynome(nome);

		Pais.orElseThrow(() -> new NotFoundException(
				"FALHA NA OBTENÇÃO: O NOME fornecido para o PAIS não retornou nenhum resultado, verifique os dados e digite novamente."));

		PaisesDTO paisDTO = PaisDTOUtil.PaisDTOConversion(Pais.get());

		Optional<PaisesDTO> OptPaisesDTO = Optional.ofNullable(paisDTO);

		return OptPaisesDTO;

	}

	public Object inserirPais(PaisesDTO PaisParam) {

		verificaSePaisInserivel(PaisParam);

		Paises Pais = PaisDTOUtil.PaisObjConversion(PaisParam);

		paisRepository.save(Pais);

		return new ModelMessage(201, "PAIS inserido com sucesso!");

	}

	public Object deletarPais(int PaisId) {
		Optional<PaisesDTO> Pais = obterPaisPorId(PaisId);
		Pais.orElseThrow(() -> new NotFoundException(
				"FALHA NA DELEÇÃO : O Codigo do Pais informado não existe, verfique as informações e digite novamente."));
		paisRepository.deleteById(PaisId);
		return new ModelMessage(200, "PAIS deletado com sucesso.");

	}

	public Object atualizarPais(int PaisIdParam, PaisesDTO Pais) {

		PaisesDTO PaisId = obterPaisPorId(PaisIdParam).get();

		verificaSePaisAtualizavel(PaisIdParam, Pais);

		BeanUtils.copyProperties(Pais, PaisId, "id");
		paisRepository.save(PaisDTOUtil.PaisObjConversion(PaisId));
		return new ModelMessage(200, "Dados atualizados com sucesso!");

	}

	
}
