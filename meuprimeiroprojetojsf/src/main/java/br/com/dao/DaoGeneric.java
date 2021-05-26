package br.com.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.jpautil.JPAUtil;

public class DaoGeneric<ObjetoGenerico> {

	public void salvar(ObjetoGenerico objetoGenerico) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(objetoGenerico);

		entityTransaction.commit();
		entityManager.close();
	}
}
