package br.com.kantar.shared.justificativas;

import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;

import br.com.kantar.model.Justificativas;



public class JustificativasDTOUtil {
	
	
	public static JustificativasDTO JustificativaDTOConversion(Justificativas Justificativa) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(Justificativa, JustificativasDTO.class);

	}

	public static Justificativas JustificativaObjConversion(JustificativasDTO JustificativasParam) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(JustificativasParam, Justificativas.class);

	}
	

}
