package br.com.kantar.shared.SlaDiario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import br.com.kantar.model.Paises;
import br.com.kantar.model.Produtos;

public class SlaDiarioDTO {


    private int id;
    

    private LocalDate dataproducao;
    

    private LocalDate datasla;
    

    private LocalTime hora;
    

    private int iddiasemana;
    

    private Paises pais;
   

    private Produtos produto;


	public SlaDiarioDTO() {
		super();
	}


	public SlaDiarioDTO(int id, LocalDate dataproducao, LocalDate datasla, LocalTime hora, int iddiasemana, Paises pais,
			Produtos produto) {
		super();
		this.id = id;
		this.dataproducao = dataproducao;
		this.datasla = datasla;
		this.hora = hora;
		this.iddiasemana = iddiasemana;
		this.pais = pais;
		this.produto = produto;
	}


	@Override
	public int hashCode() {
		return Objects.hash(dataproducao, datasla, hora, iddiasemana, pais, produto);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SlaDiarioDTO other = (SlaDiarioDTO) obj;
		return Objects.equals(dataproducao, other.dataproducao) && Objects.equals(datasla, other.datasla)
				&& Objects.equals(hora, other.hora) && iddiasemana == other.iddiasemana
				&& Objects.equals(pais, other.pais) && Objects.equals(produto, other.produto);
	}


	@Override
	public String toString() {
		return "SlaDiarioDTO [id=" + id + ", dataproducao=" + dataproducao + ", datasla=" + datasla + ", hora=" + hora
				+ ", iddiasemana=" + iddiasemana + ", pais=" + pais + ", produto=" + produto + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public LocalDate getDataproducao() {
		return dataproducao;
	}


	public void setDataproducao(LocalDate dataproducao) {
		this.dataproducao = dataproducao;
	}


	public LocalDate getDatasla() {
		return datasla;
	}


	public void setDatasla(LocalDate datasla) {
		this.datasla = datasla;
	}


	public LocalTime getHora() {
		return hora;
	}


	public void setHora(LocalTime hora) {
		this.hora = hora;
	}


	public int getIddiasemana() {
		return iddiasemana;
	}


	public void setIddiasemana(int iddiasemana) {
		this.iddiasemana = iddiasemana;
	}


	public Paises getPais() {
		return pais;
	}


	public void setPais(Paises pais) {
		this.pais = pais;
	}


	public Produtos getProduto() {
		return produto;
	}


	public void setProduto(Produtos produto) {
		this.produto = produto;
	}
	
	
    
    
	
}
