package com.alura.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.Exception.RegistroDuplicadoException;
import com.alura.modelo.Respuesta;
import com.alura.repository.RespuestaDao;

import jakarta.transaction.Transactional;

@Service
public class RespuestaServiceImpl implements RespuestaService{

	@Autowired
	private RespuestaDao respuestaDao;
	
	@Override
	@Transactional
	public List<Respuesta> findAll() {
		return (List<Respuesta>) respuestaDao.findAll();
	}

	@Override
	@Transactional
	public Respuesta save(Respuesta respuesta) {
		return respuestaDao.save(respuesta);
		/*
		if (!respuestaDao.existsByMensajeAndTopicoAndUser(respuesta.getMensaje(), 
				respuesta.getTopico(), respuesta.getAutor())) {
            // Si no existe, crea el nuevo tópico
			return respuestaDao.save(respuesta);
		} else {
            // Manejar el caso de registro duplicado aquí
            throw new RegistroDuplicadoException("El ... ya existe");
        }
        */
	}

	@Override 
	public Respuesta findById(Long id) {
		return respuestaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Respuesta respuesta) {
		respuestaDao.delete(respuesta);
		
	}
}
