package br.com.casadaspeliculas.service.formapagamento;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.casadaspeliculas.dao.FormaPagamentoDao;
import br.com.casadaspeliculas.entity.formapagamento.FormaPagamento;

@Stateless
public class FormaPagamentoServiceImpl implements FormaPagamentoService {

	@Inject
	private FormaPagamentoDao formaPagamentoDao;

	@Override
	public void save(FormaPagamento formaPagamento) {
		formaPagamentoDao.save(formaPagamento);
	}

	@Override
	public List<FormaPagamento> findAll() {
		return formaPagamentoDao.findAll();
	}

	@Override
	public void delete(FormaPagamento fp) {
		formaPagamentoDao.delete(fp);
	}

	@Override
	public FormaPagamento getFormaPagamentoById(Long id) {
		return formaPagamentoDao.getFormaPagamentoById(id);
	}

}
