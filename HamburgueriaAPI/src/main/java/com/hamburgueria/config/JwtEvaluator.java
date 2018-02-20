package com.hamburgueria.config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hamburgueria.model.Usuario;
import com.hamburgueria.service.UsuarioService;
import com.hamburgueria.util.Constants;
import io.jsonwebtoken.Jwts;

@Component(value="jwtEvaluator")
public class JwtEvaluator {
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	private HttpServletRequest request;
	
	public Usuario usuarioToken() throws ServletException{
		String token = request.getHeader(Constants.HEADER_STRING);
		System.out.println("--jwtEvaluator-- Token: " + token);
        if (token != null) {
			String email = null;
			try {
				email = Jwts.parser()
						.setSigningKey(Constants.CHAVE_SECRETA)
						.parseClaimsJws(token.replace(Constants.TOKEN_PREFIX, ""))
						.getBody()
						.getSubject();
				System.out.println("--jwtEvaluator-- Email: " + email);
				return usuarioService.buscar(email);
			}catch (Exception e) {
				throw new ServletException("Token inválido");
			}
        }
        throw new ServletException("Token inválido");
	}

}
