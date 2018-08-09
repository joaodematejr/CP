package br.com.casadaspeliculas.entity.senha;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "senhas")
public class Senhas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSenha;
	private String senhaFinalizacao;
	private String senhaEdicao;

	public Long getIdSenha() {
		return idSenha;
	}

	public void setIdSenha(Long idSenha) {
		this.idSenha = idSenha;
	}

	public String getSenhaFinalizacao() {
		return senhaFinalizacao;
	}

	public void setSenhaFinalizacao(String senhaFinalizacao) {
		this.senhaFinalizacao = senhaFinalizacao;
	}

	public String getSenhaEdicao() {
		return senhaEdicao;
	}

	public void setSenhaEdicao(String senhaEdicao) {
		this.senhaEdicao = senhaEdicao;
	}

}
