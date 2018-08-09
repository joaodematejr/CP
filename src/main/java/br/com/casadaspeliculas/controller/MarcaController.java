package br.com.casadaspeliculas.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import br.com.casadaspeliculas.entity.veiculo.Marca;
import br.com.casadaspeliculas.service.veiculo.marca.MarcaService;

@Named
@ViewAccessScoped
public class MarcaController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MarcaService marcaService;

	private List<Marca> marcas;
	private List<Marca> filterMarca;
	private Marca marca;

	public MarcaController() {
		this.marca = new Marca();
	}

	public void novaMarca() {
		this.marca = new Marca();
		redirect("CadMarca.jsf");
	}

	public void salvarMarca() {
		marcaService.save(marca);
		buscaMarcas();
		redirect("ConMarca.jsf");
	}

	public String editaMarca(Marca marca) {
		this.marca = marca;
		return "CadMarca";
	}

	public void buscaMarcas() {
		marcas = marcaService.findAll();
	}

	public void redirect(String pagina) {
		UtilsController.redirect(pagina);
	}
	
	public Marca getMarcaById(Long id){
		return marcaService.findById(id);
	}

	public String visualizarMarca(Marca marca) {
		if (marca != null) {
			this.marca = marca;
			return "CadMarca";
//			RequestContext.getCurrentInstance().openDialog("ViewMarca");
		}
		return null;
	}

	@PostConstruct
	public void init() {
		buscaMarcas();
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<Marca> getFilterMarca() {
		return filterMarca;
	}

	public void setFilterMarca(List<Marca> filterMarca) {
		this.filterMarca = filterMarca;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

}
