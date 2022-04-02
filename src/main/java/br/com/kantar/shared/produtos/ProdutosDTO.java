package br.com.kantar.shared.produtos;

import java.util.Objects;

public class ProdutosDTO {


    private int id;
   
    private String nome;
    
    private String frequencia;

	public ProdutosDTO() {
		super();
	}

	public ProdutosDTO(int id, String nome, String frequencia) {
		super();
		this.id = id;
		this.nome = nome;
		this.frequencia = frequencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(frequencia, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutosDTO other = (ProdutosDTO) obj;
		return Objects.equals(frequencia, other.frequencia) && Objects.equals(nome, other.nome);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}

	@Override
	public String toString() {
		return "ProdutosDTO [id=" + id + ", nome=" + nome + ", frequencia=" + frequencia + "]";
	}

	
    
}
