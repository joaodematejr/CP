package br.com.casadaspeliculas.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import br.com.casadaspeliculas.entity.veiculo.Modelo;
import br.com.casadaspeliculas.service.veiculo.modelo.ModeloService;

@Named
@ViewAccessScoped
public class ModeloController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ModeloService modeloService;

	private List<Modelo> modelos;
	private List<Modelo> filterModelo;
	private Modelo modelo;

	public ModeloController() {
		this.modelo = new Modelo();
	}

	public void novoModelo() {
		this.modelo = new Modelo();
		redirect("CadModelo.jsf");
	}

	public void salvarModelo() {
		modeloService.save(modelo);
		buscaModelos();
		redirect("ConModelo.jsf");
	}

	public String editaModelo(Modelo modelo) {
		this.modelo = modelo;
		return "CadModelo";
	}

	public void buscaModelos() {
		modelos = modeloService.findAll();
	}

	public void redirect(String pagina) {
		UtilsController.redirect(pagina);
	}

	public String visualizarModelo(Modelo modelo) {
		if (modelo != null) {
			this.modelo = modelo;
			// RequestContext.getCurrentInstance().openDialog("ViewModelo");
			return "CadModelo";
		}
		return null;
	}

	@PostConstruct
	public void init() {
		buscaModelos();
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	public List<Modelo> getFilterModelo() {
		return filterModelo;
	}

	public void setFilterModelo(List<Modelo> filterModelo) {
		this.filterModelo = filterModelo;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Modelo getModeloById(Long id) {
		return modeloService.getModeloById(id);
	}

}
