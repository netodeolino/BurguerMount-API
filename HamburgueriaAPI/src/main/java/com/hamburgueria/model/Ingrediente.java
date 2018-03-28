package com.hamburgueria.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.hamburgueria.util.Constants;

@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private Integer qtd;
	private String porcao;
	private Double valorDeVenda;
	private Double valorBruto;
	
	@NotNull
	private boolean disponivel;
	
	@Column(columnDefinition = "text", length = Constants.TAM_MAX_IMG_64)
	private String foto64;
	
	@ManyToOne
	private Sede sede;
	
	@NotNull
	@ManyToOne
	private TipoIngrediente tipoIngrediente;
	
	@ManyToMany
	private List<Produto> produtos;

	public Ingrediente() {
		
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

	public String getPorcao() {
		return porcao;
	}

	public void setPorcao(String porcao) {
		this.porcao = porcao;
	}

	public Double getValorDeVenda() {
		return valorDeVenda;
	}

	public void setValorDeVenda(Double valorDeVenda) {
		this.valorDeVenda = valorDeVenda;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public String getFoto64() {
		return foto64;
	}

	public void setFoto64(String foto64) {
		this.foto64 = foto64;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public TipoIngrediente getTipoIngrediente() {
		return tipoIngrediente;
	}

	public void setTipoIngrediente(TipoIngrediente tipoIngrediente) {
		this.tipoIngrediente = tipoIngrediente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nome=" + nome + ", qtd=" + qtd + ", porcao=" + porcao + ", valorDeVenda="
				+ valorDeVenda + ", valorBruto=" + valorBruto + ", disponivel=" + disponivel + ", sede=" + sede
				+ ", tipoIngrediente=" + tipoIngrediente + ", produtos=" + produtos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (disponivel ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((porcao == null) ? 0 : porcao.hashCode());
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
		result = prime * result + ((qtd == null) ? 0 : qtd.hashCode());
		result = prime * result + ((sede == null) ? 0 : sede.hashCode());
		result = prime * result + ((tipoIngrediente == null) ? 0 : tipoIngrediente.hashCode());
		result = prime * result + ((valorBruto == null) ? 0 : valorBruto.hashCode());
		result = prime * result + ((valorDeVenda == null) ? 0 : valorDeVenda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		if (disponivel != other.disponivel)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (porcao == null) {
			if (other.porcao != null)
				return false;
		} else if (!porcao.equals(other.porcao))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		if (qtd == null) {
			if (other.qtd != null)
				return false;
		} else if (!qtd.equals(other.qtd))
			return false;
		if (sede == null) {
			if (other.sede != null)
				return false;
		} else if (!sede.equals(other.sede))
			return false;
		if (tipoIngrediente == null) {
			if (other.tipoIngrediente != null)
				return false;
		} else if (!tipoIngrediente.equals(other.tipoIngrediente))
			return false;
		if (valorBruto == null) {
			if (other.valorBruto != null)
				return false;
		} else if (!valorBruto.equals(other.valorBruto))
			return false;
		if (valorDeVenda == null) {
			if (other.valorDeVenda != null)
				return false;
		} else if (!valorDeVenda.equals(other.valorDeVenda))
			return false;
		return true;
	}
}
