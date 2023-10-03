package com.alura.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.Exception.RegistroDuplicadoException;
import com.alura.modelo.Usuario;
import com.alura.repository.UsuarioDao;

import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	@Transactional
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public Usuario save(Usuario user) {
		if (!usuarioDao.existsByEmailAndNombre(user.getEmail(), user.getNombre())) {
            // Si no existe, crea el nuevo tópico
			return usuarioDao.save(user);
		} else {
            // Manejar el caso de registro duplicado aquí
            throw new RegistroDuplicadoException("El Email ya existe");
        }
	}

	@Override 
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Usuario user) {
		usuarioDao.delete(user);
		
	}
}
