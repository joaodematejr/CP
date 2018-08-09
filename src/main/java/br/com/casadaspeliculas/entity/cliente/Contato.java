package br.com.casadaspeliculas.entity.cliente;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.casadaspeliculas.common.Email;

@Entity
@Table(name = "contato")
public class Contato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idContato;
	private String nuTelRes;
	private String nuTelCom;
	private String nuTelCel;
	@Email
	private String deEmail;
	private String deSite;

	public Contato() {

	}

	public Contato(String nuTelRes, String nuTelCom, String nuTelCel, String deEmail, String deSite) {
		this.nuTelRes = nuTelRes;
		this.nuTelCom = nuTelCom;
		this.nuTelCel = nuTelCel;
		this.deEmail = deEmail;
		this.deSite = deSite;
	}

	public Long getIdContato() {
		return idContato;
	}

	public void setIdContato(Long idContato) {
		this.idContato = idContato;
	}

	public String getNuTelRes() {
		return nuTelRes;
	}

	public void setNuTelRes(String nuTelRes) {
		this.nuTelRes = nuTelRes;
	}

	public String getNuTelCom() {
		return nuTelCom;
	}

	public void setNuTelCom(String nuTelCom) {
		this.nuTelCom = nuTelCom;
	}

	public String getNuTelCel() {
		return nuTelCel;
	}

	public void setNuTelCel(String nuTelCel) {
		this.nuTelCel = nuTelCel;
	}

	public String getDeEmail() {
		return deEmail;
	}

	public void setDeEmail(String deEmail) {
		this.deEmail = deEmail;
	}

	public String getDeSite() {
		return deSite;
	}

	public void setDeSite(String deSite) {
		this.deSite = deSite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deEmail == null) ? 0 : deEmail.hashCode());
		result = prime * result + ((deSite == null) ? 0 : deSite.hashCode());
		result = prime * result + ((idContato == null) ? 0 : idContato.hashCode());
		result = prime * result + ((nuTelCel == null) ? 0 : nuTelCel.hashCode());
		result = prime * result + ((nuTelCom == null) ? 0 : nuTelCom.hashCode());
		result = prime * result + ((nuTelRes == null) ? 0 : nuTelRes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (deEmail == null) {
			if (other.deEmail != null)
				return false;
		} else if (!deEmail.equals(other.deEmail))
			return false;
		if (deSite == null) {
			if (other.deSite != null)
				return false;
		} else if (!deSite.equals(other.deSite))
			return false;
		if (idContato == null) {
			if (other.idContato != null)
				return false;
		} else if (!idContato.equals(other.idContato))
			return false;
		if (nuTelCel == null) {
			if (other.nuTelCel != null)
				return false;
		} else if (!nuTelCel.equals(other.nuTelCel))
			return false;
		if (nuTelCom == null) {
			if (other.nuTelCom != null)
				return false;
		} else if (!nuTelCom.equals(other.nuTelCom))
			return false;
		if (nuTelRes == null) {
			if (other.nuTelRes != null)
				return false;
		} else if (!nuTelRes.equals(other.nuTelRes))
			return false;
		return true;
	}

}
