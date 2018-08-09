package br.com.casadaspeliculas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import br.com.casadaspeliculas.controller.ClienteController;
import br.com.casadaspeliculas.entity.cliente.Cliente;

@Advanced
@FacesConverter(forClass = Cliente.class)
public class ClienteConverter implements Converter {

	@Inject
	private ClienteController clienteController;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		if (value == null || value.length() == 0) {
			return null;
		}
		return clienteController.getClienteById(Long.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
		if (!(object instanceof Cliente)) {
			throw new ConverterException(new StringBuilder("Classe invalida, esperado ").append(Cliente.class.getName()).append(" mas veio ").append(object.getClass().getName()).toString());
		}
		Cliente cliente = (Cliente) object;
		if (cliente == null || cliente.getIdCliente() == null) {
			return null;
		}

		return cliente.getIdCliente().toString();
	}

}
