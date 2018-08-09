package br.com.casadaspeliculas.service.financeiro;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.casadaspeliculas.dao.FinanceiroDao;
import br.com.casadaspeliculas.entity.financeiro.Financeiro;

@Stateless
public class FinanceiroServiceImpl implements FinanceiroService {

	@Inject
	private FinanceiroDao financeiroDao;

	@Override
	public void save(Financeiro financeiro) {
		financeiroDao.save(financeiro);
	}

	@Override
	public List<Financeiro> findAll() {
		return financeiroDao.findAll();
	}

}
