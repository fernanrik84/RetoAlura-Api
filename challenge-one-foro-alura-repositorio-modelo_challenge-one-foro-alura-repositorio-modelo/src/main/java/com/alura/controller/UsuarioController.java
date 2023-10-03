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

import com.alura.modelo.Usuario;
import com.alura.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Validated
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/usuarios")
	public String welcomepage() {
		return "Welcome to Fercho user";
	}
	
	@GetMapping(value="/usuarios")
	public ResponseEntity<Object> get(){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Usuario> list  = usuarioService.findAll();
			return new ResponseEntity<Object>(list,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
	
	@GetMapping(value="/usuarios/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id){ 
		try {
			Usuario data  = usuarioService.findById(id);
			return new ResponseEntity<Object>(data,HttpStatus.OK);
		} 
		catch (Exception e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}

	@PostMapping(value="/usuarios")
	public ResponseEntity<Object> create(@Valid @RequestBody Usuario user){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Usuario res = usuarioService.save(user);  
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Object> update(@RequestBody Usuario user, @PathVariable Long id){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Usuario currentPerson = usuarioService.findById(id);
			currentPerson.setNombre(user.getNombre());
			currentPerson.setEmail(user.getEmail());
			currentPerson.setContrasena(user.getContrasena()); 

			Usuario res = usuarioService.save(user);
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){ 
		Map<String, Object> map = new HashMap<String, Object>();
		try { 
			Usuario currentPerson = usuarioService.findById(id); 
			usuarioService.delete(currentPerson);
			map.put("deleted", true);
			return new ResponseEntity<Object>(map,HttpStatus.OK);
		} 
		catch (Exception e) {
			map.put("message", e.getMessage());
			return new ResponseEntity<>( map, HttpStatus.INTERNAL_SERVER_ERROR);
		} 
 	}
}
