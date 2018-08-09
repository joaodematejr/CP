package br.com.casadaspeliculas.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.casadaspeliculas.entity.produto.Produto;

@Stateless
public class ProdutoDao {

	@PersistenceContext
	private EntityManager em;

	public void save(Produto produto) {
		try {
			if (produto.getIdProduto() == null) {
				em.persist(produto);
			} else {
				em.merge(produto);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Produto salvo com sucesso !!!"));
			}
		} catch (Exception e) {
			if (e.getMessage().contains("org.hibernate.exception.ConstraintViolationException: Duplicate")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no sistema", "Você está tentando salvar uma Produto que já existe no sistema !!!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no sistema", "Erro desconhecido - Procure o administrador do sistema !!!"));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> findAll() {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT p FROM Produto p ORDER BY p.deProduto ASC");

		Query query = em.createQuery(hql.toString());

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Produto> findProdutosAtivos() {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT p FROM Produto p WHERE P.statusProduto = 'A' ORDER BY p.deProduto ASC");

		Query query = em.createQuery(hql.toString());

		return query.getResultList();
	}

	public Produto findProdutoById(Long id){
		return em.find(Produto.class, id);
	}
	
}
