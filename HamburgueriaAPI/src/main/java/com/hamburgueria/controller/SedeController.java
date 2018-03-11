package com.hamburgueria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hamburgueria.model.Sede;
import com.hamburgueria.response.SedeData;
import com.hamburgueria.service.SedeService;

@RestController
@RequestMapping("/sede")
public class SedeController {
	
	@Autowired
	SedeService sedeService;

	/* Alterar para SedeData */
	@GetMapping
	public List<Sede> listarSedes() {
		return sedeService.listar();
	}
	
	@GetMapping(path="/{id}")
	public SedeData buscarSede(@PathVariable("id") Long id) {
		Sede sede = sedeService.buscar(id);
		return new SedeData(sede.getId(), sede.getCidade());
	}
	
}
