package br.com.casadaspeliculas.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;

import br.com.casadaspeliculas.common.ConversorDatasUtil;
import br.com.casadaspeliculas.common.RelatorioUtil;
import br.com.casadaspeliculas.dto.FiltroConsultaDTO;
import br.com.casadaspeliculas.entity.servico.Servico;
import br.com.casadaspeliculas.report.TipoRelatorio;

@Named
@ViewAccessScoped
public class RelatorioController implements Serializable {

	private static final long serialVersionUID = 1L;

	private FiltroConsultaDTO filtro;

	public void imprimirServico(Servico servico) {
		HashMap<String, String> parametros = new HashMap<String, String>();
		parametros.put("cdServico", servico.getIdServico().toString());
		redirect(getFacesContext().getExternalContext().getRequestContextPath() + RelatorioUtil.criaUrlRelatorio(TipoRelatorio.SERVICOS_SIMPLIFICADO, parametros));
	}

	public void imprimirRelFechamentoCaixa() {
		TipoRelatorio tipo;
		if ("analitico".equalsIgnoreCase(filtro.getTipoRelatorio())) {
			tipo = TipoRelatorio.FECHAMENTO_CAIXA_ANALITICO;
		} else {
			tipo = TipoRelatorio.FECHAMENTO_CAIXA;
		}

		HashMap<String, String> parametros = new HashMap<String, String>();
		parametros.put("dtInicial", ConversorDatasUtil.formataDataSemHora(this.filtro.getDtInicial()));
		parametros.put("dtFinal", ConversorDatasUtil.formataDataSemHora(this.filtro.getDtFinal()));
		redirect(getFacesContext().getExternalContext().getRequestContextPath() + RelatorioUtil.criaUrlRelatorio(tipo, parametros));
	}

	public void redirect(String pagina) {
		try {
			getFacesContext().getExternalContext().redirect(pagina);
			getFacesContext().responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void init() {
		this.filtro = new FiltroConsultaDTO();
	}

	private FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public FiltroConsultaDTO getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroConsultaDTO filtro) {
		this.filtro = filtro;
	}

}
