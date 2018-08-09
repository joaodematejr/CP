package br.com.casadaspeliculas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.casadaspeliculas.entity.cliente.Cliente;

@Stateless
public class ClienteDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Cliente cliente) {
		try {
			if (cliente.getIdCliente() == null) {
				em.persist(cliente);
			} else {
				em.merge(cliente);
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cliente salvo com sucesso !!!"));
		} catch (Exception e) {
			if (e.getMessage().contains("org.hibernate.exception.ConstraintViolationException: Duplicate")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no sistema", "Você está tentando salvar um Cliente que já existe no sistema !!!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no sistema", "Erro desconhecido - Procure o administrador do sistema !!!"));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findAll() {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT c FROM Cliente c JOIN FETCH c.contato ct JOIN FETCH c.endereco ed ORDER BY c.nmCliente, c.nuCliente ASC");

		Query query = em.createQuery(hql.toString());

		return query.getResultList();
	}

	public Cliente getClienteById(Long id) {
		return em.find(Cliente.class, id);
	}

	public List<Cliente> findAllCombo() {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT c FROM Cliente c ORDER BY c.nmCliente, c.nuCliente ASC");

		Query query = em.createQuery(hql.toString());

		return query.getResultList();
	}
}
