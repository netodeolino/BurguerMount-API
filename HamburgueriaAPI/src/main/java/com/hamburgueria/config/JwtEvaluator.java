package com.hamburgueria.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hamburgueria.exceptions.TokenException;
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
	
	public Usuario usuarioToken() throws TokenException {
		String token = request.getHeader(Constants.HEADER_STRING);
        if (token != null) {
			String email = null;
			try {
				email = Jwts.parser()
						.setSigningKey(Constants.CHAVE_SECRETA)
						.parseClaimsJws(token.replace(Constants.TOKEN_PREFIX, ""))
						.getBody()
						.getSubject();
				return usuarioService.buscar(email);
			}catch (Exception e) {
				throw new TokenException(Constants.TOKEN_INVALIDO);
			}
        }
        throw new TokenException(Constants.TOKEN_INVALIDO);
	}
}
