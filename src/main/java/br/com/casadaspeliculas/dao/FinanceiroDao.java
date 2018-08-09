package br.com.casadaspeliculas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.casadaspeliculas.entity.financeiro.Financeiro;

@Stateless
public class FinanceiroDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Financeiro financeiro) {
		if (financeiro.getIdFinanceiro() == null) {
			em.persist(financeiro);
		} else {
			em.merge(financeiro);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Financeiro> findAll() {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT f FROM Financeiro f");

		Query query = em.createQuery(hql.toString());

		return query.getResultList();
	}
}
