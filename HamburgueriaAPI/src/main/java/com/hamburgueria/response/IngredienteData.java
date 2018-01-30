package com.hamburgueria.response;

public class IngredienteData {
	
	private Long id;
	private String nome;
	private Integer qtd;
	private Double valorDeVenda;
	private String foto64;
	private String tipoIngrediente;
	
	public IngredienteData() {
	}

	public IngredienteData(Long id, String nome, Integer qtd, Double valorDeVenda, String foto64,
			String tipoIngrediente) {
		super();
		this.id = id;
		this.nome = nome;
		this.qtd = qtd;
		this.valorDeVenda = valorDeVenda;
		this.foto64 = foto64;
		this.tipoIngrediente = tipoIngrediente;
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

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public Double getValorDeVenda() {
		return valorDeVenda;
	}

	public void setValorDeVenda(Double valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}

	public String getFoto64() {
		return foto64;
	}

	public void setFoto64(String foto64) {
		this.foto64 = foto64;
	}

	public String getTipoIngrediente() {
		return tipoIngrediente;
	}

	public void setTipoIngrediente(String tipoIngrediente) {
		this.tipoIngrediente = tipoIngrediente;
	}
	
}
