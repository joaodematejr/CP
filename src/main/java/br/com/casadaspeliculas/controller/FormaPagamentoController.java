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

import br.com.casadaspeliculas.entity.formapagamento.FormaPagamento;
import br.com.casadaspeliculas.service.formapagamento.FormaPagamentoService;

@Named
@ViewAccessScoped
public class FormaPagamentoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FormaPagamentoService formaPagamentoService;
	private FormaPagamento formaPagamento;
	private List<FormaPagamento> formasPagamentos;
	private List<FormaPagamento> filterFormasPagamentos;

	public FormaPagamentoController() {
		this.formaPagamento = new FormaPagamento();
	}
	
	public void deleteFormaPagamento(FormaPagamento fp){
		formaPagamentoService.delete(fp);
		this.buscaFormaPagamento();
	}
	
	public String novaFormaPagamento(){
		this.formaPagamento = new FormaPagamento();
		return "CadFormaPagamento";
	}

	public String editaFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
		return "CadFormaPagamento";
	}

	private void buscaFormaPagamento() {
		this.formasPagamentos = formaPagamentoService.findAll();
	}

	public void save() {
		formaPagamentoService.save(formaPagamento);
		buscaFormaPagamento();
		redirect("ConFormaPagamento.jsf");
	}

	public void visualizarFormaPagamento(FormaPagamento formaPagamento) {
		if (formaPagamento != null) {
			this.formaPagamento = formaPagamento;
			RequestContext.getCurrentInstance().openDialog("ViewFormaPagamento");
		}
	}

	public void redirect(String pagina) {
		try {
			getFacesContext().getExternalContext().redirect(pagina);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void init() {
		buscaFormaPagamento();
	}

	private FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public List<FormaPagamento> getFormasPagamentos() {
		return formasPagamentos;
	}

	public void setFormasPagamentos(List<FormaPagamento> formasPagamentos) {
		this.formasPagamentos = formasPagamentos;
	}

	public List<FormaPagamento> getFilterFormasPagamentos() {
		return filterFormasPagamentos;
	}

	public void setFilterFormasPagamentos(List<FormaPagamento> filterFormasPagamentos) {
		this.filterFormasPagamentos = filterFormasPagamentos;
	}

	public FormaPagamento getFormaPagamentoById(Long id) {
		return formaPagamentoService.getFormaPagamentoById(id);
	}

}
