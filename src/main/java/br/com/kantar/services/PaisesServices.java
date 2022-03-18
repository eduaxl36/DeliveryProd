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

	
	/**
	* Retorna a lista de todos os paises
	* @return Paises
	*/
	public List<Paises> obterTodosPaises() {

		return paisRepository.findAll();

	}

	/**
	 * Busca pais por id
	 * @param p_id
	 * @return Paises
	 */
	public Optional<Paises> obterPorId(int p_id) {

		Optional<Paises> Pais = paisRepository.findById(p_id);

		if (!Pais.isPresent()) {

			throw new NotFoundException("O id informado é invalido!!");

		}

		return paisRepository.findById(p_id);

	}

	/**
	 * retorna optional de pais por nome
	 * @param nome
	 * @return Optional<Paises>
	 */
	public Optional<Paises> obterPaisPorNome(String nome) {

		return paisRepository.findBynome(nome);

	}

	
	/**
	 * Adiciona uma pais a base de dados
	 * @param pais
	 * @return Object(msg de personalizada de sucesso ou erro)
	 */
	public Object inserirPais(Paises pais) {

		Optional<Paises> VarPaisInternalScope = obterPaisPorNome(pais.getNome());

		if (VarPaisInternalScope.isPresent()) {

			throw new ExistValueException(
					"O pais mencionado ja encontra-se na base dados, verifique os dados e digite novamente.");
			
		}

		paisRepository.save(pais);

		return new ModelMessage(200, "Dados inseridos com sucesso!");

	}

	
	/**
	 * Atualiza um pais escolhido
	 * @param codigo
	 * @param pais
	 * @return Object(msg de personalizada de sucesso ou erro)
	 */
	public Object atualizarPais(int codigo, Paises pais) {

		//Instancia criada pela obtencao do pais pelo ID.
		Paises PaisCriadoPorGetID = obterPorId(codigo).get();
		
		//Optional de pais Criado pela obtenção do nome
		Optional<Paises> PaisPorNome= obterPaisPorNome(pais.getNome());
		
		///Valida se o nome do pais que esta no getID é igual o do pais passado por parametro 
		boolean validaNomesIguais = PaisCriadoPorGetID.getNome().equals(pais.getNome());
		
		//Valida se o pais passado ja se encontra na base dados
		boolean validaSeNomeEstaNaBase = PaisPorNome.isPresent();
		
		if(!validaSeNomeEstaNaBase||validaNomesIguais) {
			
			BeanUtils.copyProperties(pais, PaisCriadoPorGetID, "id");
			paisRepository.save(PaisCriadoPorGetID);
			return new ModelMessage(200, "Dados atualizados com sucesso!");

		}
		else {
			
			throw new ExistValueException(
					"O pais mencionado ja encontra-se na base dados, verifique os dados e digite novamente.");			
		}

			

	}

	public void deletarPais(int codigo) {

		paisRepository.deleteById(codigo);

	}

}
