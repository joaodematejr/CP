package br.com.casadaspeliculas.entity.formapagamento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "formapgto")
public class FormaPagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFormaPgto;
	@NotNull
	@Column(unique = true)
	private String deFormaPgto;

	public FormaPagamento() {

	}

	public Long getIdFormaPgto() {
		return idFormaPgto;
	}

	public void setIdFormaPgto(Long idFormaPgto) {
		this.idFormaPgto = idFormaPgto;
	}

	public String getDeFormaPgto() {
		return deFormaPgto;
	}

	public void setDeFormaPgto(String deFormaPgto) {
		this.deFormaPgto = deFormaPgto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFormaPgto == null) ? 0 : idFormaPgto.hashCode());
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
		FormaPagamento other = (FormaPagamento) obj;
		if (idFormaPgto == null) {
			if (other.idFormaPgto != null)
				return false;
		} else if (!idFormaPgto.equals(other.idFormaPgto))
			return false;
		return true;
	}

}
