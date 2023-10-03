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

import com.alura.modelo.Respuesta;
import com.alura.services.RespuestaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class RespuestaController {

	@Autowired
	private RespuestaService respuestaService;
	
	@RequestMapping("/respuesta")
	public String welcomepage() {
		return "Welcome to Fercho respuesta";
	}
	
	@GetMapping(value="/respuesta")
	public ResponseEntity<Object> get(){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Respuesta> list  = respuestaService.findAll();
			return new ResponseEntity<Object>(list,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
	
	@GetMapping(value="/respuesta/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id){ 
		try {
			Respuesta data  = respuestaService.findById(id);
			return new ResponseEntity<Object>(data,HttpStatus.OK);
		} 
		catch (Exception e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}

	@PostMapping(value="/respuesta")
	public ResponseEntity<Object> create(@Valid @RequestBody Respuesta respuesta){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Respuesta res = respuestaService.save(respuesta);  
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
	
	@PutMapping("/respuesta/{id}")
	public ResponseEntity<Object> update(@RequestBody Respuesta respuesta, @PathVariable Long id){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Respuesta currentPerson = respuestaService.findById(id);
			currentPerson.setAutor(respuesta.getAutor());
			currentPerson.setMensaje(respuesta.getMensaje());
			currentPerson.setTopico(respuesta.getTopico()); 

			Respuesta res = respuestaService.save(respuesta);
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
	
	@DeleteMapping("/respuesta/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try { 
			Respuesta currentPerson = respuestaService.findById(id); 
			respuestaService.delete(currentPerson);
			map.put("deleted", true);
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
}
