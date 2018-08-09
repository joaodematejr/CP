package br.com.casadaspeliculas.service.servico;

import java.util.List;

import javax.ejb.Local;

import br.com.casadaspeliculas.entity.senha.Senhas;
import br.com.casadaspeliculas.entity.servico.Servico;
import br.com.casadaspeliculas.entity.veiculo.Marca;
import br.com.casadaspeliculas.entity.veiculo.Modelo;

@Local
public interface ServicoService {

	void save(Servico servico);

	List<Servico> findAll();

	List<Servico> findAllByDay();

	List<Marca> getMarcas();

	List<Modelo> getModelos(Long idMarca);

	Senhas getSenhas();

}
