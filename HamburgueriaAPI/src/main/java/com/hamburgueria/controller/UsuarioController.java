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
import com.hamburgueria.exceptions.UsuarioException;
import com.hamburgueria.model.Papel;
import com.hamburgueria.model.Usuario;
import com.hamburgueria.response.AuthToken;
import com.hamburgueria.response.MensagemRetorno;
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
	public AuthToken logar(@RequestBody Usuario usuario){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				usuario.getUsername(), 
				usuario.getPassword(), 
				Collections.emptyList()
		));
		String JWT = Jwts.builder()
				.setSubject(authentication.getName())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, Constants.CHAVE_SECRETA)
				.compact();
		return new AuthToken(Constants.TOKEN_PREFIX + " " + JWT);
	}
	
	@GetMapping
	public ResponseEntity<UsuarioData> buscar() throws TokenException {
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
		return new ResponseEntity<UsuarioData>(userResponse, HttpStatus.OK);
	}
	
	@PostMapping
	public MensagemRetorno cadastrar(@RequestBody @Valid Usuario usuario) throws UsuarioException {
		try {
			usuario.setPapel(Papel.CLIENTE);
			usuarioService.salvar(usuario);
			return new MensagemRetorno(Constants.SUCESSO_CADASTRO_USUARIO);
		} catch (Exception e) {
			throw new UsuarioException(Constants.ERRO_CADASTRO_USUARIO);
		}
	}
	
	// Quais informações serão atualizadas? Criar métodos diferentes para cada tipo de atualização? Coisas a serem decididas!
	@PutMapping
	public MensagemRetorno atualizar(@RequestBody Usuario usuario) {
		Usuario usuarioBanco = usuarioService.buscar(usuario.getEmail());
		if (usuarioService.compararSenha(usuario.getSenha(), usuarioBanco.getSenha())) {
			usuarioBanco.setNome(usuario.getNome());
			usuarioService.salvar(usuarioBanco);
			return new MensagemRetorno(Constants.SUCESSO_ATUALIZAR_USUARIO);
		}
		return new MensagemRetorno(Constants.ERRO_EMAIL_SENHA);
	}
	
	@DeleteMapping
	public MensagemRetorno excluir(@PathParam(value="email") String email) {
		Usuario usuarioBanco = usuarioService.buscar(email);
		usuarioService.excluir(usuarioBanco.getId());
		return new MensagemRetorno(Constants.SUCESSO_EXCLUIR_USUARIO);
	}
	
	@PostMapping(path="/validartoken")
	public MensagemRetorno token(@RequestBody AuthToken authToken) throws TokenException {
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
        		throw new TokenException(Constants.TOKEN_INVALIDO);
        	}
			if(usuarioService.buscar(email) != null) {
				return new MensagemRetorno(Constants.TOKEN_VALIDO);
			}
			return new MensagemRetorno(Constants.ERRO_EMAIL_SENHA);
        }
        throw new TokenException(Constants.TOKEN_INVALIDO);
	}
}
