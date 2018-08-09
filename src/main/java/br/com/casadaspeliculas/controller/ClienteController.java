package br.com.casadaspeliculas.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.context.RequestContext;

import br.com.casadaspeliculas.common.MessagesController;
import br.com.casadaspeliculas.entity.cliente.Cliente;
import br.com.casadaspeliculas.service.cliente.ClienteService;

@Named
@ViewAccessScoped
public class ClienteController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteService clienteService;

	private List<Cliente> clientes;
	private List<Cliente> filterClientes;
	private Cliente cliente;

	public ClienteController() {
		this.cliente = new Cliente();
	}

	public void novoCliente() {
		this.cliente = new Cliente();
		redirect("CadCliente.jsf");
	}

	public void salvarCliente() {
		// valida cpf e cnpj
		clienteService.save(cliente);
//		buscaClientes();
		redirect("ConCliente.jsf");
	}

	public void updateStatus(Cliente cliente) {
		if ("A".equals(cliente.getStatusCliente())) {
			cliente.setStatusCliente("I");
		} else {
			cliente.setStatusCliente("A");
		}
		clienteService.save(cliente);
	}

	public String editaCliente(Cliente cliente) {
		this.cliente = cliente;
		return "CadCliente";
	}

	public void getClientesCombo() {
		clientes = clienteService.findAllCombo();
	}

	public void buscaClientes() {
		clientes = clienteService.findAll();
	}

	public void redirect(String pagina) {
		try {
			getFacesContext().getExternalContext().redirect(pagina);
		} catch (IOException e) {
			MessagesController.addFatal();
			e.printStackTrace();
		}
	}

	public void visualizarCliente(Cliente cliente) {
		if (cliente != null) {
			this.cliente = cliente;
			RequestContext.getCurrentInstance().openDialog("ViewCliente");
		}
	}

	@PostConstruct
	public void init() {
//		getClientesCombo();
		buscaClientes();
	}

	private FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getFilterClientes() {
		return filterClientes;
	}

	public void setFilterClientes(List<Cliente> filterClientes) {
		this.filterClientes = filterClientes;
	}

	public Cliente getClienteById(Long id) {
		return clienteService.getClienteById(id);
	}

}
