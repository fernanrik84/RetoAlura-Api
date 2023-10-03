package com.alura.services;

import java.util.List;

import com.alura.modelo.Respuesta;

public interface RespuestaService {

	public List<Respuesta> findAll();
	
	public Respuesta save(Respuesta respuesta);
	
	public Respuesta findById(Long id);
	
	public void delete(Respuesta respuesta);
}
