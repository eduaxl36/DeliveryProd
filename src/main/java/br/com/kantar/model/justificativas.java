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
public class justificativas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable=false)  
    private int Id;
    
    @Column(name = "descricao")
    private String Descricao;
    
    @Column(name = "abreviacao")
    private String Abreviacao;
    
    @Column(name = "tipoatraso")
    private String Tipoatraso;

    public justificativas() {
    }

    public justificativas(int Id, String Descricao, String Abreviacao, String Tipoatraso) {
        this.Id = Id;
        this.Descricao = Descricao;
        this.Abreviacao = Abreviacao;
        this.Tipoatraso = Tipoatraso;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getAbreviacao() {
        return Abreviacao;
    }

    public void setAbreviacao(String Abreviacao) {
        this.Abreviacao = Abreviacao;
    }

    public String getTipoatraso() {
        return Tipoatraso;
    }

    public void setTipoatraso(String Tipoatraso) {
        this.Tipoatraso = Tipoatraso;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.Id;
        hash = 23 * hash + Objects.hashCode(this.Descricao);
        hash = 23 * hash + Objects.hashCode(this.Abreviacao);
        hash = 23 * hash + Objects.hashCode(this.Tipoatraso);
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
        final justificativas other = (justificativas) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (!Objects.equals(this.Descricao, other.Descricao)) {
            return false;
        }
        if (!Objects.equals(this.Abreviacao, other.Abreviacao)) {
            return false;
        }
        return Objects.equals(this.Tipoatraso, other.Tipoatraso);
    }

    @Override
    public String toString() {
        return "justificativas{" + "Id=" + Id + ", Descricao=" + Descricao + ", Abreviacao=" + Abreviacao + ", Tipoatraso=" + Tipoatraso + '}';
    }
    
    
    
    
    
}
