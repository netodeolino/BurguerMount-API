package com.hamburgueria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hamburgueria.model.Sede;
import com.hamburgueria.service.SedeService;

@RestController
@RequestMapping("/sede")
public class SedeController {
	
	@Autowired
	SedeService sedeService;

	@RequestMapping(value="/sedes", method=RequestMethod.GET)
	public ResponseEntity<List<Sede>> listarSedes() {
		return new ResponseEntity<List<Sede>>(sedeService.listar(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/sede/{id}", method=RequestMethod.GET)
	public ResponseEntity<Sede> buscarSede(@PathVariable("id") Long id) {
		return new ResponseEntity<Sede>(sedeService.buscar(id), HttpStatus.OK);
	}
	
}
