package br.com.casadaspeliculas.common;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesController implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void addInfo() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Importante: ", "PrimeFaces rocks!"));
	}

	public static void addWarn() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Sample warn message", "Watch out for PrimeFaces!"));
	}

	public static void addError() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sample error message", "PrimeFaces makes no mistakes"));
	}

	public static void addFatal() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ocorreu um erro fatal no sistema: ", "Entre em contato com o Administrador do sistema."));
	}
}
