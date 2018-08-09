package br.com.casadaspeliculas.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import br.com.casadaspeliculas.ConstantesSistema;
import br.com.casadaspeliculas.entity.senha.Senhas;
import br.com.casadaspeliculas.entity.servico.ProdutoServico;
import br.com.casadaspeliculas.entity.servico.Servico;
import br.com.casadaspeliculas.entity.servico.TipoServico;
import br.com.casadaspeliculas.entity.veiculo.Marca;
import br.com.casadaspeliculas.entity.veiculo.Modelo;
import br.com.casadaspeliculas.service.servico.ServicoService;
import br.com.casadaspeliculas.service.servicoproduto.ProdutoServicoService;

public class ActionsServicoController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static final String NAVIGATION_RULE_CADASTRO = "CadServico";

	@Inject
	private ServicoService servicoService;
	@Inject
	private ProdutoServicoService produtoServicoService;
	
	private List<Servico> servicesByDay;
	private List<Servico> filterServicos;
	private Servico servico;
	private boolean podeEditar;
	private String senhaOperadorEdicao;
	private Senhas senhas;
	private String senhaOperadorFinalizacao;
	private String operacaoSenha;
	private List<Marca> marcas;
	private List<Modelo> modelos;

	
	
//
//	private List<Servico> servicos;
	
	public String editaServico(Servico servico) {
		this.servico = servico;
		buscaProdutosDoServico(servico);
		montaMapaServicosPrestados();
		this.podeEditar = false;
		if (!this.servico.getFinanceiro().isFlFaturar()) {
			this.servico.getFinanceiro().setStatusFinanceiro(ConstantesSistema.A.toString());
		}
		setParameter("Servico", this.servico);
		buscaMarcas();
		buscaModelos();
		return NAVIGATION_RULE_CADASTRO;
	}
	
	private void buscaProdutosDoServico(Servico servico) {
		this.servico.setProdutos(this.produtoServicoService.findAllById(servico));
	}
	
	@SuppressWarnings("unchecked")
	private void montaMapaServicosPrestados() {
		TipoServico[] tiposServico = TipoServico.values();
		List<ProdutoServico> produtos = this.servico.getProdutos();
		for (TipoServico tipoServico : tiposServico) {
			ProdutoServico servicoPrestado = this.servico.getServicoPrestado(tipoServico);
			if (servicoPrestado == null) {
				servicoPrestado = new ProdutoServico();
				servicoPrestado.setServico(this.servico);
				servicoPrestado.setTipoServico(tipoServico);
				produtos.add(servicoPrestado);
			}
		}
		Collections.sort(produtos);
	}
	
	public void setParameter(String key, Object obj) {
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		req.setAttribute(key, obj);
	}
	
	public Object getParameter(String key) {
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return req.getAttribute(key);
	}
	


	
	
	
	
	
	
	
	
	
	public void podeEditar(){
		if ((getSenhaOperadorEdicao() == null || "".equals(getSenhaOperadorEdicao()))) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Validação de Senha", "É preciso informar uma senha !!!"));
		} else {
			if (getSenhas().getSenhaEdicao().equalsIgnoreCase(getSenhaOperadorEdicao())) {
				podeEditar = true;
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Validação de Senha", "A senha não confere !!!"));
			}
		}
	}


	
	
	
	
	
	
	
	
	
	
	
	public Senhas carregaSenhas(){
		return this.servicoService.getSenhas();
	}
	
	public String getSenhaOperadorEdicao() {
		return senhaOperadorEdicao;
	}

	public void setSenhaOperadorEdicao(String senhaOperadorEdicao) {
		this.senhaOperadorEdicao = senhaOperadorEdicao;
	}
	
	public List<Servico> getFilterServicos() {
		return filterServicos;
	}

	public void setFilterServicos(List<Servico> filterServicos) {
		this.filterServicos = filterServicos;
	}

	public List<Servico> getServicesByDay() {
		return servicesByDay;
	}

	public void setServicesByDay(List<Servico> servicesByDay) {
		this.servicesByDay = servicesByDay;
	}

	public ServicoService getServicoService() {
		return servicoService;
	}

	public void setServicoService(ServicoService servicoService) {
		this.servicoService = servicoService;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	
	

	public void buscaMarcas() {
		setMarcas(servicoService.getMarcas());
	}

	public String novoServico() {
 		this.servico = new Servico();
		montaMapaServicosPrestados();

		this.servico.setDtServico(new Date());
		this.servico.setStatusServico(ConstantesSistema.A.toString());
		this.servico.getFinanceiro().setStatusFinanceiro(ConstantesSistema.A.toString());
		this.podeEditar = true;
		buscaMarcas();
		buscaModelos();
		return NAVIGATION_RULE_CADASTRO;
	}

//	private void buscaServicos() {
//		this.servicos = servicoService.findAll();
//	}
//
//	public void save() {
//		removerProdutosNaoSelecionadosDoServico(servico);
//		if (servico.getFinanceiro().isFlFaturar()) {
//			servico.getFinanceiro().setStatusFinanceiro("F");
//		}
//
//		buildDataFinalGarantia();
//		servico.getVeiculo().setNuPlaca(servico.getVeiculo().getNuPlaca().toUpperCase());
//		servicoService.save(servico);
//		redirect("OSAndamento.jsf");
//	}
//	
//	public void cancelarServico() {
//		servico.getFinanceiro().setStatusFinanceiro("C");
//		servico.setStatusServico("C");
//		save();
//	}
//
//	public void finalizarServico() {
//		servico.getFinanceiro().setStatusFinanceiro("P");
//		servico.getFinanceiro().setDtPagamento(new Date());
//		servico.setStatusServico("F");
//		buildDataFinalGarantia();
//		save();
//	}
//
//	private void buildDataFinalGarantia() {
//		if (servico.getTempoGarantia() == 1) {
//			buildTempoGarantia(1);
//		} else if (servico.getTempoGarantia() == 2) {
//			buildTempoGarantia(2);
//		} else if (servico.getTempoGarantia() == 3) {
//			buildTempoGarantia(3);
//		} else {
//			buildTempoGarantia(-1);
//		}
//	}
//
//	private void buildTempoGarantia(int tempo) {
//		Calendar c = Calendar.getInstance();
//		c.setTime(servico.getDtServico());
//		c.set(Calendar.YEAR, c.get(Calendar.YEAR) + (tempo));
//		servico.setDtFinalGarantia(c.getTime());
//	}
//
//	private void removerProdutosNaoSelecionadosDoServico(Servico servico) {
//		List<ProdutoServico> produtosSelecionados = new ArrayList<ProdutoServico>();
//		for (ProdutoServico produtoServico : servico.getProdutos()) {
//			if (produtoServico.getProduto() != null) {
//				produtosSelecionados.add(produtoServico);
//			}
//		}
//		servico.setProdutos(produtosSelecionados);
//	}
//
	public void buscaModelos() {
		setModelos(servicoService.getModelos(this.servico.getVeiculo().getModelo().getMarca().getIdMarca()));
	}
//
//	public void cadastraNovoCliente() {
//		RequestContext.getCurrentInstance().openDialog("ViewNovoCliente");
//	}
//
//	public void visualizaServico(Servico servico) {
//		if (servico != null) {
//			this.servico = servico;
//		}
//	}

	public void redirect(String pagina) {
		try {
			getFacesContext().getExternalContext().redirect(pagina);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

//	public Servico getServico() {
//		return servico;
//	}
//
//	public void setServico(Servico servico) {
//		this.servico = servico;
//	}
//
//	public List<Servico> getServicos() {
//		return servicos;
//	}
//
//	public void setServicos(List<Servico> servicos) {
//		this.servicos = servicos;
//	}
//
//	public List<Servico> getFilterServicos() {
//		return filterServicos;
//	}
//
//	public void setFilterServicos(List<Servico> filterServicos) {
//		this.filterServicos = filterServicos;
//	}
//
//	public List<Marca> getMarcas() {
//		return marcas;
//	}
//
//	public void setMarcas(List<Marca> marcas) {
//		this.marcas = marcas;
//	}
//
//	public List<Modelo> getModelos() {
//		return modelos;
//	}
//
//	public void setModelos(List<Modelo> modelos) {
//		this.modelos = modelos;
//	}

	public boolean isPodeEditar() {
		return podeEditar;
	}

	public void setPodeEditar(boolean podeEditar) {
		this.podeEditar = podeEditar;
	}

	public String getOperacaoSenha() {
		return operacaoSenha;
	}

	public void setOperacaoSenha(String operacaoSenha) {
		this.operacaoSenha = operacaoSenha;
	}

	

	public String getSenhaOperadorFinalizacao() {
		return senhaOperadorFinalizacao;
	}

	public void setSenhaOperadorFinalizacao(String senhaOperadorFinalizacao) {
		this.senhaOperadorFinalizacao = senhaOperadorFinalizacao;
	}

	public Senhas getSenhas() {
		return senhas;
	}

	public void setSenhas(Senhas senhas) {
		this.senhas = senhas;
	}

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

}
