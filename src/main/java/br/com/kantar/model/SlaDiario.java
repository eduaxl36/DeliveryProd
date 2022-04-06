/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author eduardo.fernando
 */

@Table(name="sla_diario") 
@Entity
public class SlaDiario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable=false) 
    private int id;
    
    @Column(name = "dataproducao")
    private LocalDate dataproducao;
    
    @Column(name = "datasla")
    private LocalDate datasla;
    
    @Column(name = "hora")
    private LocalTime hora;
    
    @Column(name = "iddiasemana")
    private int iddiasemana;
    
    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="Pais")
    private Paises pais;
   
    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="Produto")
    private Produtos produto;

    public SlaDiario() {
    }

	public SlaDiario(int id, LocalDate dataproducao, LocalDate datasla, LocalTime hora, int iddiasemana, Paises pais,
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
		SlaDiario other = (SlaDiario) obj;
		return Objects.equals(dataproducao, other.dataproducao) && Objects.equals(datasla, other.datasla)
				&& Objects.equals(hora, other.hora) && iddiasemana == other.iddiasemana
				&& Objects.equals(pais, other.pais) && Objects.equals(produto, other.produto);
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
