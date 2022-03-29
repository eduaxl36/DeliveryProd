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

@Table(name="sla_mensal") 
@Entity
public class SlaMensal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable=false) 
    private int Id;
    
    @Column(name = "mesproducao")
    private int Semanaproducao;
    
    @Column(name = "datasla")
    private LocalDate Datasla;
    
    @Column(name = "hora")
    private LocalTime Hora;
    
    @Column(name = "iddiasemana")
    private int Iddiasemana;
    
    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="Pais")
    private Paises Pais;
   
    @OneToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinColumn(name="Produto")
    private Produtos Produto;

    public SlaMensal() {
    }

    public SlaMensal(int Id, int Semanaproducao, LocalDate Datasla, LocalTime Hora, int Iddiasemana, Paises Pais, Produtos Produto) {
        this.Id = Id;
        this.Semanaproducao = Semanaproducao;
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

    public int getSemanaproducao() {
        return Semanaproducao;
    }

    public void setSemanaproducao(int Semanaproducao) {
        this.Semanaproducao = Semanaproducao;
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
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.Id;
        hash = 97 * hash + this.Semanaproducao;
        hash = 97 * hash + Objects.hashCode(this.Datasla);
        hash = 97 * hash + Objects.hashCode(this.Hora);
        hash = 97 * hash + this.Iddiasemana;
        hash = 97 * hash + Objects.hashCode(this.Pais);
        hash = 97 * hash + Objects.hashCode(this.Produto);
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
        final SlaMensal other = (SlaMensal) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (this.Semanaproducao != other.Semanaproducao) {
            return false;
        }
        if (this.Iddiasemana != other.Iddiasemana) {
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

    @Override
    public String toString() {
        return "sla_mensal{" + "Id=" + Id + ", Semanaproducao=" + Semanaproducao + ", Datasla=" + Datasla + ", Hora=" + Hora + ", Iddiasemana=" + Iddiasemana + ", Pais=" + Pais + ", Produto=" + Produto + '}';
    }






}