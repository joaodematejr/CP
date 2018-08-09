package br.com.casadaspeliculas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.casadaspeliculas.entity.formapagamento.FormaPagamento;

@Stateless
public class FormaPagamentoDao {

	@PersistenceContext
	private EntityManager em;

	public void save(FormaPagamento formaPagamento) {
		try {
			if (formaPagamento.getIdFormaPgto() == null) {
				em.persist(formaPagamento);
			} else {
				em.merge(formaPagamento);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Forma de Pagamento salva com sucesso !!!"));
			}
		} catch (Exception e) {
			if (e.getMessage().contains("org.hibernate.exception.ConstraintViolationException: Duplicate")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no sistema", "Você está tentando salvar uma Forma de Pagamento que já existe no sistema !!!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no sistema", "Erro desconhecido - Procure o administrador do sistema !!!"));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<FormaPagamento> findAll() {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT f FROM FormaPagamento f ORDER BY f.deFormaPgto ASC");

		Query query = em.createQuery(hql.toString());

		return query.getResultList();
	}

	public void delete(FormaPagamento fp) {
		try {
			em.remove(em.merge(fp));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Forma de Pagamento removida com sucesso !!!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no sistema", "Erro desconhecido - Procure o administrador do sistema !!!"));
		}
	}

	public FormaPagamento getFormaPagamentoById(Long id) {
		return em.find(FormaPagamento.class, id);
	}

}
