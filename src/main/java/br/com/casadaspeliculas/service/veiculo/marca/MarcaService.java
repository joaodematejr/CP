package br.com.casadaspeliculas.service.veiculo.marca;

import java.util.List;

import javax.ejb.Local;

import br.com.casadaspeliculas.entity.veiculo.Marca;

@Local
public interface MarcaService {

	void save(Marca marca);

	List<Marca> findAll();
	
	Marca findById(Long id);

}
