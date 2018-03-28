package com.hamburgueria.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CodigoPromocional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codigo;
	private Integer creditos;
	private Integer maxUso;
	
	@OneToMany
	private List<Usuario> usuarios;
	
	public CodigoPromocional() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Integer getCreditos() {
		return creditos;
	}
	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}
	public Integer getMaxUso() {
		return maxUso;
	}
	public void setMaxUso(Integer maxUso) {
		this.maxUso = maxUso;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public String gerarCodigo() {		
		String uuid = UUID.randomUUID().toString();
		String codigo = String.valueOf(uuid);
		return codigo.substring(0, 6).toUpperCase();
	}

	@Override
	public String toString() {
		return "CodigoPromocional [id=" + id + ", codigo=" + codigo + ", creditos=" + creditos + ", maxUso=" + maxUso
				+ ", usuarios=" + usuarios + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((creditos == null) ? 0 : creditos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((maxUso == null) ? 0 : maxUso.hashCode());
		result = prime * result + ((usuarios == null) ? 0 : usuarios.hashCode());
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
		CodigoPromocional other = (CodigoPromocional) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (creditos == null) {
			if (other.creditos != null)
				return false;
		} else if (!creditos.equals(other.creditos))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maxUso == null) {
			if (other.maxUso != null)
				return false;
		} else if (!maxUso.equals(other.maxUso))
			return false;
		if (usuarios == null) {
			if (other.usuarios != null)
				return false;
		} else if (!usuarios.equals(other.usuarios))
			return false;
		return true;
	}
	
}
