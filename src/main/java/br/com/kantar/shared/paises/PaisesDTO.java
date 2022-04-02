package br.com.kantar.shared.paises;

import java.util.*;



public class PaisesDTO {


	    private int id;
	    private String nome;
	    private String agrupamento;
	    
	   
		public PaisesDTO() {
			super();
		}
		
		public PaisesDTO(int id, String nome, String agrupamento) {
			super();
			this.id = id;
			this.nome = nome;
			this.agrupamento = agrupamento;
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
		public String getAgrupamento() {
			return agrupamento;
		}
		public void setAgrupamento(String agrupamento) {
			this.agrupamento = agrupamento;
		}

		@Override
		public int hashCode() {
			return Objects.hash(agrupamento, nome);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PaisesDTO other = (PaisesDTO) obj;
			return Objects.equals(agrupamento, other.agrupamento) && Objects.equals(nome, other.nome);
		}

		@Override
		public String toString() {
			return "PaisesDTO [id=" + id + ", nome=" + nome + ", agrupamento=" + agrupamento + "]";
		}


	
}
