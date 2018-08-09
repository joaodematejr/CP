package br.com.casadaspeliculas.dto;

import java.io.Serializable;

import br.com.casadaspeliculas.common.Moeda;

public class RelFechamentoCaixaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String deFormaPgto;
	private Double vlTotalFormaPgto;
	
	
	public String getVlFormatado() {
		return Moeda.mascaraDinheiro(vlTotalFormaPgto, Moeda.DINHEIRO_REAL);
	}

	public String getDeFormaPgto() {
		return deFormaPgto;
	}

	public void setDeFormaPgto(String deFormaPgto) {
		this.deFormaPgto = deFormaPgto;
	}

	public Double getVlTotalFormaPgto() {
		return vlTotalFormaPgto;
	}

	public void setVlTotalFormaPgto(Double vlTotalFormaPgto) {
		this.vlTotalFormaPgto = vlTotalFormaPgto;
	}

}
