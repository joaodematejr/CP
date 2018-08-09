package br.com.casadaspeliculas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.casadaspeliculas.entity.veiculo.Veiculo;

@Stateless
public class VeiculoDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Veiculo veiculo) {
		try {
			if (veiculo.getIdVeiculo() == null) {
				em.persist(veiculo);
			} else {
				em.merge(veiculo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Veiculo> findAll() {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT v FROM Veiculo v JOIN FETCH v.modelo m JOIN FETCH m.marca ma ORDER BY m.deModelo ASC");

		Query query = em.createQuery(hql.toString());

		return query.getResultList();
	}
}
