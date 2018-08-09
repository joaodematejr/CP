package br.com.casadaspeliculas.entity.financeiro;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.com.casadaspeliculas.common.ConversorDatasUtil;
import br.com.casadaspeliculas.common.Moeda;
import br.com.casadaspeliculas.entity.formapagamento.FormaPagamento;

@Entity
@Table(name = "financeiro")
public class Financeiro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFinanceiro;
	private Double valor;
	@NotNull
	private String statusFinanceiro;
	private Date dtPagamento;
	private boolean flFaturar;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idFormaPgto")
	private FormaPagamento formaPagamento;

	@Transient
	public String getValorFmt() {
		return Moeda.mascaraDinheiro(valor, Moeda.DINHEIRO_REAL);
	}

	@Transient
	public String getStatusFinanceiroFmt() {
		if ("A".equals(statusFinanceiro)) {
			return "Aguardando Pagamento (" + formaPagamento.getDeFormaPgto() + ")";
		} else if ("P".equals(statusFinanceiro)) {
			return "Pago (" + formaPagamento.getDeFormaPgto() + ")";
		} else {
			return "À Faturar (" + formaPagamento.getDeFormaPgto() + ")";
		}
	}
	
	@Transient
	public String getDtPagamentoFmt(){
		if (dtPagamento == null) {
			return "Aguardando Pagamento (À Faturar)";
		}
		return ConversorDatasUtil.formataDataSemHora(dtPagamento);
	}

	public Long getIdFinanceiro() {
		return idFinanceiro;
	}

	public void setIdFinanceiro(Long idFinanceiro) {
		this.idFinanceiro = idFinanceiro;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getStatusFinanceiro() {
		return statusFinanceiro;
	}

	public void setStatusFinanceiro(String statusFinanceiro) {
		this.statusFinanceiro = statusFinanceiro;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Date getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(Date dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	public boolean isFlFaturar() {
		return flFaturar;
	}

	public void setFlFaturar(boolean flFaturar) {
		this.flFaturar = flFaturar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtPagamento == null) ? 0 : dtPagamento.hashCode());
		result = prime * result + (flFaturar ? 1231 : 1237);
		result = prime * result + ((formaPagamento == null) ? 0 : formaPagamento.hashCode());
		result = prime * result + ((idFinanceiro == null) ? 0 : idFinanceiro.hashCode());
		result = prime * result + ((statusFinanceiro == null) ? 0 : statusFinanceiro.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Financeiro other = (Financeiro) obj;
		if (dtPagamento == null) {
			if (other.dtPagamento != null)
				return false;
		} else if (!dtPagamento.equals(other.dtPagamento))
			return false;
		if (flFaturar != other.flFaturar)
			return false;
		if (formaPagamento == null) {
			if (other.formaPagamento != null)
				return false;
		} else if (!formaPagamento.equals(other.formaPagamento))
			return false;
		if (idFinanceiro == null) {
			if (other.idFinanceiro != null)
				return false;
		} else if (!idFinanceiro.equals(other.idFinanceiro))
			return false;
		if (statusFinanceiro == null) {
			if (other.statusFinanceiro != null)
				return false;
		} else if (!statusFinanceiro.equals(other.statusFinanceiro))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

}
