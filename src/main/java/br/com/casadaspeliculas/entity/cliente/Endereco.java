package br.com.casadaspeliculas.entity.cliente;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idEndereco;
	private String nmEndereco;
	private String nmBairro;
	private String nmCidade;
	private String nmUf;
	private String nuCep;

	public Endereco() {

	}

	public Endereco(String nmEndereco, String nmBairro, String nmCidade, String nmUf, String nuCep) {
		this.nmEndereco = nmEndereco;
		this.nmBairro = nmBairro;
		this.nmCidade = nmCidade;
		this.nmUf = nmUf;
		this.nuCep = nuCep;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getNmEndereco() {
		return nmEndereco;
	}

	public void setNmEndereco(String nmEndereco) {
		this.nmEndereco = nmEndereco;
	}

	public String getNmBairro() {
		return nmBairro;
	}

	public void setNmBairro(String nmBairro) {
		this.nmBairro = nmBairro;
	}

	public String getNmCidade() {
		return nmCidade;
	}

	public void setNmCidade(String nmCidade) {
		this.nmCidade = nmCidade;
	}

	public String getNmUf() {
		return nmUf;
	}

	public void setNmUf(String nmUf) {
		this.nmUf = nmUf;
	}

	public String getNuCep() {
		return nuCep;
	}

	public void setNuCep(String nuCep) {
		this.nuCep = nuCep;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idEndereco == null) ? 0 : idEndereco.hashCode());
		result = prime * result + ((nmBairro == null) ? 0 : nmBairro.hashCode());
		result = prime * result + ((nmCidade == null) ? 0 : nmCidade.hashCode());
		result = prime * result + ((nmEndereco == null) ? 0 : nmEndereco.hashCode());
		result = prime * result + ((nmUf == null) ? 0 : nmUf.hashCode());
		result = prime * result + ((nuCep == null) ? 0 : nuCep.hashCode());
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
		Endereco other = (Endereco) obj;
		if (idEndereco == null) {
			if (other.idEndereco != null)
				return false;
		} else if (!idEndereco.equals(other.idEndereco))
			return false;
		if (nmBairro == null) {
			if (other.nmBairro != null)
				return false;
		} else if (!nmBairro.equals(other.nmBairro))
			return false;
		if (nmCidade == null) {
			if (other.nmCidade != null)
				return false;
		} else if (!nmCidade.equals(other.nmCidade))
			return false;
		if (nmEndereco == null) {
			if (other.nmEndereco != null)
				return false;
		} else if (!nmEndereco.equals(other.nmEndereco))
			return false;
		if (nmUf == null) {
			if (other.nmUf != null)
				return false;
		} else if (!nmUf.equals(other.nmUf))
			return false;
		if (nuCep == null) {
			if (other.nuCep != null)
				return false;
		} else if (!nuCep.equals(other.nuCep))
			return false;
		return true;
	}

}
