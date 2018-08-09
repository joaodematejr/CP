package br.com.casadaspeliculas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.casadaspeliculas.entity.servico.ProdutoServico;
import br.com.casadaspeliculas.entity.servico.Servico;

@Stateless
public class ProdutoServicoDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<ProdutoServico> findAllById(Servico servico) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT ps FROM ProdutoServico ps JOIN FETCH ps.produto WHERE ps.servico = :servico ");

		Query query = em.createQuery(hql.toString());

		query.setParameter("servico", servico);

		return query.getResultList();
	}

}
