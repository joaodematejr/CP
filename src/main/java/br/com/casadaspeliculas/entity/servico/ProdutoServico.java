package br.com.casadaspeliculas.entity.servico;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.casadaspeliculas.entity.produto.Produto;

@Entity
@Table(name = "servico_produto")
public class ProdutoServico implements Comparable, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProduto", referencedColumnName = "idProduto")
	private Produto produto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idServico", referencedColumnName = "idServico")
	private Servico servico;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "flTipoServico")
	private TipoServico tipoServico;

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	@Override
	public int compareTo(Object o) {
		if(o == null || ! (o instanceof ProdutoServico)){
			return 1;
		}
		
		return tipoServico.compareTo(((ProdutoServico)o).getTipoServico());
	}

}
