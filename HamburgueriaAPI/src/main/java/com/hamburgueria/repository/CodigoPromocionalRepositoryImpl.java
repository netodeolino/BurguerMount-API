package com.hamburgueria.repository;

import javax.persistence.EntityManager;

import com.hamburgueria.model.CodigoPromocional;

public class CodigoPromocionalRepositoryImpl implements CodigoPromocionalRepositoryCustom {

	private EntityManager entityManager;

	/* Não está reconhecendo classes geradas */
	@Override
	public CodigoPromocional buscarPorCodigo(String codigo) {
		return null;
	}
}
