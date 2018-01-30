package com.hamburgueria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hamburgueria.model.Ingrediente;
import com.hamburgueria.response.IngredienteData;
import com.hamburgueria.service.IngredienteService;

@RestController
@RequestMapping("/ingrediente")
public class IngredienteController {

	@Autowired
	IngredienteService ingredienteService;
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ResponseEntity<List<IngredienteData>> listar() {
		List<IngredienteData> ingredientes = new ArrayList<>();
		List<Ingrediente> ingredientesBanco = ingredienteService.listar();
		
		for (Ingrediente ingrediente : ingredientesBanco) {
			ingredientes.add(new IngredienteData(ingrediente.getId(), ingrediente.getNome(), 
					ingrediente.getQtd(), ingrediente.getValorDeVenda(), ingrediente.getFoto64(), 
					ingrediente.getTipoIngrediente().getNome()));
		}
		
		return new ResponseEntity<List<IngredienteData>>(ingredientes, HttpStatus.OK);
	}
	
}
