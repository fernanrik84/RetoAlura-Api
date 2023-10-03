package com.alura.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.modelo.Curso;

@Repository 
public interface CursoDao extends JpaRepository<Curso, Serializable>{

	boolean existsByCategoria(String categoria);

}
