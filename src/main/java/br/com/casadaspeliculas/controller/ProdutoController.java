package br.com.casadaspeliculas.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ViewAccessScoped;
import org.primefaces.context.RequestContext;

import br.com.casadaspeliculas.entity.produto.Produto;
import br.com.casadaspeliculas.service.produto.ProdutoService;

@Named
@ViewAccessScoped
public class ProdutoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoService produtoService;
	private Produto produto;
	private List<Produto> produtos;
	private List<Produto> produtosAtivos;
	private List<Produto> filterProdutos;

	public ProdutoController() {
		this.produto = new Produto();
	}

	public void updateStatus(Produto produto) {
		if ("A".equals(produto.getStatusProduto())) {
			produto.setStatusProduto("I");
		} else {
			produto.setStatusProduto("A");
		}
		produtoService.save(produto);
	}

	public String novoProduto() {
		this.produto = new Produto();
		return "CadProduto";
	}

	public String editaProduto(Produto produto) {
		this.produto = produto;
		return "CadProduto";
	}

	private void buscaProdutos() {
		this.produtos = produtoService.findAll();
	}
	
	private void buscaProdutosAtivos(){
		this.produtosAtivos = produtoService.findProdutosAtivos();
	}

	public void save() {
		produtoService.save(produto);
		buscaProdutos();
		redirect("ConProduto.jsf");
	}

	public void visualizaProduto(Produto produto) {
		if (produto != null) {
			this.produto = produto;
			RequestContext.getCurrentInstance().openDialog("ViewProduto");
		}
	}

	public void redirect(String pagina) {
		try {
			getFacesContext().getExternalContext().redirect(pagina);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void init() {
		buscaProdutos();
		buscaProdutosAtivos();
	}

	private FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getFilterProdutos() {
		return filterProdutos;
	}

	public void setFilterProdutos(List<Produto> filterProdutos) {
		this.filterProdutos = filterProdutos;
	}

	public List<Produto> getProdutosAtivos() {
		return produtosAtivos;
	}

	public void setProdutosAtivos(List<Produto> produtosAtivos) {
		this.produtosAtivos = produtosAtivos;
	}

	public Produto getProdutoById(Long id) {
		return produtoService.findProdutoById(id);
	}

}
