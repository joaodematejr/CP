package br.com.casadaspeliculas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import br.com.casadaspeliculas.controller.ModeloController;
import br.com.casadaspeliculas.entity.veiculo.Modelo;

@Advanced
@FacesConverter(forClass = Modelo.class)
public class ModeloConverter implements Converter {

	@Inject
	private ModeloController modeloController;

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		if (value == null || value.length() == 0) {
			return null;
		}
		return modeloController.getModeloById(Long.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
		if (!(object instanceof Modelo)) {
			throw new ConverterException(new StringBuilder("Classe invalida, esperado ").append(Modelo.class.getName()).append(" mas veio ").append(object.getClass().getName()).toString());
		}
		Modelo modelo = (Modelo) object;
		if (modelo == null || modelo.getIdModelo() == null) {
			return null;
		}

		return modelo.getIdModelo().toString();
	}

}
