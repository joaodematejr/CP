package br.com.casadaspeliculas.service.produto;

import java.util.List;

import javax.ejb.Local;

import br.com.casadaspeliculas.entity.produto.Produto;

@Local
public interface ProdutoService {

	void save(Produto produto);

	List<Produto> findAll();

	List<Produto> findProdutosAtivos();

	Produto findProdutoById(Long id);

}
