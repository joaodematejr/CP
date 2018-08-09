package br.com.casadaspeliculas.report;

import java.io.Serializable;
import java.util.List;

import br.com.casadaspeliculas.dto.FiltroConsultaDTO;
import br.com.casadaspeliculas.entity.servico.Servico;

public class RelFechamentoCaixaAnalitico implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Servico> servicos;
	private FiltroConsultaDTO filtro;
	
	public RelFechamentoCaixaAnalitico(){
		this.filtro = new FiltroConsultaDTO();
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public FiltroConsultaDTO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroConsultaDTO filtro) {
		this.filtro = filtro;
	}

}
