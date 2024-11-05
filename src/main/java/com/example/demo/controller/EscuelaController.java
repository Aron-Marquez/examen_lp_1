package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Escuela;
import com.example.demo.service.EscuelaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/escuelas")
@CrossOrigin
public class EscuelaController {
	
	@Autowired
	private EscuelaService escuelaService;
	
	@GetMapping
	public ResponseEntity<List<Escuela>> readAll(){
		try {
			List<Escuela> Escuelas = escuelaService.readAll();
			if(Escuelas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Escuelas, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<Escuela> create(@Valid @RequestBody Escuela cat){
		try {
			Escuela c = escuelaService.create(cat);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Escuela> getAlumnobyId(@PathVariable("id") Long id){
		try {
			Escuela c = escuelaService.read(id).get();
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Escuela> delAlumno(@PathVariable("id") Long id){
		try {
			escuelaService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAlumno(@PathVariable("id") Long id, @Valid @RequestBody Escuela cat){

	    Optional<Escuela> existingEscuela = escuelaService.read(id);
	    
	    if(existingEscuela.isPresent()) {

	    	Escuela updatedEscuela= existingEscuela.get();
	        

	    	updatedEscuela.setNombre(cat.getNombre());
	    	updatedEscuela.setFacultad(cat.getFacultad());


	        escuelaService.update(updatedEscuela);
	        return new ResponseEntity<>(updatedEscuela, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
