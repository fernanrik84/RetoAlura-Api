package com.alura.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.modelo.Usuario;

@Repository 
public interface UsuarioDao extends JpaRepository<Usuario, Serializable>{

	boolean existsByEmailAndNombre(String email, String nombre);

}
