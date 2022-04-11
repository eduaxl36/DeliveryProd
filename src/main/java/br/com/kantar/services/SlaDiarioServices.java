package br.com.kantar.services;

import java.time.LocalDate;
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
import br.com.kantar.model.SlaDiario;
import br.com.kantar.modelMessage.ModelMessage;
import br.com.kantar.repositories.SlaDiarioRepository;
import br.com.kantar.shared.SlaDiario.SlaDiarioDTO;
import br.com.kantar.shared.SlaDiario.SlaDiarioDTOUtil;

@Service
public class SlaDiarioServices {

	@Autowired
	SlaDiarioRepository slaDiarioRepository;

	public List<SlaDiarioDTO> ObterTodosSlas() {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		return slaDiarioRepository.findAll()
				.stream()
				.map(sla -> mapper.map(sla, SlaDiarioDTO.class))
				.collect(Collectors.toList());

	}

	public boolean validaRegraInsercaoDatas(LocalDate DataProducao, LocalDate DataSla) {

		if (DataProducao.isBefore(DataSla)) {

			return true;

		} else {

			throw new IllegalArgumentException("Regra de Datas : A data de produção deve ser inferior a data do sla");
		}

	}

	public List<SlaDiarioDTO> obterSlaPorRangeMes(String PaisNome, int Ano, int Mes) {

		List<SlaDiario> SlasDiarios = slaDiarioRepository.findAll().stream()
				.filter(sla -> sla.getDataproducao().getMonth().getValue() == Mes)
				.filter(sla -> sla.getDataproducao().getYear() == Ano)
				.filter(sla -> sla.getPais().getNome().equals(PaisNome)).collect(Collectors.toList());

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		return SlasDiarios.stream()
				           .map(sla->mapper.map(sla, SlaDiarioDTO.class)).collect(Collectors.toList());

	}

	public List<SlaDiarioDTO> obterSlaPorRangeMes(int PaisId, int Ano, int Mes) {

		List<SlaDiario> SlasDiarios = slaDiarioRepository.findAll().stream()
				.filter(sla -> sla.getDataproducao().getMonth().getValue() == Mes)
				.filter(sla -> sla.getDataproducao().getYear() == Ano).filter(sla -> sla.getPais().getId() == PaisId)
				.collect(Collectors.toList());
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		return SlasDiarios.stream().map(sla->mapper.map(sla, SlaDiarioDTO.class)).collect(Collectors.toList());

	}

	public List<SlaDiarioDTO> obterSlaPorRangeDatas(int PaisId, LocalDate DataInicial, LocalDate DataFinal,
			int ProdutoId) {

		List<SlaDiario> SlasDiarios = slaDiarioRepository.findAll().stream()
				.filter(sla -> sla.getDataproducao().isAfter(DataInicial) || sla.getDataproducao().isEqual(DataInicial))
				.filter(sla -> sla.getDataproducao().isBefore(DataFinal) || sla.getDataproducao().isEqual(DataFinal))
				.filter(sla -> sla.getPais().getId() == PaisId).filter(sla -> sla.getProduto().getId() == ProdutoId)
				.collect(Collectors.toList());
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		

		return SlasDiarios.stream().map(sla->mapper.map(sla, SlaDiarioDTO.class)).collect(Collectors.toList());

	}

	public List<SlaDiarioDTO> obterSlaPorRangeDatas(String PaisNome, LocalDate DataInicial, LocalDate DataFinal,
			String ProdutoNome) {

		List<SlaDiario> SlasDiarios = slaDiarioRepository.findAll().stream()
				.filter(sla -> sla.getDataproducao().isAfter(DataInicial) || sla.getDataproducao().isEqual(DataInicial))
				.filter(sla -> sla.getDataproducao().isBefore(DataFinal) || sla.getDataproducao().isEqual(DataFinal))
				.filter(sla -> sla.getPais().getNome().equals(PaisNome))
				.filter(sla -> sla.getProduto().getNome().equals(ProdutoNome)).collect(Collectors.toList());


		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		
		return SlasDiarios.stream().map(sla->mapper.map(sla, SlaDiarioDTO.class)).collect(Collectors.toList());

	}

	
	public boolean validaDataDeProducao(SlaDiarioDTO slaDiario) {

		return slaDiarioRepository.findAll().stream()
				.filter(sla -> sla.getProduto().getId() == slaDiario.getProduto().getId())
				.filter(sla -> sla.getProduto().getNome().equals(slaDiario.getProduto().getNome()))
				.filter(sla -> sla.getProduto().getFrequencia().equals(slaDiario.getProduto().getFrequencia()))
				.filter(sla -> sla.getPais().getId() == slaDiario.getPais().getId())
				.filter(sla -> sla.getPais().getNome().equals(slaDiario.getPais().getNome()))
				.filter(sla -> sla.getPais().getAgrupamento().equals(slaDiario.getPais().getAgrupamento()))
				.anyMatch(sla -> sla.getDataproducao().equals(slaDiario.getDataproducao()));

	}

	public int obterNumeroDiaSemana(LocalDate data) {

		if (data == null)
			throw new NullPointerException("Valor fornecido para a data de produção é invalido");

		return data.getDayOfWeek().getValue();

	}

	public Optional<SlaDiarioDTO> obterSlaPorId(int SlaId) {

		
	  SlaDiarioDTO slaDiarioDTO = SlaDiarioDTOUtil.slaDiarioDTOConversion(slaDiarioRepository.findById(SlaId).get());

	  Optional<SlaDiarioDTO>slaDiarioDTOPTN =Optional.of(slaDiarioDTO);
		
	  
	  slaDiarioDTOPTN.orElseThrow(() -> new NotFoundException(
				"FALHA NA OBTENÇÃO: O Id fornecido para este SLA não existe, verifique os dados e digite novamente."));

	  
	  return slaDiarioDTOPTN;

	}

	public boolean verificaSeIdEstaNaBase(int SlaId) {

		return !slaDiarioRepository.findById(SlaId).isEmpty();

	}

	public boolean verificaSeSlaInserivel(SlaDiarioDTO SlaParametro) {

		// verifica se a data de producao não esta base,--> permite atualizar
		boolean ValidaSeExisteDataProducao = !validaDataDeProducao(SlaParametro);

		boolean PermiteInsercao = (ValidaSeExisteDataProducao);

		if (!PermiteInsercao)
			throw new ExistValueException(
					"FALHA DE INSERÇÃO: A data de produção fornecida ja encontra-se cadastrada, verfique os dados e digite novamente.");

		return true;

	}

	public boolean verificaSeSlaAtualizavel(int SlaDiarioId, SlaDiarioDTO SlaParametro) {

		Optional<SlaDiarioDTO> SlaDiario = obterSlaPorId(SlaDiarioId);

		// verifica se a data de producao não esta base,--> permite atualizar
		boolean ValidaSeExisteDataProducao = !validaDataDeProducao(SlaParametro);

		/// se existir id, permite atualizar
		boolean validaSeIdExiste = verificaSeIdEstaNaBase(SlaDiarioId);

		/// se os nomes forem iguais, permite atualizar
		boolean validaSeDataIgualParametro = SlaParametro.getDataproducao().equals(SlaDiario.get().getDataproducao());

		boolean PermiteInsercao = (validaSeIdExiste && validaSeDataIgualParametro) || ValidaSeExisteDataProducao;

		if (!PermiteInsercao)
			throw new ExistValueException(
					"FALHA DE ATUALIZAÇÃO: A data fornecida para atualização ja encontra-se cadastrada, verifique os dados e digite novamente.");

		return true;

	}

	public Object atualizaSla(int SlaId, SlaDiarioDTO SlaParam) {

		SlaDiarioDTO SlaDiario = obterSlaPorId(SlaId).get();

		validaRegraInsercaoDatas(SlaParam.getDataproducao(), SlaParam.getDatasla());
		
		verificaSeSlaAtualizavel(SlaId, SlaParam);

		BeanUtils.copyProperties(SlaParam, SlaDiario, "id");

		SlaDiario.setIddiasemana(obterNumeroDiaSemana(SlaDiario.getDataproducao()));
		
		slaDiarioRepository.save(SlaDiarioDTOUtil.slaDiarioObjConversion(SlaDiario));

		return new ModelMessage(200, "Dados atualizados com sucesso!");

	}

	public Object deletaSla(int SlaId) {

		obterSlaPorId(SlaId);
		slaDiarioRepository.deleteById(SlaId);
		return new ModelMessage(200, "Dados deletados com sucesso!");

	}

	public Object inserirSla(SlaDiarioDTO slaDiario) {

		validaRegraInsercaoDatas(slaDiario.getDataproducao(), slaDiario.getDatasla());
		verificaSeSlaInserivel(slaDiario);

		slaDiario.setIddiasemana(obterNumeroDiaSemana(slaDiario.getDataproducao()));

		slaDiarioRepository.save(SlaDiarioDTOUtil.slaDiarioObjConversion(slaDiario));

		return new ModelMessage(200, "Dados inseridos com sucesso");

	}

}
