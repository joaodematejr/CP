package br.com.casadaspeliculas.common;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class MenuController {

	public String home() {
		return "/index.html";
	}

	public String clienteConsultar() {
		return "/pages/ConCliente.jsf";
	}

	public String clienteCadastrar() {
		return "/pages/CadCliente.jsf";
	}

	public String formaPagamentoConsultar() {
		return "/pages/ConFormaPagamento.jsf";
	}

	public String formaPagamentoCadastrar() {
		return "/pages/CadFormaPagamento.jsf";
	}

	public String veiculoCadastrar() {
		return "/pages/CadVeiculo.jsf";
	}

	public String veiculoConsultar() {
		return "/pages/ConVeiculo.jsf";
	}

	public String marcaConsultar() {
		return "/pages/ConMarca.jsf";
	}

	public String marcaCadastrar() {
		return "/pages/CadMarca.jsf";
	}

	public String modeloConsultar() {
		return "/pages/ConModelo.jsf";
	}

	public String modeloCadastrar() {
		return "/pages/CadModelo.jsf";
	}
	
	public String produtoConsultar() {
		return "/pages/ConProduto.jsf";
	}
	
	public String produtoCadastrar() {
		return "/pages/CadProduto.jsf";
	}
	
	public String servicoConsultar() {
		return "/pages/ConServico.jsf";
	}
	
	public String servicoCadastrar() {
		return "/pages/CadServico.jsf";
	}
	
	public String relFechamentoCaixa() {
		return "/pages/RelFechamentoCaixa.jsf";
	}
}
