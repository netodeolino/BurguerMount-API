package com.hamburgueria.controller;

import java.util.Collections;
import java.util.Date;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hamburgueria.config.JwtEvaluator;
import com.hamburgueria.exceptions.TokenException;
import com.hamburgueria.model.Papel;
import com.hamburgueria.model.Token;
import com.hamburgueria.model.Usuario;
import com.hamburgueria.response.AuthToken;
import com.hamburgueria.response.MensagemRetorno;
import com.hamburgueria.response.Response;
import com.hamburgueria.response.SedeData;
import com.hamburgueria.response.UsuarioData;
import com.hamburgueria.service.UsuarioService;
import com.hamburgueria.util.Constants;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	JwtEvaluator jwtEvaluator;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	private static final long EXPIRATION_TIME = 1000 * Constants.TOKEN_EXPIRAR_MINUTOS;
	
	@PostMapping(path="/login")
	public ResponseEntity logar(@RequestBody Usuario usuario) {
		Response<AuthToken> response = new Response<AuthToken>();
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				usuario.getUsername(), 
				usuario.getPassword(), 
				Collections.emptyList()
		));
		try {
			String JWT = Jwts.builder()
					.setSubject(authentication.getName())
					.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS512, Constants.CHAVE_SECRETA)
					.compact();
			return ResponseEntity.ok(new AuthToken(Constants.TOKEN_PREFIX + " " + JWT));
		} catch (Exception e) {
			response.getErrors().add(Constants.ERRO_EMAIL_SENHA);
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity buscar() throws TokenException {
		Usuario usuarioLogado = jwtEvaluator.usuarioToken();
		
		UsuarioData userResponse = new UsuarioData(usuarioLogado.getId(), usuarioLogado.getNome(),
												   usuarioLogado.getEmail(), usuarioLogado.getCreditos());
		
		if (usuarioLogado.getDataNascimento() != null) {
			userResponse.setDataNascimento(usuarioLogado.getDataNascimento());
		}
		if (usuarioLogado.getTelefone() != null) {
			userResponse.setTelefone(usuarioLogado.getTelefone());
		}
		if (usuarioLogado.getSede() != null) {
			SedeData sedeData = new SedeData(usuarioLogado.getSede().getId(), usuarioLogado.getSede().getCidade());
			
			userResponse.setSede(sedeData);
		}
		if (usuarioLogado.getFoto64() != null) {
			userResponse.setFoto64(usuarioLogado.getFoto64());
		}
		return ResponseEntity.ok(userResponse);
	}
	
	@PostMapping
	public ResponseEntity cadastrar(@RequestBody @Valid Usuario usuario) {
		Response<MensagemRetorno> response = new Response<MensagemRetorno>();
		try {
			usuario.setPapel(Papel.CLIENTE);
			usuarioService.salvar(usuario);
			return ResponseEntity.ok(new MensagemRetorno(Constants.SUCESSO_CADASTRO_USUARIO));
		} catch (Exception e) {
			response.getErrors().add(Constants.ERRO_CADASTRO_USUARIO);
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	// Quais informações serão atualizadas? Criar métodos diferentes para cada tipo de atualização? Coisas a serem decididas!
	@PutMapping
	public ResponseEntity atualizar(@RequestBody Usuario usuario) {
		Response<MensagemRetorno> response = new Response<MensagemRetorno>();
		Usuario usuarioBanco = usuarioService.buscar(usuario.getEmail());
		if (usuarioService.compararSenha(usuario.getSenha(), usuarioBanco.getSenha())) {
			usuarioBanco.setNome(usuario.getNome());
			usuarioService.salvar(usuarioBanco);
			return ResponseEntity.ok(new MensagemRetorno(Constants.SUCESSO_ATUALIZAR_USUARIO));
		}
		response.getErrors().add(Constants.ERRO_EMAIL_SENHA);
		return ResponseEntity.badRequest().body(response);
	}
	
	@DeleteMapping
	public ResponseEntity excluir(@PathParam(value="email") String email) {
		Response<MensagemRetorno> response = new Response<MensagemRetorno>();
		Usuario usuarioBanco = usuarioService.buscar(email);
		try {
			usuarioService.excluir(usuarioBanco.getId());
			return ResponseEntity.ok(new MensagemRetorno(Constants.SUCESSO_EXCLUIR_USUARIO));
		} catch (Exception e) {
			response.getErrors().add(Constants.ERRO_ATUALIZAR_USUARIO);
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PostMapping(path="/validartoken")
	public ResponseEntity token(@RequestBody AuthToken authToken) {
		Response<AuthToken> response = new Response<AuthToken>();
		String token = authToken.getToken();
        if (token != null) {
        	String email = null;
        	try {
        		email = Jwts.parser()
								.setSigningKey(Constants.CHAVE_SECRETA)
								.parseClaimsJws(token.replace(Constants.TOKEN_PREFIX, ""))
								.getBody()
								.getSubject();
        	}catch (Exception e) {
        		response.getErrors().add(Constants.TOKEN_INVALIDO);
        		return ResponseEntity.badRequest().body(response);
        	}
			if(usuarioService.buscar(email) != null) {
				return ResponseEntity.ok(new AuthToken(Constants.TOKEN_VALIDO));
			}
			response.getErrors().add(Constants.ERRO_EMAIL_SENHA);
			return ResponseEntity.badRequest().body(response);
        }
        response.getErrors().add(Constants.TOKEN_INVALIDO);
		return ResponseEntity.badRequest().body(response);
	}
	
	@GetMapping(path="/atrusuario")
	public ResponseEntity<Boolean> atrUsuario() throws TokenException {
		Usuario usuarioLogado = jwtEvaluator.usuarioToken();
		if (usuarioLogado.getTelefone() == null || usuarioLogado.getDataNascimento() == null
				|| usuarioLogado.getFoto64() == null || usuarioLogado.getSede() == null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
}
