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

import com.example.demo.entity.Facultad;
import com.example.demo.service.FacultadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/facultades")
@CrossOrigin
public class FacultadController {
	
	@Autowired
	private FacultadService facultadService;
	
	@GetMapping
	public ResponseEntity<List<Facultad>> readAll(){
		try {
			List<Facultad> Facultades = facultadService.readAll();
			if(Facultades.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(Facultades, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PostMapping
	public ResponseEntity<Facultad> create(@Valid @RequestBody Facultad cat){
		try {
			Facultad c = facultadService.create(cat);
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Facultad> getAlumnobyId(@PathVariable("id") Long id){
		try {
			Facultad c = facultadService.read(id).get();
			return new ResponseEntity<>(c, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Facultad> delFacultad(@PathVariable("id") Long id){
		try {
			facultadService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAlumno(@PathVariable("id") Long id, @Valid @RequestBody Facultad cat){

	    Optional<Facultad> existingFacultad = facultadService.read(id);
	    
	    if(existingFacultad.isPresent()) {

	    	Facultad updatedFacultad= existingFacultad.get();
	        

	    	updatedFacultad.setNombre(cat.getNombre());


	        facultadService.update(updatedFacultad);
	        return new ResponseEntity<>(updatedFacultad, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
