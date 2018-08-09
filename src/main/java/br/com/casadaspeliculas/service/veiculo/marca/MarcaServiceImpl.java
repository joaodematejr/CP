package br.com.casadaspeliculas.service.veiculo.marca;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.casadaspeliculas.dao.MarcaDao;
import br.com.casadaspeliculas.entity.veiculo.Marca;

@Stateless
public class MarcaServiceImpl implements MarcaService {

	@Inject
	private MarcaDao marcaDao;

	@Override
	public void save(Marca marca) {
		marcaDao.save(marca);
	}

	@Override
	public List<Marca> findAll() {
		return marcaDao.findAll();
	}

	@Override
	public Marca findById(Long id) {
		return marcaDao.findById(id);
	}

}
