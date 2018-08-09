package br.com.casadaspeliculas.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.casadaspeliculas.common.ConversorDatasUtil;

public class FiltroConsultaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dtInicial;
	private Date dtFinal;
	private String tipoRelatorio;

	public FiltroConsultaDTO() {
		this.dtInicial = new Date();
		this.dtFinal = new Date();
		this.tipoRelatorio = "simplificado";
	}

	public String getDtIncialFmt() {
		return ConversorDatasUtil.formataDataSemHora(dtInicial);
	}

	public String getDtFinalFmt() {
		return ConversorDatasUtil.formataDataSemHora(dtFinal);
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public String getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

}
