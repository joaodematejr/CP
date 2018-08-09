package br.com.casadaspeliculas.servlet;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.casadaspeliculas.common.CasaPeliculasConstants;
import br.com.casadaspeliculas.common.RelatorioUtil;
import br.com.casadaspeliculas.report.TipoRelatorio;
import br.com.casadaspeliculas.service.relatorio.RelatorioService;

@WebServlet(name="reportServlet", urlPatterns={CasaPeliculasConstants.URL_RELATORIOS})
public class ReportServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8867483078172425790L;


	@Inject
	private RelatorioService relatorioService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}
	
	public void doService(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String tipoRelatorio = request.getParameter(RelatorioUtil.PARAMETRO_TIPO_RELATORIO);
		Map<String, String[]> parametros = request.getParameterMap();
		relatorioService.imprimeRelatorio(TipoRelatorio.valueOf(tipoRelatorio),parametros, response.getOutputStream() );
	}
	
}
