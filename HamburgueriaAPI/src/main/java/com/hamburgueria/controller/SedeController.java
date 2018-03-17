package com.hamburgueria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hamburgueria.model.Sede;
import com.hamburgueria.response.Response;
import com.hamburgueria.response.SedeData;
import com.hamburgueria.service.SedeService;

@RestController
@RequestMapping("/sede")
public class SedeController {
	
	@Autowired
	SedeService sedeService;

	/* Alterar para SedeData */
	@GetMapping
	public ResponseEntity<List<SedeData>> listarSedes() {
		List<SedeData> sedeDatas = new ArrayList<SedeData>();
		for (Sede sede : sedeService.listar()) {
			sedeDatas.add(new SedeData(sede.getId(), sede.getCidade()));
		}
		return ResponseEntity.ok(sedeDatas);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity buscarSede(@PathVariable("id") Long id) {
		Response<SedeData> response = new Response<SedeData>();
		try {
			Sede sede = sedeService.buscar(id);
			return ResponseEntity.ok(new SedeData(sede.getId(), sede.getCidade()));
		} catch (Exception e) {
			response.getErrors().add("Erro ao buscar Sede " + id);
			return ResponseEntity.badRequest().body(response);
		}
	}
	
}
