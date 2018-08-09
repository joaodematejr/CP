package br.com.casadaspeliculas.service.formapagamento;

import java.util.List;

import javax.ejb.Local;

import br.com.casadaspeliculas.entity.formapagamento.FormaPagamento;

@Local
public interface FormaPagamentoService {

	void save(FormaPagamento formaPagamento);
	
	List<FormaPagamento> findAll();

	void delete(FormaPagamento fp);

	FormaPagamento getFormaPagamentoById(Long id);

}
