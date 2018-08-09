package br.com.casadaspeliculas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.casadaspeliculas.entity.veiculo.Modelo;

@Stateless
public class ModeloDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Modelo modelo) {
		try {
			if (modelo.getIdModelo() == null) {
				em.persist(modelo);
			} else {
				em.merge(modelo);
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Modelo salvo com sucesso !!!"));
		} catch (Exception e) {
			if (e.getMessage().contains("org.hibernate.exception.ConstraintViolationException: Duplicate")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no sistema", "Você está tentando salvar uma Modelo que já existe no sistema !!!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no sistema", "Erro desconhecido - Procure o administrador do sistema !!!"));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Modelo> findAll() {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT m FROM Modelo m LEFT JOIN FETCH m.marca ma ORDER BY m.deModelo ASC");

		Query query = em.createQuery(hql.toString());

		return query.getResultList();
	}

	public Modelo getModeloById(Long id) {
		return em.find(Modelo.class, id);
	}
}
