package br.com.casadaspeliculas.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.myfaces.extensions.cdi.core.api.Advanced;

import br.com.casadaspeliculas.controller.ProdutoController;
import br.com.casadaspeliculas.entity.produto.Produto;


@Advanced
@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

	@Inject
	private ProdutoController produtoController;
	
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		if (value == null || value.length() == 0) {
			return null;
		}
		return produtoController.getProdutoById(Long.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
		if(!(object instanceof Produto)){ 
			throw new ConverterException(
					new StringBuilder("Classe invalida, esperado ").append(Produto.class.getName())
					.append(" mas veio ").append(object.getClass().getName()).toString());
		}
		Produto marca = (Produto)object;
		if(marca == null || marca.getIdProduto() == null){return null; } 
		
		return marca.getIdProduto().toString();
	}

}
