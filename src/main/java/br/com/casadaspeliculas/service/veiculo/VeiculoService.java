package br.com.casadaspeliculas.service.veiculo;

import java.util.List;

import javax.ejb.Local;

import br.com.casadaspeliculas.entity.veiculo.Veiculo;

@Local
public interface VeiculoService {

	void save(Veiculo veiculo);

	List<Veiculo> findAll();

}
