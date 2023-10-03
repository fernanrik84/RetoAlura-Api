package com.alura.services;

import java.util.List;
import com.alura.modelo.Topico;

public interface TopicoService{

	public List<Topico> findAll();
	
	public Topico save(Topico topic);
	
	public Topico findById(Long id);
	
	public void delete(Topico topic);
}
