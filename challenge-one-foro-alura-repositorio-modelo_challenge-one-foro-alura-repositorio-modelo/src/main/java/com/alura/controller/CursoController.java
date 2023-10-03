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

import com.alura.modelo.Curso;
import com.alura.services.CursoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class CursoController {

	@Autowired
	private CursoService cursoService;
	
	@RequestMapping("/curso")
	public String welcomepage() {
		return "Welcome to Fercho curso";
	}
	
	@GetMapping(value="/curso")
	public ResponseEntity<Object> get(){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Curso> list  = cursoService.findAll();
			return new ResponseEntity<Object>(list,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
	
	@GetMapping(value="/curso/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id){ 
		try {
			Curso data  = cursoService.findById(id);
			return new ResponseEntity<Object>(data,HttpStatus.OK);
		} 
		catch (Exception e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}

	@PostMapping(value="/curso")
	public ResponseEntity<Object> create(@Valid @RequestBody Curso curso){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Curso res = cursoService.save(curso);  
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
	
	@PutMapping("/curso/{id}")
	public ResponseEntity<Object> update(@RequestBody Curso curso, @PathVariable Long id){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Curso currentPerson = cursoService.findById(id);
			currentPerson.setNombre(curso.getNombre());
			currentPerson.setCategoria(curso.getCategoria());

			Curso res = cursoService.save(curso);
			
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
	
	@DeleteMapping("/curso/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try { 
			Curso currentPerson = cursoService.findById(id); 
			cursoService.delete(currentPerson);
			map.put("deleted", true);
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
}
