package br.com.casadaspeliculas.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.casadaspeliculas.common.ConversorDatasUtil;
import br.com.casadaspeliculas.dto.RelFechamentoCaixaDTO;
import br.com.casadaspeliculas.entity.servico.Servico;

@Stateless
public class RelatorioDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<RelFechamentoCaixaDTO> findFechamentoByPeriodo(Map<String, String[]> parametros) {
		Date dtInicial = null;
		Date dtFinal = null;
		try {
			dtInicial = ConversorDatasUtil.getDataInicial(ConversorDatasUtil.formataData(parametros.get("dtInicial")[0]));
			dtFinal = ConversorDatasUtil.getDataFinal(ConversorDatasUtil.formataData(parametros.get("dtFinal")[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Query query = em.createNamedQuery("RelatorioFechamentoCaixa");
		query.setParameter("dataInicial", dtInicial);
		query.setParameter("dataFinal", dtFinal);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		List<RelFechamentoCaixaDTO> lista = new ArrayList<RelFechamentoCaixaDTO>();
		for (Object[] objects : result) {
			RelFechamentoCaixaDTO obj = new RelFechamentoCaixaDTO();
			obj.setDeFormaPgto((String)objects[0]);
			obj.setVlTotalFormaPgto((Double)objects[1]);
			lista.add(obj);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<Servico> findFechamentoAnaliticoByPeriodo(Map<String, String[]> parametros) {
		Date dtInicial = null;
		Date dtFinal = null;
		try {
			dtInicial = ConversorDatasUtil.getDataInicial(ConversorDatasUtil.formataData(parametros.get("dtInicial")[0]));
			dtFinal = ConversorDatasUtil.getDataFinal(ConversorDatasUtil.formataData(parametros.get("dtFinal")[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		StringBuilder hql = new StringBuilder();
		hql.append("FROM Servico s ");
//		hql.append("JOIN s.financeiro fi ");
//		hql.append("JOIN fi.formaPagamento fp ");
		hql.append("WHERE ((s.dtServico BETWEEN :dataInicial AND :dataFinal AND s.financeiro.statusFinanceiro in ('F','A')) ");
		hql.append("OR (s.financeiro.dtPagamento BETWEEN :dataInicial AND :dataFinal AND s.financeiro.statusFinanceiro in ('P'))) ");
		hql.append("AND s.financeiro.statusFinanceiro <> 'C' ");
		hql.append("ORDER BY s.dtServico ");
		Query query = em.createQuery(hql.toString());
		query.setParameter("dataInicial", dtInicial);
		query.setParameter("dataFinal", dtFinal);
		return (List<Servico>) query.getResultList();
	}

}
