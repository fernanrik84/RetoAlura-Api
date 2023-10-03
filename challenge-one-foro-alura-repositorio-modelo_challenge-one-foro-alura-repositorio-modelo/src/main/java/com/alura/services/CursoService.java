package com.alura.services;

import java.util.List;
import com.alura.modelo.Curso;

public interface CursoService {

	public List<Curso> findAll();
	
	public Curso save(Curso curso);
	
	public Curso findById(Long id);
	
	public void delete(Curso curso);
}
