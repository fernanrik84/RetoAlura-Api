package com.alura.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alura.modelo.Topico;

@Repository 
public interface TopicoDao extends JpaRepository<Topico, Serializable> {

	boolean existsByTituloAndMensaje(String titulo, String mensaje);

}
