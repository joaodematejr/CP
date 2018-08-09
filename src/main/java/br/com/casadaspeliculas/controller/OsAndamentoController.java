package br.com.casadaspeliculas.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import br.com.casadaspeliculas.entity.servico.Servico;
import br.com.casadaspeliculas.service.servico.ServicoService;

@Named
@ViewAccessScoped
public class OsAndamentoController extends ActionsServicoController implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Inject
//	private ServicoService servicoService;

//	private List<Servico> servicesByDay;
//	private List<Servico> filterServicos;

	@PostConstruct
	public void init() {
		buscaServicosByDia();
	}

	public void buscaServicosByDia() {
		setServicesByDay(getServicoService().findAllByDay());
	}

//	public List<Servico> getFilterServicos() {
//		return filterServicos;
//	}
//
//	public void setFilterServicos(List<Servico> filterServicos) {
//		this.filterServicos = filterServicos;
//	}
//
//	public List<Servico> getServicesByDay() {
//		return servicesByDay;
//	}
//
//	public void setServicesByDay(List<Servico> servicesByDay) {
//		this.servicesByDay = servicesByDay;
//	}

}
