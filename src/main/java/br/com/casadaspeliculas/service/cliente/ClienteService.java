package br.com.casadaspeliculas.service.cliente;

import java.util.List;

import javax.ejb.Local;

import br.com.casadaspeliculas.entity.cliente.Cliente;

@Local
public interface ClienteService {

	void save(Cliente cliente);

	List<Cliente> findAll();
	
	List<Cliente> findAllCombo();

	Cliente getClienteById(Long id);

}
