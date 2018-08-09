package br.com.casadaspeliculas.service.relatorio;

import java.io.OutputStream;
import java.util.Map;

import javax.ejb.Local;

import br.com.casadaspeliculas.report.TipoRelatorio;

@Local
public interface RelatorioService {

	void imprimeRelatorio(TipoRelatorio valueOf, Map<String, String[]> parametros, OutputStream outputStream);

}
