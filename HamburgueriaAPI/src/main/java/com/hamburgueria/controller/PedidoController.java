package com.hamburgueria.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hamburgueria.config.JwtEvaluator;
import com.hamburgueria.exceptions.TokenException;
import com.hamburgueria.model.Ingrediente;
import com.hamburgueria.model.Papel;
import com.hamburgueria.model.Pedido;
import com.hamburgueria.model.Produto;
import com.hamburgueria.model.Sede;
import com.hamburgueria.model.Status;
import com.hamburgueria.model.Usuario;
import com.hamburgueria.response.MensagemRetorno;
import com.hamburgueria.response.Response;
import com.hamburgueria.service.IngredienteService;
import com.hamburgueria.service.PedidoService;
import com.hamburgueria.service.ProdutoService;
import com.hamburgueria.service.SedeService;
import com.hamburgueria.util.Constants;
import com.hamburgueria.util.Mensagem;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	SedeService sedeService;
	
	@Autowired
	IngredienteService ingredienteService;
	
	@Autowired
	IngredienteController ingredienteController;
	
	@Autowired
	JwtEvaluator jwtEvaluator;
	
	//Função que salva o pedido cadastrado.
	@PostMapping
	public ResponseEntity cadastrarPedido(@Valid Pedido pedido) throws TokenException {
		Response<MensagemRetorno> response = new Response<MensagemRetorno>();
		Usuario usuarioLogado = jwtEvaluator.usuarioToken();
		
		pedido.setCliente(usuarioLogado);
		
		//Adiciona a data do pedido a data em que o pedido foi criado.
		Date today = new Date();
		pedido.setData(today);
		pedido.setPreco(pedido.getPreco());
		pedido.setStatus(Status.EM_ABERTO);
		pedido.setSede(usuarioLogado.getSede());
		
		try {
			Pedido pedidoSalvo = pedidoService.salvar(pedido);
			//Adicionao o pedido a lista de pedidos da sede do usuário logado.
			this.adicionarPedidoSede(pedidoSalvo, usuarioLogado.getSede());
			
			return ResponseEntity.ok(new MensagemRetorno(Constants.SUCESSO_CADASTRO_PEDIDO));
		} catch (Exception e) {
			response.getErrors().add(Constants.ERRO_CADASTRO_PEDIDO);
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PutMapping
	public ResponseEntity editarPedido(@Valid Pedido pedido) throws TokenException {
		Usuario usuarioLogado = jwtEvaluator.usuarioToken();
		
		Pedido pedidoBanco = pedidoService.buscar(pedido.getId(), usuarioLogado.getSede().getId());
		pedidoBanco.setLocal(pedido.getLocal());
		pedidoBanco.setTroco(pedido.getTroco());
		pedidoBanco.setMensagem(pedido.getMensagem());
		Date today = new Date();
		pedidoBanco.setData(today);
		
		//Altera a quantidade de todos os ingredientes presentes no pedido.
		this.debitaIngredientes(pedidoBanco);
		
		pedidoService.salvar(pedidoBanco);
		return ResponseEntity.ok(new MensagemRetorno(Constants.SUCESSO_ATUALIZAR_PEDIDO));
	}
	
	//Função que exclui um pedido.
	@DeleteMapping(path="/{id}")
	public ResponseEntity excluirPedido(@PathVariable("id") Long id) throws TokenException {
		Response<MensagemRetorno> response = new Response<MensagemRetorno>();
		Usuario usuarioLogado = jwtEvaluator.usuarioToken();
		Pedido pedido = pedidoService.buscar(id, usuarioLogado.getSede().getId());		
		
		//Verifica se o status era EM_ABERTO ou o papel do usuário é MASTER ou ADM, pois apenas pedidos em aberto podem ser editados.
		if(pedido.getStatus().equals(Status.EM_ABERTO)
				|| usuarioLogado.getPapel().equals(Papel.ADMINISTRADOR)
				|| usuarioLogado.getPapel().equals(Papel.MASTER)) {
			
			try {
				pedidoService.excluir(id);
				return ResponseEntity.ok(new MensagemRetorno(Constants.SUCESSO_EXCLUIR_PEDIDO));
			} catch (Exception e) {
				response.getErrors().add(Constants.ERRO_EXCLUIR_PEDIDO);
				return ResponseEntity.badRequest().body(response);
			}
		}
		response.getErrors().add(Constants.ERRO_EXCLUIR_PEDIDO);
		return ResponseEntity.badRequest().body(response);
	}
	
	/*Função que retorna para a página "formAdicionarLanchesProntos" uma lista de produtos disponíveis e um pedido novo.
	 * O pedido enviado vai com o status, preço e sede preenchidos previamente.
	 */
	@GetMapping(path="/lanches_prontos")
	public ResponseEntity<List<Produto>> lanchesProntos() throws TokenException {
		Usuario usuarioLogado = jwtEvaluator.usuarioToken();
		
		List<Produto> produtos = produtoService.listarDisponiveis(usuarioLogado.getSede().getId());
		Pedido pedido = new Pedido();
		Date today = new Date();
		pedido.setData(today);
		pedido.setStatus(Status.EM_ABERTO);
		pedido.setPreco(0.0);
		pedido.setSede(usuarioLogado.getSede());
		pedido.setTroco(0.0);
		
		//Escolhe uma mensagem aleatória e adiciona ao pedido.po
		Mensagem mensagem = new Mensagem();
		pedido.setMensagem(mensagem.getMensagem());
		
		pedidoService.salvar(pedido);
		return ResponseEntity.ok(produtos);
	}

	/*Função que retorna para a página "listarPedidos"
	 * uma lista de todos os pedidos da sede do usuário logado.
	*/
	@GetMapping(path="/listar/todos")
	public ResponseEntity<List<Pedido>> listarPedidos() throws TokenException {
		Usuario usuarioLogado = jwtEvaluator.usuarioToken();
		List<Pedido> pedidos = pedidoService.listarTodos(usuarioLogado.getSede().getId());
		return ResponseEntity.ok(pedidos);
	}
	
	/*Função que retorna para a página "listarPedidos"
	 * uma lista de todos os pedidos em aberto da sede do usuário logado.
	*/
	@GetMapping(path="/listar/em_aberto")
	public ResponseEntity<List<Pedido>> listarPedidosEmAberto() throws TokenException {
		Usuario usuarioLogado = jwtEvaluator.usuarioToken();
		List<Pedido> pedidos = pedidoService.listarEmAberto(usuarioLogado.getSede().getId());
		return ResponseEntity.ok(pedidos);
	}
	
	/*Função que retorna para a página "listarPedidos"
	 * uma lista de todos os pedidos em andamento da sede do usuário logado.
	*/
	@GetMapping(path="/listar/em_andamento")
	public ResponseEntity<List<Pedido>> listarPedidosEmAndamento() throws TokenException {
		Usuario usuarioLogado = jwtEvaluator.usuarioToken();
		List<Pedido> pedidos = pedidoService.listarEmAndamento(usuarioLogado.getSede().getId());
		return ResponseEntity.ok(pedidos);
	}
	
	/*Função que retorna para a página "listarPedidos"
	 * uma lista de todos os pedidos prontos da sede do usuário logado.
	*/
	@GetMapping(path="/listar/prontos")
	public ResponseEntity<List<Pedido>> listarPedidosProntos() throws TokenException {
		Usuario usuarioLogado = jwtEvaluator.usuarioToken();
		List<Pedido> pedidos = pedidoService.listarProntos(usuarioLogado.getSede().getId());		
		return ResponseEntity.ok(pedidos);
	}
	
	/*Função que retorna para a página "listarPedidos"
	 * uma lista de todos os pedidos entregues da sede do usuário logado.
	*/
	@GetMapping(path="/listar/entregues")
	public ResponseEntity<List<Pedido>> listarPedidosEntregues() throws TokenException {
		Usuario usuarioLogado = jwtEvaluator.usuarioToken();
		List<Pedido> pedidos = pedidoService.listarEntregues(usuarioLogado.getSede().getId());	
		return ResponseEntity.ok(pedidos);
	}
	
	//Adiciona o pedido a lista de pedidos de uma sede.
	public void adicionarPedidoSede(Pedido pedido, Sede sede) {
		List<Pedido> pedidos =  sede.getPedidos();
			if(!pedidos.contains(pedido)) {
			pedidos.add(pedido);
			sede.setPedidos(pedidos);
			sedeService.salvar(sede);
		}
	}
	
	//Debita os ingredientes do estoque com base na quantidade usada no pedido.
	public void debitaIngredientes(Pedido pedido) {
		List<Ingrediente> ingredientes = ingredienteService.getIngredientes(pedido.getId());
		for (Ingrediente ingrediente : ingredientes) {
			ingrediente.setQtd(ingrediente.getQtd() - pedidoService.contaIngredientes(pedido.getId(), ingrediente.getId()));
			ingredienteService.salvar(ingrediente);
			ingredienteController.verificaDisponibilidade(ingrediente);
		}
	}
}
