package br.com.casadaspeliculas.entity.cliente;

import java.io.Serializable;

public class ClienteFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nuCliente;
	private String nmCliente;
	private String deTipoCliente;
	private String statusCliente;

	public String getNuCliente() {
		return nuCliente;
	}

	public void setNuCliente(String nuCliente) {
		this.nuCliente = nuCliente;
	}

	public String getNmCliente() {
		return nmCliente;
	}

	public void setNmCliente(String nmCliente) {
		this.nmCliente = nmCliente;
	}

	public String getDeTipoCliente() {
		return deTipoCliente;
	}

	public void setDeTipoCliente(String deTipoCliente) {
		this.deTipoCliente = deTipoCliente;
	}

	public String getStatusCliente() {
		return statusCliente;
	}

	public void setStatusCliente(String statusCliente) {
		this.statusCliente = statusCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deTipoCliente == null) ? 0 : deTipoCliente.hashCode());
		result = prime * result + ((nmCliente == null) ? 0 : nmCliente.hashCode());
		result = prime * result + ((nuCliente == null) ? 0 : nuCliente.hashCode());
		result = prime * result + ((statusCliente == null) ? 0 : statusCliente.hashCode());
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
		ClienteFilter other = (ClienteFilter) obj;
		if (deTipoCliente == null) {
			if (other.deTipoCliente != null)
				return false;
		} else if (!deTipoCliente.equals(other.deTipoCliente))
			return false;
		if (nmCliente == null) {
			if (other.nmCliente != null)
				return false;
		} else if (!nmCliente.equals(other.nmCliente))
			return false;
		if (nuCliente == null) {
			if (other.nuCliente != null)
				return false;
		} else if (!nuCliente.equals(other.nuCliente))
			return false;
		if (statusCliente == null) {
			if (other.statusCliente != null)
				return false;
		} else if (!statusCliente.equals(other.statusCliente))
			return false;
		return true;
	}

}
