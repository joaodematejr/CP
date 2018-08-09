package br.com.casadaspeliculas.service.cliente;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.casadaspeliculas.dao.ClienteDao;
import br.com.casadaspeliculas.entity.cliente.Cliente;

@Stateless
public class ClienteServiceImpl implements ClienteService {

	@Inject
	private ClienteDao clienteDao;

	@Override
	public void save(Cliente cliente) {
		if (cliente.getDtCadastro() == null) {
			cliente.setDtCadastro(new Date());
		}
		clienteDao.save(cliente);
	}

	@Override
	public List<Cliente> findAll() {
		return clienteDao.findAll();
	}

	@Override
	public Cliente getClienteById(Long id) {
		return clienteDao.getClienteById(id);
	}

	@Override
	public List<Cliente> findAllCombo() {
		return clienteDao.findAllCombo();
	}

}
