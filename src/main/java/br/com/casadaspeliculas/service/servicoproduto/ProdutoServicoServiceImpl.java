package br.com.casadaspeliculas.service.servicoproduto;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.casadaspeliculas.dao.ProdutoServicoDao;
import br.com.casadaspeliculas.entity.servico.ProdutoServico;
import br.com.casadaspeliculas.entity.servico.Servico;

@Stateless
public class ProdutoServicoServiceImpl implements ProdutoServicoService {

	@Inject
	private ProdutoServicoDao produtoServicoDao;

	@Override
	public List<ProdutoServico> findAllById(Servico servico) {
		return this.produtoServicoDao.findAllById(servico);
	}

}
