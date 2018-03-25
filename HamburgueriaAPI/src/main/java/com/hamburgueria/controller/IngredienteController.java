package com.hamburgueria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hamburgueria.config.JwtEvaluator;
import com.hamburgueria.exceptions.TokenException;
import com.hamburgueria.model.Ingrediente;
import com.hamburgueria.model.Produto;
import com.hamburgueria.model.Usuario;
import com.hamburgueria.response.IngredienteData;
import com.hamburgueria.service.IngredienteService;
import com.hamburgueria.service.ProdutoService;

@RestController
@RequestMapping("/ingrediente")
public class IngredienteController {

	@Autowired
	IngredienteService ingredienteService;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	JwtEvaluator jwtEvaluator;
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ResponseEntity<List<IngredienteData>> listar() throws TokenException {
		Usuario usuarioLogado = jwtEvaluator.usuarioToken();
		
		List<IngredienteData> ingredientes = new ArrayList<>();
		List<Ingrediente> ingredientesBanco = ingredienteService.listarTodos(usuarioLogado.getSede().getId());
		
		for (Ingrediente ingrediente : ingredientesBanco) {
			ingredientes.add(new IngredienteData(ingrediente.getId(), ingrediente.getNome(), 
					ingrediente.getQtd(), ingrediente.getValorDeVenda(), ingrediente.getFoto64(), 
					ingrediente.getTipoIngrediente().getNome()));
		}
		return ResponseEntity.ok(ingredientes);
	}
	
	//Verifica a disponibilidade do ingrediente e de cada produto que possui o mesmo.
	public void verificaDisponibilidade(Ingrediente ingrediente) {
		
		if(ingrediente.getQtd() == 0) {
			ingrediente.setDisponivel(false);
		}else {
			ingrediente.setDisponivel(true);
		}
		
		ingredienteService.salvar(ingrediente);
		
		List<Produto> produtos = ingrediente.getProdutos();
		if(produtos != null) {
			for (Produto produto : produtos) {
				List<Ingrediente> ingredientes = produto.getIngredientes();
				if(ingredientes != null) {
					for (Ingrediente i : ingredientes) {
						if(!i.isDisponivel() || 
								produtoService.contaIngrediente(produto.getId(), i.getId()) > i.getQtd()) {
							
							produto.setDisponivel(false);
							break;
						}else {
							produto.setDisponivel(true);
						}
					}
					produtoService.salvar(produto);
				}
			}
		}
	}
	
}
