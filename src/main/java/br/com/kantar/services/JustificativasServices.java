package br.com.kantar.services;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kantar.exception.ExistValueException;
import br.com.kantar.exception.NotFoundException;
import br.com.kantar.model.Justificativas;

import br.com.kantar.modelMessage.ModelMessage;
import br.com.kantar.repositories.JustificativasRepository;

import br.com.kantar.shared.justificativas.JustificativasDTO;
import br.com.kantar.shared.justificativas.JustificativasDTOUtil;


@Service
public class JustificativasServices {
	
	@Autowired
	private JustificativasRepository justificativasRepository;
	
	
	public List<JustificativasDTO> obterTodasJustificativas() {

		List<Justificativas> Justificativa = justificativasRepository.findAll();

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		return Justificativa.stream().map(justificativa -> mapper.map(justificativa, JustificativasDTO.class)).collect(Collectors.toList());

	}

	public boolean verificaSeAbreviacaoEstaNaBase(JustificativasDTO JustificativasDTO) {

		return justificativasRepository.findAll().stream()
			                         .anyMatch(justificativa -> justificativa.getAbreviacao()
						             .toLowerCase()
						             .equals(JustificativasDTO.getAbreviacao().toLowerCase()));

	}

	public boolean verificaSeIdEstaNaBase(int JustificativaDTO) {

		return justificativasRepository.findAll().stream().anyMatch(justificativa -> justificativa.getId() == JustificativaDTO);
	}

	public boolean verificaSeJustificativaInserivel(JustificativasDTO Justificativa) {


		boolean ValidaSeAbreviacaoEstaNaBase = !verificaSeAbreviacaoEstaNaBase(Justificativa);

		if (!ValidaSeAbreviacaoEstaNaBase)
			throw new ExistValueException("FALHA DE INSERÇÃO: A abreviacao para a justificativa Fornecida ja encontra-se cadastrada");

		return true;

	}

	public boolean verificaSeJustificativaAtualizavel(int JustificativaDTOId, JustificativasDTO JustificativaParametro) {

		Optional<JustificativasDTO> Justificativa = obterJustificativaPorId(JustificativaDTOId);

		boolean ValidaSeNomeEstaNaBase = !verificaSeAbreviacaoEstaNaBase(JustificativaParametro);

		boolean validaSeIdExiste = verificaSeIdEstaNaBase(JustificativaDTOId);

		boolean validaSeNomeIgualParametro = JustificativaParametro.getAbreviacao()
				.equals(Justificativa.get().getAbreviacao());

		boolean PermiteInsercao = (validaSeIdExiste && validaSeNomeIgualParametro) || ValidaSeNomeEstaNaBase;

		if (!PermiteInsercao)
			throw new ExistValueException(
					"FALHA DE ATUALIZAÇÃO: A Abreviacao fornecida para a justificativa ja encontra-se cadastrada");

		return true;

	}

	public Optional<JustificativasDTO> obterJustificativaPorDescricao(String JustificativaDescricao) {
		
		
		Optional<Justificativas> Justificativa = justificativasRepository.findByDescricao(JustificativaDescricao);
	
		return Optional.ofNullable(JustificativasDTOUtil.JustificativaDTOConversion(Justificativa.get()));
		

	}
	
	public Optional<JustificativasDTO> obterJustificativaPorId(int JustificativaId) {

		Optional<Justificativas> Justificativas = justificativasRepository.findById(JustificativaId);

		Justificativas.orElseThrow(() -> new NotFoundException("FALHA NA OBTENÇÃO: --> O id fornecido para a justificativa é invalido."));

		JustificativasDTO JustificativaDTO = JustificativasDTOUtil.JustificativaDTOConversion(Justificativas.get());

		Optional<JustificativasDTO> OptJustificativasDTO = Optional.ofNullable(JustificativaDTO);

		return OptJustificativasDTO;

	}

	public Optional<Justificativas> obterJustificativaPorAbreviacao(String JustificativaDTOAbrev) {

		return justificativasRepository.findByAbreviacao(JustificativaDTOAbrev);

	}

	public Object insereJustificativa(JustificativasDTO JustificativasParam) {

		verificaSeJustificativaInserivel(JustificativasParam);

		Justificativas Justificativa = JustificativasDTOUtil.JustificativaObjConversion(JustificativasParam);

		justificativasRepository.save(Justificativa);

		return new ModelMessage(201, "Produto inserido com sucesso!");

	}

	public Object deletarJustificativa(int JustificativaId) {
		Optional<JustificativasDTO> Justificativa = obterJustificativaPorId(JustificativaId);
		Justificativa.orElseThrow(() -> new NotFoundException(
				"FALHA NA DELEÇÃO : O Id da justificativa informada não existe, verfique as informações e digite novamente."));
		justificativasRepository.deleteById(JustificativaId);
		return new ModelMessage(200, "Justificativa deletada com sucesso.");

	}

	public Object atualizarJustificativa(int JustificativaIdParam, JustificativasDTO Justificativa) {

		JustificativasDTO JustificativaID = obterJustificativaPorId(JustificativaIdParam).get();

		verificaSeJustificativaAtualizavel(JustificativaIdParam, Justificativa);

		BeanUtils.copyProperties(Justificativa, JustificativaID, "id");
		
		justificativasRepository.save(JustificativasDTOUtil.JustificativaObjConversion(JustificativaID));
		
		return new ModelMessage(200, "Dados da justificativa atualizada com sucesso");

	}







}
