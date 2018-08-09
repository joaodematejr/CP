package br.com.casadaspeliculas.service.veiculo.modelo;

import java.util.List;

import javax.ejb.Local;

import br.com.casadaspeliculas.entity.veiculo.Modelo;

@Local
public interface ModeloService {

	void save(Modelo modelo);

	List<Modelo> findAll();

	Modelo getModeloById(Long id);

}
