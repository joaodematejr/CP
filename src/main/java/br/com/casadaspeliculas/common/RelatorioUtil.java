package br.com.casadaspeliculas.common;

import java.util.Map;

import br.com.casadaspeliculas.report.TipoRelatorio;

public class RelatorioUtil {
	public static final String PARAMETRO_TIPO_RELATORIO = "tpRelatorio";
	
	
	public static String criaUrlRelatorio(TipoRelatorio tipo, Map<String, String> parametros){
		
		StringBuilder urlBuilder = new StringBuilder(CasaPeliculasConstants.URL_RELATORIOS);
		urlBuilder.append("?").append(PARAMETRO_TIPO_RELATORIO).append("=").append(tipo.toString());
		for(Map.Entry<String, String> parametro : parametros.entrySet()){
			urlBuilder.append("&").append(parametro.getKey()).append("=").append(parametro.getValue());
		}
		return urlBuilder.toString();
	}
}
