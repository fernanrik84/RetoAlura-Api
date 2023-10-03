package com.alura.services;

import java.util.List;
import com.alura.modelo.Usuario;

public interface UsuarioService {

	public List<Usuario> findAll();
	
	public Usuario save(Usuario user);
	
	public Usuario findById(Long id);
	
	public void delete(Usuario user);
}
