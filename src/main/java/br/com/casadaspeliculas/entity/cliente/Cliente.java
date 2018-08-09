package br.com.casadaspeliculas.entity.cliente;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	@NotNull
	@Column(unique = true)
	private String nuCliente;
	@NotNull
	private String nmCliente;
	private String deSexo;
	@Temporal(TemporalType.DATE)
	private Date dtNasc;
	private String nuIdentidade;
	@NotNull
	private String deTipoCliente;
	private String deObservacao;
	private String deApelido;
	private String nuInscricaoEstadual;
	@Temporal(TemporalType.DATE)
	private Date dtCadastro;
	@NotNull
	private String statusCliente;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idEndereco")
	private Endereco endereco;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idContato")
	private Contato contato;

	public Cliente() {
		this.statusCliente = "A";
		this.contato = new Contato();
		this.endereco = new Endereco();
	}
	
	@Transient
	public String getNmClienteFmt() {
		return this.nmCliente + " - (" + this.nuCliente + ")";
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNuCliente() {
		return nuCliente;
	}

	public void setNuCliente(String nuCliente) {
		this.nuCliente = nuCliente;
	}

	public String getNmCliente() {
		return nmCliente;
	}

	public void setNmCliente(String nmCliente) {
		this.nmCliente = nmCliente;
	}

	public String getDeSexo() {
		return deSexo;
	}

	public void setDeSexo(String deSexo) {
		this.deSexo = deSexo;
	}

	public Date getDtNasc() {
		return dtNasc;
	}

	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}

	public String getNuIdentidade() {
		return nuIdentidade;
	}

	public void setNuIdentidade(String nuIdentidade) {
		this.nuIdentidade = nuIdentidade;
	}

	public String getDeTipoCliente() {
		return deTipoCliente;
	}
	
	@Transient
	public String getDeTipoClienteFmt() {
		return getDeTipoCliente().equalsIgnoreCase("F") ? "PF" : "PJ";
	}

	public void setDeTipoCliente(String deTipoCliente) {
		this.deTipoCliente = deTipoCliente;
	}

	public String getDeObservacao() {
		return deObservacao;
	}

	public void setDeObservacao(String deObservacao) {
		this.deObservacao = deObservacao;
	}

	public String getDeApelido() {
		return deApelido;
	}

	public void setDeApelido(String deApelido) {
		this.deApelido = deApelido;
	}

	public String getNuInscricaoEstadual() {
		return nuInscricaoEstadual;
	}

	public void setNuInscricaoEstadual(String nuInscricaoEstadual) {
		this.nuInscricaoEstadual = nuInscricaoEstadual;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getStatusCliente() {
		return statusCliente;
	}
	
	@Transient
	public String getStatusClienteFmt() {
		return getStatusCliente().equalsIgnoreCase("A") ? "Ativo" : "Inativo";
	}

	public void setStatusCliente(String statusCliente) {
		this.statusCliente = statusCliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}

}
