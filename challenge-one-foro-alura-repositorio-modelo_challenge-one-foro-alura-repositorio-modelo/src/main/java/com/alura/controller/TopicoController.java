package com.alura.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.modelo.Topico;
import com.alura.services.TopicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class TopicoController {

	@Autowired
	private TopicoService topicService;
	
	@RequestMapping("/topicos")
	public String welcomepage() {
		return "Welcome to Yawin Tutor";
	}
	
	@GetMapping(value="/topicos")
	public ResponseEntity<Object> get(){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Topico> list  = topicService.findAll();
			return new ResponseEntity<Object>(list,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
	
	@GetMapping(value="/topicos/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id){ 
		try {
			Topico data  = topicService.findById(id);
			return new ResponseEntity<Object>(data,HttpStatus.OK);
		} 
		catch (Exception e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}

	@PostMapping(value="/topicos")
	public ResponseEntity<Object> create(@Valid @RequestBody Topico topic){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Topico res = topicService.save(topic);  
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
	
	@PutMapping("/topicos/{id}")
	public ResponseEntity<Object> update(@RequestBody Topico topic, @PathVariable Long id){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			Topico currentPerson = topicService.findById(id);
			currentPerson.setTitulo(topic.getTitulo());
			currentPerson.setMensaje(topic.getMensaje());
			currentPerson.setAutor(topic.getAutor()); 
			currentPerson.setCurso(topic.getCurso()); 

			Topico res = topicService.save(topic);
			
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
	
	@DeleteMapping("/topicos/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try { 
			Topico currentPerson = topicService.findById(id); 
			topicService.delete(currentPerson);
			map.put("deleted", true);
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
	
}
