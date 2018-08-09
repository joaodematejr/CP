package br.com.casadaspeliculas.service.servicoproduto;

import java.util.List;

import javax.ejb.Local;

import br.com.casadaspeliculas.entity.servico.ProdutoServico;
import br.com.casadaspeliculas.entity.servico.Servico;

@Local
public interface ProdutoServicoService {

	List<ProdutoServico> findAllById(Servico servico);

}
