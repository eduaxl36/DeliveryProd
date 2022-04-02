package br.com.kantar.shared.paises;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import br.com.kantar.model.Paises;

public class PaisDTOUtil {

	public static PaisesDTO PaisDTOConversion(Paises Pais) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		PaisesDTO paisDTO = mapper.map(Pais, PaisesDTO.class);

		return paisDTO;

	}

	public static Paises PaisObjConversion(PaisesDTO Pais) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Paises pais = mapper.map(Pais, Paises.class);

		return pais;

	}
	
	

	
}
