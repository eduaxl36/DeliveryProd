/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author eduardo.fernando
 */
@Table(name="justificativas") 
@Entity
public class Justificativas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable=false)  
    private int id;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "abreviacao")
    private String abreviacao;
    
    @Column(name = "tipoatraso")
    private String tipoatraso;

    public Justificativas() {
    }

    public Justificativas(int Id, String Descricao, String Abreviacao, String Tipoatraso) {
        this.id = Id;
        this.descricao = Descricao;
        this.abreviacao = Abreviacao;
        this.tipoatraso = Tipoatraso;
    }

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String Descricao) {
        this.descricao = Descricao;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String Abreviacao) {
        this.abreviacao = Abreviacao;
    }

    public String getTipoatraso() {
        return tipoatraso;
    }

    public void setTipoatraso(String Tipoatraso) {
        this.tipoatraso = Tipoatraso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.descricao);
        hash = 23 * hash + Objects.hashCode(this.abreviacao);
        hash = 23 * hash + Objects.hashCode(this.tipoatraso);
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
        final Justificativas other = (Justificativas) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.abreviacao, other.abreviacao)) {
            return false;
        }
        return Objects.equals(this.tipoatraso, other.tipoatraso);
    }

    @Override
    public String toString() {
        return "justificativas{" + "Id=" + id + ", Descricao=" + descricao + ", Abreviacao=" + abreviacao + ", Tipoatraso=" + tipoatraso + '}';
    }
    
    
    
    
    
}
