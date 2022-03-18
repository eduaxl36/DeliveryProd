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
public class sla_diario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable=false) 
    private int Id;
    
    @Column(name = "dataproducao")
    private LocalDate Dataproducao;
    
    @Column(name = "datasla")
    private LocalDate Datasla;
    
    @Column(name = "hora")
    private LocalTime Hora;
    
    @Column(name = "iddiasemana")
    private int Iddiasemana;
    
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="Pais")
    private Paises Pais;
   
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="Produto")
    private Produtos Produto;

    public sla_diario() {
    }

    public sla_diario(int Id, LocalDate Dataproducao, LocalDate Datasla, LocalTime Hora, int Iddiasemana, Paises Pais, Produtos Produto) {
        this.Id = Id;
        this.Dataproducao = Dataproducao;
        this.Datasla = Datasla;
        this.Hora = Hora;
        this.Iddiasemana = Iddiasemana;
        this.Pais = Pais;
        this.Produto = Produto;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public LocalDate getDataproducao() {
        return Dataproducao;
    }

    public void setDataproducao(LocalDate Dataproducao) {
        this.Dataproducao = Dataproducao;
    }

    public LocalDate getDatasla() {
        return Datasla;
    }

    public void setDatasla(LocalDate Datasla) {
        this.Datasla = Datasla;
    }

    public LocalTime getHora() {
        return Hora;
    }

    public void setHora(LocalTime Hora) {
        this.Hora = Hora;
    }

    public int getIddiasemana() {
        return Iddiasemana;
    }

    public void setIddiasemana(int Iddiasemana) {
        this.Iddiasemana = Iddiasemana;
    }

    public Paises getPais() {
        return Pais;
    }

    public void setPais(Paises Pais) {
        this.Pais = Pais;
    }

    public Produtos getProduto() {
        return Produto;
    }

    public void setProduto(Produtos Produto) {
        this.Produto = Produto;
    }

    @Override
    public String toString() {
        return "sla_diario{" + "Id=" + Id + ", Dataproducao=" + Dataproducao + ", Datasla=" + Datasla + ", Hora=" + Hora + ", Iddiasemana=" + Iddiasemana + ", Pais=" + Pais + ", Produto=" + Produto + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.Id;
        hash = 37 * hash + Objects.hashCode(this.Dataproducao);
        hash = 37 * hash + Objects.hashCode(this.Datasla);
        hash = 37 * hash + Objects.hashCode(this.Hora);
        hash = 37 * hash + this.Iddiasemana;
        hash = 37 * hash + Objects.hashCode(this.Pais);
        hash = 37 * hash + Objects.hashCode(this.Produto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final sla_diario other = (sla_diario) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (this.Iddiasemana != other.Iddiasemana) {
            return false;
        }
        if (!Objects.equals(this.Dataproducao, other.Dataproducao)) {
            return false;
        }
        if (!Objects.equals(this.Datasla, other.Datasla)) {
            return false;
        }
        if (!Objects.equals(this.Hora, other.Hora)) {
            return false;
        }
        if (!Objects.equals(this.Pais, other.Pais)) {
            return false;
        }
        return Objects.equals(this.Produto, other.Produto);
    }
    
    
    
    
    
    
    
    
}
