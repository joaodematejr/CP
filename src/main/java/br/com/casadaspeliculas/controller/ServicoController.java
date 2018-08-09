package br.com.casadaspeliculas.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.event.FlowEvent;

import br.com.casadaspeliculas.entity.servico.ProdutoServico;
import br.com.casadaspeliculas.entity.servico.Servico;

@Named
@ViewAccessScoped
public class ServicoController extends ActionsServicoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean skip;
	private List<Servico> servicos;

	@PostConstruct
	public void init() {
		setServico((Servico) getParameter("Servico"));
		setSenhas(carregaSenhas());

		if (getServico() == null && !Boolean.valueOf(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("osAndamento"))) {
			buscaServicos();
		}
	}

	public void validaFinalizacaoServico() {
		if ((getSenhaOperadorFinalizacao() == null || "".equals(getSenhaOperadorFinalizacao()))) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Validação de Senha", "É preciso informar uma senha !!!"));
		} else {
			if (getSenhas().getSenhaFinalizacao().equalsIgnoreCase(getSenhaOperadorFinalizacao())) {
				finalizarServico();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Validação de Senha", "A senha não confere !!!"));
			}
		}
	}

	private void buscaServicos() {
		this.servicos = getServicoService().findAll();
	}

	public void save() {
		removerProdutosNaoSelecionadosDoServico();
		if (getServico().getFinanceiro().isFlFaturar()) {
			getServico().getFinanceiro().setStatusFinanceiro("F");
		}

		buildDataFinalGarantia();
		getServico().getVeiculo().setNuPlaca(getServico().getVeiculo().getNuPlaca().toUpperCase());
		getServicoService().save(getServico());
		redirect("OSAndamento.jsf");
	}

	public void cancelarServico() {
		getServico().getFinanceiro().setStatusFinanceiro("C");
		getServico().setStatusServico("C");
		save();
	}

	public void finalizarServico() {
		getServico().getFinanceiro().setStatusFinanceiro("P");
		getServico().getFinanceiro().setDtPagamento(new Date());
		getServico().setStatusServico("F");
		buildDataFinalGarantia();
		save();
	}

	private void buildDataFinalGarantia() {
		if (getServico().getTempoGarantia() == 1) {
			buildTempoGarantia(1);
		} else if (getServico().getTempoGarantia() == 2) {
			buildTempoGarantia(2);
		} else if (getServico().getTempoGarantia() == 3) {
			buildTempoGarantia(3);
		} else {
			buildTempoGarantia(-1);
		}
	}

	private void buildTempoGarantia(int tempo) {
		Calendar c = Calendar.getInstance();
		c.setTime(getServico().getDtServico());
		c.set(Calendar.YEAR, c.get(Calendar.YEAR) + (tempo));
		getServico().setDtFinalGarantia(c.getTime());
	}

	private void removerProdutosNaoSelecionadosDoServico() {
		List<ProdutoServico> produtosSelecionados = new ArrayList<ProdutoServico>();
		for (ProdutoServico produtoServico : getServico().getProdutos()) {
			if (produtoServico.getProduto() != null) {
				produtosSelecionados.add(produtoServico);
			}
		}
		getServico().setProdutos(produtosSelecionados);
	}

	public void visualizaServico(Servico servico) {
		if (servico != null) {
			setServico(servico);
		}
	}
	
	public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}
