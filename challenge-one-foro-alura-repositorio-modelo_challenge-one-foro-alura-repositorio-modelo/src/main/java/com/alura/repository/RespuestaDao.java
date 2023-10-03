package com.alura.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.modelo.Respuesta;
import com.alura.modelo.Topico;
import com.alura.modelo.Usuario;

@Repository 
public interface RespuestaDao extends JpaRepository<Respuesta, Serializable>{

	boolean existsByMensajeAndTopicoAndAutor(String mensaje, Topico topico, Usuario autor);
}
