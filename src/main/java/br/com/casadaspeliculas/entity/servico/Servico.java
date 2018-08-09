package br.com.casadaspeliculas.entity.servico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.com.casadaspeliculas.common.ConversorDatasUtil;
import br.com.casadaspeliculas.dto.RelFechamentoCaixaDTO;
import br.com.casadaspeliculas.entity.cliente.Cliente;
import br.com.casadaspeliculas.entity.financeiro.Financeiro;
import br.com.casadaspeliculas.entity.veiculo.Veiculo;

@Entity
@Table(name = "servico")
@SqlResultSetMapping(name = "fechamentoCaixa", 
		entities=@EntityResult(entityClass = RelFechamentoCaixaDTO.class, 
		fields={
				@FieldResult(column="deFormaPgto", name="deFormaPgto"),
				@FieldResult(column="vlTotalFormaPgto", name="vlTotalFormaPgto")				
		})
		
)
@NamedQueries({
	@NamedQuery(name="RelatorioFechamentoCaixa", 
			query="SELECT fp.deFormaPgto AS deFormaPgto, SUM(fi.valor) AS vlTotalFormaPgto "
				+ "FROM Servico s "
				+ "JOIN s.financeiro fi "
				+ "JOIN fi.formaPagamento fp "
				+ "WHERE ((s.dtServico BETWEEN :dataInicial AND :dataFinal AND fi.statusFinanceiro in ('F','A'))"
				+ "OR (fi.dtPagamento BETWEEN :dataInicial AND :dataFinal AND fi.statusFinanceiro in ('P')))"
				+ "AND fi.statusFinanceiro <> 'C' "
				+ "GROUP BY fp.deFormaPgto "
			)
})
public class Servico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idServico;
	@NotNull
	private Date dtServico;
	@NotNull
	private String statusServico;
	private String deServico;
	@NotNull
	private Integer tempoGarantia;
	@NotNull
	private Date dtFinalGarantia;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idFinanceiro")
	private Financeiro financeiro;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idVeiculo")
	private Veiculo veiculo;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "idServico")
	private List<ProdutoServico> produtos;

	public Servico() {
		this.veiculo = new Veiculo();
		this.cliente = new Cliente();
		this.financeiro = new Financeiro();
		this.produtos = new ArrayList<ProdutoServico>();
	}
	
	@Transient
	public String getEstaNaGarantia(){
		return new Date().after(dtFinalGarantia) ? "Fora Garantia":"Em Garantia"; 
	}
	
	@Transient
	public String getStatusServicoFmt(){
		return "A".equals(statusServico) ? "Em Andamento" : "F".equals(statusServico) ? "Finalizado" : "Cancelado";
	}
	
	@Transient
	public String getDtFinalGarantiaFmt(){
		return ConversorDatasUtil.formataDataSemHora(dtFinalGarantia);
	}
	
	@Transient
	public String getDtServicoFmt(){
		return ConversorDatasUtil.formataData(dtServico);
	}

	public Long getIdServico() {
		return idServico;
	}

	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}

	public Date getDtServico() {
		return dtServico;
	}

	public void setDtServico(Date dtServico) {
		this.dtServico = dtServico;
	}

	public String getStatusServico() {
		return statusServico;
	}

	public void setStatusServico(String statusServico) {
		this.statusServico = statusServico;
	}

	public String getDeServico() {
		return deServico;
	}

	public void setDeServico(String deServico) {
		this.deServico = deServico;
	}

	public Integer getTempoGarantia() {
		return tempoGarantia;
	}

	public void setTempoGarantia(Integer tempoGarantia) {
		this.tempoGarantia = tempoGarantia;
	}

	public Date getDtFinalGarantia() {
		return dtFinalGarantia;
	}

	public void setDtFinalGarantia(Date dtFinalGarantia) {
		this.dtFinalGarantia = dtFinalGarantia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Financeiro getFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public List<ProdutoServico> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoServico> produtos) {
		this.produtos = produtos;
	}

	public ProdutoServico getServicoPrestado(TipoServico tipoServico) {

		if (produtos == null) {
			return null;
		}

		for (ProdutoServico servicoPrestado : this.produtos) {
			if (tipoServico.equals(servicoPrestado.getTipoServico())) {
				return servicoPrestado;
			}
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((deServico == null) ? 0 : deServico.hashCode());
		result = prime * result + ((dtFinalGarantia == null) ? 0 : dtFinalGarantia.hashCode());
		result = prime * result + ((dtServico == null) ? 0 : dtServico.hashCode());
		result = prime * result + ((financeiro == null) ? 0 : financeiro.hashCode());
		result = prime * result + ((idServico == null) ? 0 : idServico.hashCode());
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
		result = prime * result + ((statusServico == null) ? 0 : statusServico.hashCode());
		result = prime * result + ((tempoGarantia == null) ? 0 : tempoGarantia.hashCode());
		result = prime * result + ((veiculo == null) ? 0 : veiculo.hashCode());
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
		Servico other = (Servico) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (deServico == null) {
			if (other.deServico != null)
				return false;
		} else if (!deServico.equals(other.deServico))
			return false;
		if (dtFinalGarantia == null) {
			if (other.dtFinalGarantia != null)
				return false;
		} else if (!dtFinalGarantia.equals(other.dtFinalGarantia))
			return false;
		if (dtServico == null) {
			if (other.dtServico != null)
				return false;
		} else if (!dtServico.equals(other.dtServico))
			return false;
		if (financeiro == null) {
			if (other.financeiro != null)
				return false;
		} else if (!financeiro.equals(other.financeiro))
			return false;
		if (idServico == null) {
			if (other.idServico != null)
				return false;
		} else if (!idServico.equals(other.idServico))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		if (statusServico == null) {
			if (other.statusServico != null)
				return false;
		} else if (!statusServico.equals(other.statusServico))
			return false;
		if (tempoGarantia == null) {
			if (other.tempoGarantia != null)
				return false;
		} else if (!tempoGarantia.equals(other.tempoGarantia))
			return false;
		if (veiculo == null) {
			if (other.veiculo != null)
				return false;
		} else if (!veiculo.equals(other.veiculo))
			return false;
		return true;
	}

}
