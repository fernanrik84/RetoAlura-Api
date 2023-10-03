package com.alura.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.Exception.RegistroDuplicadoException;
import com.alura.modelo.Topico;
import com.alura.repository.TopicoDao;

import jakarta.transaction.Transactional;

@Service
public class TopicoServiceImpl implements TopicoService{

	@Autowired
	private TopicoDao topicoDao;
	
	@Override
	@Transactional
	public List<Topico> findAll() {
		return (List<Topico>) topicoDao.findAll();
	}

	@Override
	@Transactional
	public Topico save(Topico topic) {
		if (!topicoDao.existsByTituloAndMensaje(topic.getTitulo(), topic.getMensaje())) {
            // Si no existe, crea el nuevo tópico
		 	return topicoDao.save(topic);
        } else {
            // Manejar el caso de registro duplicado aquí
            throw new RegistroDuplicadoException("El tópico ya existe");
        }
	}

	@Override 
	public Topico findById(Long id) {
		return topicoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Topico topic) {
		topicoDao.delete(topic);
		
	}
}
