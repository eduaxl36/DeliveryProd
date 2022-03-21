/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author eduardo.fernando
 */

@Table(name="paises") 
@Entity
public class Paises implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable=false)  
    private int id;
    
    @Column(name = "nome")
    @NotBlank(message="NOME FORNECIDO PARA PAIS É INVALIDO!")
    @Length(min=3,max=50,message="TAMANHO FORNECIDO PARA PAIS É INVALIDO")
    private String nome;
    
    @Column(name = "agrupamento")
    private String agrupamento;

    public Paises() {
    }

    public Paises(int id, String nome, String agrupamento) {
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
        int hash = 7;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.agrupamento);
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
        final Paises other = (Paises) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return Objects.equals(this.agrupamento, other.agrupamento);
    }

    @Override
    public String toString() {
        return "Paises{" + "id=" + id + ", nome=" + nome + ", agrupamento=" + agrupamento + '}';
    }
    
    
    
    
}
