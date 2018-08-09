package br.com.casadaspeliculas.report;

import java.io.Serializable;
import java.util.List;

import br.com.casadaspeliculas.common.Moeda;
import br.com.casadaspeliculas.dto.FiltroConsultaDTO;
import br.com.casadaspeliculas.dto.RelFechamentoCaixaDTO;

public class RelFechamentoCaixa implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<RelFechamentoCaixaDTO> listFechamentoCaixa;
	private FiltroConsultaDTO filtro;
	private Double vlTotal;
	@SuppressWarnings("unused")
	private String vlTotalFmt;
	

	public void somaVlTotalLista(List<RelFechamentoCaixaDTO> listFechamentoCaixa) {
		setVlTotal(0.0);
		for (RelFechamentoCaixaDTO relFechamentoCaixaDTO : listFechamentoCaixa) {
			vlTotal = vlTotal + relFechamentoCaixaDTO.getVlTotalFormaPgto();
		}
	}

	public RelFechamentoCaixa() {
		this.filtro = new FiltroConsultaDTO();
	}

	public List<RelFechamentoCaixaDTO> getListFechamentoCaixa() {
		return listFechamentoCaixa;
	}

	public void setListFechamentoCaixa(List<RelFechamentoCaixaDTO> listFechamentoCaixa) {
		this.listFechamentoCaixa = listFechamentoCaixa;
	}

	public FiltroConsultaDTO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroConsultaDTO filtro) {
		this.filtro = filtro;
	}

	public Double getVlTotal() {
		return vlTotal;
	}

	public void setVlTotal(Double vlTotal) {
		this.vlTotal = vlTotal;
	}

	public String getVlTotalFmt() {
		return Moeda.mascaraDinheiro(vlTotal, Moeda.DINHEIRO_REAL);
	}

}
