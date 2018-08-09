package br.com.casadaspeliculas.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.context.RequestContext;

import br.com.casadaspeliculas.entity.veiculo.Veiculo;
import br.com.casadaspeliculas.service.veiculo.VeiculoService;

@Named
@ViewAccessScoped
public class VeiculoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VeiculoService veiculoService;

	private List<Veiculo> veiculos;
	private List<Veiculo> filterVeiculo;
	private Veiculo veiculo;

	public VeiculoController() {
		this.veiculo = new Veiculo();
	}

	public void buscaVeiculos() {
		veiculos = veiculoService.findAll();
	}

	public void redirect(String pagina) {
		UtilsController.redirect(pagina);
	}
	
	public void visualizarVeiculo(Veiculo veiculo) {
		if (veiculo != null) {
			this.veiculo = veiculo;
			RequestContext.getCurrentInstance().openDialog("ViewVeiculo");
		}
	}

	@PostConstruct
	public void init() {
		buscaVeiculos();
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public List<Veiculo> getFilterVeiculo() {
		return filterVeiculo;
	}

	public void setFilterVeiculo(List<Veiculo> filterVeiculo) {
		this.filterVeiculo = filterVeiculo;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

}
