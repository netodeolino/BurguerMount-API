package com.hamburgueria.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.hamburgueria.util.Constants;

@Entity
public class Sede {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cidade;
	
	@Column(columnDefinition = "text", length = Constants.TAM_MAX_IMG_64)
	private String foto64;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<TipoIngrediente> tipoIngredientes;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Ingrediente> estoque;
	
	@OneToMany
	private List<Pedido> pedidos;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Produto> produtos;
	
	@OneToMany
	private List<Usuario> clientes;

	public Sede() {
		
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

	public String getFoto64() {
		return foto64;
	}

	public void setFoto64(String foto64) {
		this.foto64 = foto64;
	}

	public List<Ingrediente> getEstoque() {
		return estoque;
	}

	public void setEstoque(List<Ingrediente> estoque) {
		this.estoque = estoque;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Usuario> getClientes() {
		return clientes;
	}

	public void setClientes(List<Usuario> clientes) {
		this.clientes = clientes;
	}

	public List<TipoIngrediente> getTipoIngredientes() {
		return tipoIngredientes;
	}

	public void setTipoIngredientes(List<TipoIngrediente> tipoIngredientes) {
		this.tipoIngredientes = tipoIngredientes;
	}

	@Override
	public String toString() {
		return "Sede [id=" + id + ", cidade=" + cidade + ", tipoIngredientes=" + tipoIngredientes + ", estoque="
				+ estoque + ", pedidos=" + pedidos + ", produtos=" + produtos + ", clientes=" + clientes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((clientes == null) ? 0 : clientes.hashCode());
		result = prime * result + ((estoque == null) ? 0 : estoque.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pedidos == null) ? 0 : pedidos.hashCode());
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
		result = prime * result + ((tipoIngredientes == null) ? 0 : tipoIngredientes.hashCode());
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
		Sede other = (Sede) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (clientes == null) {
			if (other.clientes != null)
				return false;
		} else if (!clientes.equals(other.clientes))
			return false;
		if (estoque == null) {
			if (other.estoque != null)
				return false;
		} else if (!estoque.equals(other.estoque))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pedidos == null) {
			if (other.pedidos != null)
				return false;
		} else if (!pedidos.equals(other.pedidos))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		if (tipoIngredientes == null) {
			if (other.tipoIngredientes != null)
				return false;
		} else if (!tipoIngredientes.equals(other.tipoIngredientes))
			return false;
		return true;
	}
	
}
