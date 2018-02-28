package com.hamburgueria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hamburgueria.util.Constants;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason=Constants.TOKEN_INVALIDO)
public class TokenException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public TokenException(String mensagem){
		super(mensagem);
	}
}
