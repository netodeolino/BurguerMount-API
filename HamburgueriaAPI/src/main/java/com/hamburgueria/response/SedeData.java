package com.hamburgueria.response;

public class SedeData {

	private Long id;
	
	private String cidade;
	
	public SedeData (Long id, String cidade) {
		this.id = id;
		this.cidade = cidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}
