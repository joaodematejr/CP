package br.com.casadaspeliculas.service.veiculo.modelo;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.casadaspeliculas.dao.ModeloDao;
import br.com.casadaspeliculas.entity.veiculo.Modelo;

@Stateless
public class ModeloServiceImpl implements ModeloService {

	@Inject
	private ModeloDao modeloDao;

	@Override
	public void save(Modelo modelo) {
		modeloDao.save(modelo);
	}

	@Override
	public List<Modelo> findAll() {
		return modeloDao.findAll();
	}

	@Override
	public Modelo getModeloById(Long id) {
		return modeloDao.getModeloById(id);
	}

}
