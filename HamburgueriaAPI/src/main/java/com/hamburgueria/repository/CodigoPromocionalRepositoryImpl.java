package com.hamburgueria.repository;

import javax.persistence.EntityManager;

import com.hamburgueria.model.CodigoPromocional;
import com.hamburgueria.model.QCodigoPromocional;

import com.mysema.query.jpa.impl.JPAQuery;

public class CodigoPromocionalRepositoryImpl implements CodigoPromocionalRepositoryCustom {

	private EntityManager entityManager;

	/* - Documentação
	 * http://www.querydsl.com/static/querydsl/latest/reference/html/ch02.html#jpa_integration
	 */
	@Override
	public CodigoPromocional buscarPorCodigo(String codigo) {
		QCodigoPromocional  qCodigoPromocional = QCodigoPromocional.codigoPromocional;
		JPAQuery query = new JPAQuery(entityManager).from(qCodigoPromocional);
		query.where(qCodigoPromocional.codigo.eq(codigo));
		return query.singleResult(qCodigoPromocional);
	}
}
