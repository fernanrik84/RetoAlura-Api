package com.alura.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.Exception.RegistroDuplicadoException;
import com.alura.modelo.Curso;
import com.alura.repository.CursoDao;

import jakarta.transaction.Transactional;

@Service
public class CursoServiceImpl implements CursoService{

	@Autowired
	private CursoDao cursoDao;
	
	@Override
	@Transactional
	public List<Curso> findAll() {
		return (List<Curso>) cursoDao.findAll();
	}

	@Override
	@Transactional
	public Curso save(Curso curso) {
		if (!cursoDao.existsByCategoria(curso.getCategoria())) {
            // Si no existe, crea el nuevo tópico
			return cursoDao.save(curso);
		} else {
            // Manejar el caso de registro duplicado aquí
            throw new RegistroDuplicadoException("La categoria ya existe");
        }
	}

	@Override 
	public Curso findById(Long id) {
		return cursoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Curso curso) {
		cursoDao.delete(curso);
		
	}
}
