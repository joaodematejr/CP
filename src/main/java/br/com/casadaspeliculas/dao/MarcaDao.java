package br.com.casadaspeliculas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.casadaspeliculas.entity.veiculo.Marca;

@Stateless
public class MarcaDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Marca marca) {
		try {
			if (marca.getIdMarca() == null) {
				em.persist(marca);
			} else {
				em.merge(marca);
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Marca salva com sucesso !!!"));
		} catch (Exception e) {
			if (e.getMessage().contains("org.hibernate.exception.ConstraintViolationException: Duplicate")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no sistema", "Você está tentando salvar uma Marca que já existe no sistema !!!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no sistema", "Erro desconhecido - Procure o administrador do sistema !!!"));
			}
		}
	}
	
	public Marca findById(Long id) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT m FROM Marca m WHERE m.idMarca = :idMarca ORDER BY m.deMarca ASC");

		Query query = em.createQuery(hql.toString());
		query.setParameter("idMarca", id);
		return (Marca) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Marca> findAll() {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT m FROM Marca m ORDER BY m.deMarca ASC");

		Query query = em.createQuery(hql.toString());

		return query.getResultList();
	}
}
