package com.hamburgueria.response;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UsuarioData {

	private Long id;
	private String nome;
	private String telefone;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	private String email;
	private int creditos;
	private String sede;

	public UsuarioData() {
	}

	public UsuarioData(Long id, String nome, String telefone, Date dataNascimento,
			String email, int creditos, String sede) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.creditos = creditos;
		this.sede = sede;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}
}
