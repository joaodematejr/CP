package br.com.casadaspeliculas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import br.com.casadaspeliculas.controller.FormaPagamentoController;
import br.com.casadaspeliculas.entity.formapagamento.FormaPagamento;

@Advanced
@FacesConverter(forClass = FormaPagamento.class)
public class FormaPagamentoConverter implements Converter {

	@Inject
	private FormaPagamentoController formaPagamentoController;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		if (value == null || value.length() == 0) {
			return null;
		}
		return formaPagamentoController.getFormaPagamentoById(Long.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
		if (!(object instanceof FormaPagamento)) {
			throw new ConverterException(new StringBuilder("Classe invalida, esperado ").append(FormaPagamento.class.getName()).append(" mas veio ").append(object.getClass().getName()).toString());
		}
		FormaPagamento formaPagamento = (FormaPagamento) object;
		if (formaPagamento == null || formaPagamento.getIdFormaPgto() == null) {
			return null;
		}

		return formaPagamento.getIdFormaPgto().toString();
	}

}
