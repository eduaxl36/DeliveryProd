package br.com.kantar.services;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kantar.exception.ExistValueException;
import br.com.kantar.exception.NotFoundException;
import br.com.kantar.model.SlaDiario;
import br.com.kantar.modelMessage.ModelMessage;
import br.com.kantar.repositories.SlaDiarioRepository;


@Service
public class SlaDiarioServices {

	@Autowired
	SlaDiarioRepository slaDiarioRepository;
	

	public List<SlaDiario> ObterTodosSlas(){
		
	return 	slaDiarioRepository.findAll();
		
	}
	
	
	public List<SlaDiario> obterSlaPorRangeMes(String PaisNome,int Ano,int Mes) {
		
		List<SlaDiario> SlasDiarios = slaDiarioRepository.findAll()
				                      .stream()
				                      .filter(sla->sla.getDataproducao().getMonth().getValue()==Mes)
				                      .filter(sla->sla.getDataproducao().getYear()==Ano)
				                      .filter(sla->sla.getPais().getNome().equals(PaisNome))
				                      .collect(Collectors.toList());
			
		return SlasDiarios;
		
	}
	
public List<SlaDiario> obterSlaPorRangeMes(int PaisId,int Ano,int Mes) {
		
		List<SlaDiario> SlasDiarios = slaDiarioRepository.findAll()
				                      .stream()
				                      .filter(sla->sla.getDataproducao().getMonth().getValue()==Mes)
				                      .filter(sla->sla.getDataproducao().getYear()==Ano)
				                      .filter(sla->sla.getPais().getId()==PaisId)
				                      .collect(Collectors.toList());
			
		return SlasDiarios;
		
	}



public List<SlaDiario> obterSlaPorRangeDatas(int PaisId,LocalDate DataInicial,LocalDate DataFinal,int ProdutoId) {
	
	List<SlaDiario> SlasDiarios = slaDiarioRepository.findAll()
			                      .stream()
			                      .filter(sla->sla.getDataproducao().isAfter(DataInicial)||sla.getDataproducao().isEqual(DataInicial))
			                      .filter(sla->sla.getDataproducao().isBefore(DataFinal)||sla.getDataproducao().isEqual(DataFinal))			
			                      .filter(sla->sla.getPais().getId()==PaisId)
			                      .filter(sla->sla.getProduto().getId()==ProdutoId)
			                      .collect(Collectors.toList());

	return SlasDiarios;
	
}	


	

public List<SlaDiario> obterSlaPorRangeDatas(String PaisNome,LocalDate DataInicial,LocalDate DataFinal,String ProdutoNome) {
	
	List<SlaDiario> SlasDiarios = slaDiarioRepository.findAll()
			                      .stream()
			                      .filter(sla->sla.getDataproducao().isAfter(DataInicial)||sla.getDataproducao().isEqual(DataInicial))
			                      .filter(sla->sla.getDataproducao().isBefore(DataFinal)||sla.getDataproducao().isEqual(DataFinal))			
			                      .filter(sla->sla.getPais().getNome().equals(PaisNome))
			                      .filter(sla->sla.getProduto().getNome().equals(ProdutoNome))
			                      .collect(Collectors.toList());

	return SlasDiarios;
	
}	



public Object inserirSla(SlaDiario slaDiario) {
	
	slaDiarioRepository.save(slaDiario);
	
	return null;
	
}


public boolean verificarSePaisCompativel(SlaDiario slaDiario) {
	
	List<SlaDiario>validarCompatibilidade =slaDiarioRepository.findAll().stream()
	                   .filter(sla->sla.getPais().getId()==slaDiario.getPais().getId())
	                   .filter(sla->sla.getPais().getNome().equals(slaDiario.getPais().getNome()))
	                   .filter(sla->sla.getPais().getAgrupamento().equals(slaDiario.getPais().getAgrupamento()))
	                   .collect(Collectors.toList());
	                   
	return validarCompatibilidade.size()>0?true:false;
}


public boolean verificarSeProdutoCompativel(SlaDiario slaDiario) {
	
	List<SlaDiario>validarCompatibilidade =slaDiarioRepository.findAll().stream()
	                   .filter(sla->sla.getProduto().getId()==slaDiario.getProduto().getId())
	                   .filter(sla->sla.getProduto().getNome().equals(slaDiario.getProduto().getNome()))
	                   .filter(sla->sla.getProduto().getFrequencia().equals(slaDiario.getProduto().getFrequencia()))
	                   .collect(Collectors.toList());
	                   
	return validarCompatibilidade.size()>0?true:false;
}




public boolean existeDataDeProducao(SlaDiario slaDiario) {
	
	return   slaDiarioRepository.findAll().stream()
            .filter(sla->sla.getProduto().getId()==slaDiario.getProduto().getId())
            .filter(sla->sla.getProduto().getNome().equals(slaDiario.getProduto().getNome()))
            .filter(sla->sla.getProduto().getFrequencia().equals(slaDiario.getProduto().getFrequencia()))
            .filter(sla->sla.getPais().getId()==slaDiario.getPais().getId())
            .filter(sla->sla.getPais().getNome().equals(slaDiario.getPais().getNome()))
            .filter(sla->sla.getPais().getAgrupamento().equals(slaDiario.getPais().getAgrupamento()))
            .anyMatch(sla->sla.getDataproducao().equals(slaDiario.getDataproducao()));
	
}


public int obterNumeroDiaSemana(LocalDate data) {
	
	
	return data.getDayOfWeek().getValue();
	
}



public Optional<SlaDiario> obterSlaPorId(int SlaId) {
	
	
	return Optional.ofNullable(slaDiarioRepository.findById(SlaId).orElseThrow(()->new NotFoundException("Id nao encontrado")));
	
}


public boolean verificaSeIdEstaNaBase(int SlaId) {
	
	
	return !slaDiarioRepository.findById(SlaId).isEmpty();
	
}



public boolean verificaSeSlaAtualizavel(int SlaDiarioId,SlaDiario SlaParametro) {

//	boolean validaIntegridadeObjetos = verificarSePaisCompativel(slaDiario)&&verificarSeProdutoCompativel(slaDiario);
//	
	
	Optional<SlaDiario> SlaDiario = obterSlaPorId(SlaDiarioId);

	// verifica se a data de producao não esta base,--> permite atualizar
	boolean ValidaSeExisteDataProducao = !existeDataDeProducao(SlaParametro);

	/// se existir id, permite atualizar
	boolean validaSeIdExiste = verificaSeIdEstaNaBase(SlaDiarioId);

	/// se os nomes forem iguais, permite atualizar
	boolean validaSeDataIgualParametro = SlaParametro.getDataproducao().equals(SlaDiario.get().getDataproducao());

	boolean PermiteInsercao = (validaSeIdExiste && validaSeDataIgualParametro) || ValidaSeExisteDataProducao;

	if (!PermiteInsercao)
		throw new ExistValueException(
				"FALHA DE ATUALIZAÇÃO: O NOME para o PAIS Fornecido ja encontra-se cadastrado");

	return true;

}


public Object atualizaSla(int SlaId,SlaDiario SlaParam) {
	
	SlaDiario SlaDiario = obterSlaPorId(SlaId).get();

	BeanUtils.copyProperties(SlaParam, SlaDiario, "id");
	slaDiarioRepository.save(SlaDiario);
	return new ModelMessage(200, "Dados atualizados com sucesso!");

	
}


public Object deletaSla(int SlaId) {
	

	slaDiarioRepository.deleteById(SlaId);
	return new ModelMessage(200, "Dados deletados com sucesso!");

	
}

}
