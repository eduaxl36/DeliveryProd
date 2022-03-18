/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.kantar.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kantar.model.Paises;




/**
 *
 * @author eduardo.fernando
 */

@Repository
public interface PaisesRepository extends JpaRepository<Paises, Integer> {
    
  Optional<Paises> findBynome(String pNome);
    
}
