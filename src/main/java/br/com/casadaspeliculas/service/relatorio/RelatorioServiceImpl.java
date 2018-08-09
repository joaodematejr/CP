package br.com.casadaspeliculas.service.relatorio;

import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.casadaspeliculas.common.ConversorDatasUtil;
import br.com.casadaspeliculas.dao.RelatorioDao;
import br.com.casadaspeliculas.dao.ServicoDao;
import br.com.casadaspeliculas.dto.RelFechamentoCaixaDTO;
import br.com.casadaspeliculas.entity.servico.Servico;
import br.com.casadaspeliculas.report.RelFechamentoCaixa;
import br.com.casadaspeliculas.report.RelFechamentoCaixaAnalitico;
import br.com.casadaspeliculas.report.TipoRelatorio;

@Stateless
public class RelatorioServiceImpl implements RelatorioService {

	@Inject
	private ServicoDao servicoDao;
	@Inject
	private RelatorioDao relatorioDao;

	@Override
	public void imprimeRelatorio(TipoRelatorio tipo, Map<String, String[]> parametros, OutputStream outputStream) {
		try {
			JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream(tipo.getUrlRelatorio()));
			JasperPrint print = JasperFillManager.fillReport(report, null, getDataSource(tipo, parametros));
			JasperExportManager.exportReportToPdfStream(print, outputStream);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	private JRDataSource getDataSource(TipoRelatorio tipo, Map<String, String[]> parametros) {
		switch (tipo) {
		case FINANCEIRO:
			break;
		case SERVICOS:
			break;
		case SERVICOS_SIMPLIFICADO:
			return new JRBeanCollectionDataSource(Collections.singleton(getDatasourceServicoSimplificado(parametros)));
		case FECHAMENTO_CAIXA:
			return new JRBeanCollectionDataSource(Collections.singleton(getDatasourceFechamentoCaixa(parametros)));
		case FECHAMENTO_CAIXA_ANALITICO:
			return new JRBeanCollectionDataSource(Collections.singleton(getDatasourceFechamentoCaixaAnalitico(parametros)));
		}
		throw new IllegalArgumentException(new StringBuilder("Tipo de relatório não suportado: ").append(tipo).toString());
	}
	
	private RelFechamentoCaixaAnalitico getDatasourceFechamentoCaixaAnalitico(Map<String, String[]> parametros) {
		RelFechamentoCaixaAnalitico obj = new RelFechamentoCaixaAnalitico();
		List<Servico> servicos = relatorioDao.findFechamentoAnaliticoByPeriodo(parametros);
		obj.setServicos(servicos);
		try {
			obj.getFiltro().setDtInicial(ConversorDatasUtil.formataData(parametros.get("dtInicial")[0]));
			obj.getFiltro().setDtFinal(ConversorDatasUtil.formataData(parametros.get("dtFinal")[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	private Servico getDatasourceServicoSimplificado(Map<String, String[]> parametros) {
		return servicoDao.findById(Long.valueOf(parametros.get("cdServico")[0]));
	}

	private RelFechamentoCaixa getDatasourceFechamentoCaixa(Map<String, String[]> parametros) {
		RelFechamentoCaixa obj = new RelFechamentoCaixa();
		List<RelFechamentoCaixaDTO> findFechamentoByPeriodo = relatorioDao.findFechamentoByPeriodo(parametros);
		obj.setListFechamentoCaixa(findFechamentoByPeriodo);
		try {
			obj.getFiltro().setDtInicial(ConversorDatasUtil.formataData(parametros.get("dtInicial")[0]));
			obj.getFiltro().setDtFinal(ConversorDatasUtil.formataData(parametros.get("dtFinal")[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		obj.somaVlTotalLista(findFechamentoByPeriodo);
		return obj;
	}
}
