package br.com.kantar.shared.justificativas;

import java.util.Objects;

public class JustificativasDTO {


	    private int id;
	    
	    private String descricao;
	    
	    private String abreviacao;
	    
	    private String tipoatraso;

		public JustificativasDTO() {
			super();
		}

		public JustificativasDTO(int id, String descricao, String abreviacao, String tipoatraso) {
			super();
			this.id = id;
			this.descricao = descricao;
			this.abreviacao = abreviacao;
			this.tipoatraso = tipoatraso;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getAbreviacao() {
			return abreviacao;
		}

		public void setAbreviacao(String abreviacao) {
			this.abreviacao = abreviacao;
		}

		public String getTipoatraso() {
			return tipoatraso;
		}

		public void setTipoatraso(String tipoatraso) {
			this.tipoatraso = tipoatraso;
		}

		@Override
		public int hashCode() {
			return Objects.hash(abreviacao, descricao, tipoatraso);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			JustificativasDTO other = (JustificativasDTO) obj;
			return Objects.equals(abreviacao, other.abreviacao) && Objects.equals(descricao, other.descricao)
					&& Objects.equals(tipoatraso, other.tipoatraso);
		}

		@Override
		public String toString() {
			return "JustificativasDTO [id=" + id + ", descricao=" + descricao + ", abreviacao=" + abreviacao
					+ ", tipoatraso=" + tipoatraso + "]";
		}
	    
	    
	    

}
