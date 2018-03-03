package com.hamburgueria.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hamburgueria.util.Constants;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason=Constants.ERRO_CADASTRO_USUARIO)
public class UsuarioException extends Exception {

private static final long serialVersionUID = 1L;
	
	public UsuarioException(String mensagem){
		super(mensagem);
	}
}
