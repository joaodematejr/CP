package br.com.casadaspeliculas.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.casadaspeliculas.common.ConversorDatasUtil;
import br.com.casadaspeliculas.entity.senha.Senhas;
import br.com.casadaspeliculas.entity.servico.Servico;
import br.com.casadaspeliculas.entity.veiculo.Marca;
import br.com.casadaspeliculas.entity.veiculo.Modelo;

@Stateless
public class ServicoDao {

	@PersistenceContext
	private EntityManager em;

	public Servico findById(Long idServico){
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Servico s WHERE s.idServico = :idServico");
		Query query = em.createQuery(hql.toString());
		query.setParameter("idServico", idServico);
		return (Servico) query.getSingleResult();
	}
	
	public void save(Servico servico) {
		try {
			if (servico.getIdServico() == null) {
				em.persist(servico);
			} else {
				em.merge(servico);
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Serviço salvo com sucesso !!!"));
		} catch (Exception e) {
			if (e.getMessage().contains("org.hibernate.exception.ConstraintViolationException: Duplicate")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no sistema", "Você está tentando salvar um Serviço que já existe no sistema !!!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no sistema", "Erro desconhecido - Procure o administrador do sistema !!!"));
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Servico> findAll() {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Servico s JOIN FETCH s.cliente c JOIN FETCH s.financeiro f JOIN FETCH f.formaPagamento fo JOIN FETCH s.veiculo v JOIN FETCH v.modelo m  JOIN FETCH m.marca ma ORDER BY s.dtServico");

		Query query = em.createQuery(hql.toString());

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Servico> findAllByDay(Date dataAtual) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Servico ser JOIN FETCH ser.financeiro fin JOIN FETCH fin.formaPagamento for JOIN FETCH ser.cliente cli JOIN FETCH ser.veiculo vei JOIN FETCH vei.modelo mod JOIN FETCH mod.marca mar WHERE ser.dtServico BETWEEN :dataAtualInicial AND :dataAtualFinal ORDER BY ser.dtServico");

		Query query = em.createQuery(hql.toString());

		query.setParameter("dataAtualInicial", ConversorDatasUtil.getDataInicial(dataAtual));
		query.setParameter("dataAtualFinal", ConversorDatasUtil.getDataFinal(dataAtual));

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Marca> getMarcas() {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Marca m ");

		Query query = em.createQuery(hql.toString());

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Modelo> getModelos(Long idMarca) {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Modelo m WHERE m.marca.idMarca = :idMarca ");

		Query query = em.createQuery(hql.toString());
		
		query.setParameter("idMarca", idMarca);

		return query.getResultList();
	}

	public Senhas getSenhas() {
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Senhas s WHERE s.idSenha = 1");
		Query query = em.createQuery(hql.toString());
		return (Senhas) query.getSingleResult();
	}
}
