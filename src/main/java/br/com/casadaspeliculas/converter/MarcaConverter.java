package br.com.casadaspeliculas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import br.com.casadaspeliculas.controller.MarcaController;
import br.com.casadaspeliculas.entity.veiculo.Marca;


@Advanced
@FacesConverter(forClass = Marca.class)
public class MarcaConverter implements Converter {

	@Inject
	private MarcaController marcaController;
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		if (value == null || value.length() == 0) {
			return null;
		}
		return marcaController.getMarcaById(Long.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
		if(!(object instanceof Marca)){ 
			throw new ConverterException(
					new StringBuilder("Classe invalida, esperado ").append(Marca.class.getName())
					.append(" mas veio ").append(object.getClass().getName()).toString());
		}
		Marca marca = (Marca)object;
		if(marca == null || marca.getIdMarca() == null){return null; } 
		
		return marca.getIdMarca().toString();
	}

}
