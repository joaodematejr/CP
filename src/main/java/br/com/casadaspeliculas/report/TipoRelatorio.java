package br.com.casadaspeliculas.report;

/**
 * @author Nildo Beppler Jr
 *
 */
public enum TipoRelatorio {
	SERVICOS(""),
	SERVICOS_SIMPLIFICADO("/br/com/casadaspeliculas/report/RelServicoSimplificado.jrxml"),
	FECHAMENTO_CAIXA("/br/com/casadaspeliculas/report/RelFechamentoCaixa.jrxml"),
	FECHAMENTO_CAIXA_ANALITICO("/br/com/casadaspeliculas/report/RelFechamentoCaixaAnalitico.jrxml"),
	FINANCEIRO("");
	
	private final String urlRelatorio; 
	
	private TipoRelatorio(String urlRelatorio) {
		this.urlRelatorio = urlRelatorio;
	}

	public String getUrlRelatorio() {
		return urlRelatorio;
	}
	
	
	
}
