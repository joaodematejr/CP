package br.com.casadaspeliculas.service.veiculo;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.casadaspeliculas.dao.VeiculoDao;
import br.com.casadaspeliculas.entity.veiculo.Veiculo;

@Stateless
public class VeiculoServiceImpl implements VeiculoService {

	@Inject
	private VeiculoDao veiculoDao;

	@Override
	public void save(Veiculo veiculo) {
		veiculoDao.save(veiculo);
	}

	@Override
	public List<Veiculo> findAll() {
		return veiculoDao.findAll();
	}

}
