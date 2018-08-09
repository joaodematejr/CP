package br.com.casadaspeliculas.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.TimeZone;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

import br.com.casadaspeliculas.common.MessagesController;

@Named
@ApplicationScoped
public class UtilsController {
	
	public TimeZone getTimeZone() {
		return TimeZone.getDefault();
	}
	
	public static void redirect(String pagina) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
		} catch (IOException e) {
			MessagesController.addFatal();
			e.printStackTrace();
		}
	}
	
	public void preProcessPDF(Object document) throws BadElementException, MalformedURLException, DocumentException, IOException {
		Document pdf = (Document) document;

		pdf.setPageSize(PageSize.A4.rotate());
		pdf.setMargins(20f, 20f, 20f, 20f);
		
		pdf.open();

		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String logo = servletContext.getRealPath("imagens") + File.separator + "logo_cp.jpg";
		Image imagem = Image.getInstance(logo);
		imagem.scalePercent(30f);
		pdf.add(imagem);
		
	}

}
