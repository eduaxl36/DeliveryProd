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
@Table(name="produtos") 
@Entity
public class Produtos implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable=false)  
    private int id;
    
	
    @Column(name = "nome")
    @NotBlank(message="NOME FORNECIDO PARA PRODUTO É INVALIDO!")
    @Length(min=3,max=50,message="TAMANHO FORNECIDO PARA PRODUTO É INVALIDO")
    private String nome;
    
    @Column(name = "frequencia")
    @NotBlank(message="DEFINIÇÃO INVALIDA PARA FREQUENCIA!")
    @Length(min=3,max=100,message="TAMANHO FORNECIDO PARA FREQUENCIA É INVALIDO")
    private String frequencia;

    public Produtos() {
    }

    public Produtos(int id, String nome, String frequencia) {
        this.id = id;
        this.nome = nome;
        this.frequencia = frequencia;
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
		Produtos other = (Produtos) obj;
		return Objects.equals(frequencia, other.frequencia) && Objects.equals(nome, other.nome);
	}


    
}
