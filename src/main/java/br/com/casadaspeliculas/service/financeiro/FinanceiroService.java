package br.com.casadaspeliculas.service.financeiro;

import java.util.List;

import javax.ejb.Local;

import br.com.casadaspeliculas.entity.financeiro.Financeiro;

@Local
public interface FinanceiroService {

	void save(Financeiro financeiro);

	List<Financeiro> findAll();

}
