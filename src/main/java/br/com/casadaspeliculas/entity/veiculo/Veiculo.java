package br.com.casadaspeliculas.entity.veiculo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVeiculo;
	@NotNull
	private String nuPlaca;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idModelo")
	private Modelo modelo;

	public Veiculo() {
		this.modelo = new Modelo();
	}
	
	@Transient
	public String getVeiculoFmt(){
		return nuPlaca + " (" + modelo.getDeModelo() + ")";
	}
	
	@Transient
	public String getMarcaFmt() {
		return getModelo().getMarca().getDeMarca();
	}
	
	@Transient
	public String getModeloFmt() {
		return getModelo().getDeModelo();
	}

	public Long getIdVeiculo() {
		return idVeiculo;
	}

	public void setIdVeiculo(Long idVeiculo) {
		this.idVeiculo = idVeiculo;
	}

	public String getNuPlaca() {
		return nuPlaca;
	}

	public void setNuPlaca(String nuPlaca) {
		this.nuPlaca = nuPlaca;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idVeiculo == null) ? 0 : idVeiculo.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((nuPlaca == null) ? 0 : nuPlaca.hashCode());
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
		Veiculo other = (Veiculo) obj;
		if (idVeiculo == null) {
			if (other.idVeiculo != null)
				return false;
		} else if (!idVeiculo.equals(other.idVeiculo))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (nuPlaca == null) {
			if (other.nuPlaca != null)
				return false;
		} else if (!nuPlaca.equals(other.nuPlaca))
			return false;
		return true;
	}

}
