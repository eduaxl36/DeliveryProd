package br.com.kantar.shared.SlaDiario;

import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import br.com.kantar.model.SlaDiario;


public class SlaDiarioDTOUtil {

	public static SlaDiarioDTO slaDiarioDTOConversion(SlaDiario slaDiario) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		SlaDiarioDTO slaDiarioDTO = mapper.map(slaDiario, SlaDiarioDTO.class);

		return slaDiarioDTO;

	}

	public static SlaDiario slaDiarioObjConversion(SlaDiarioDTO slaDiarioDTO) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		SlaDiario slaDiario = mapper.map(slaDiarioDTO, SlaDiario.class);

		return slaDiario;

	}
	
	

	
}
