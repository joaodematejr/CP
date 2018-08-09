package br.com.casadaspeliculas.service.produto;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.casadaspeliculas.dao.ProdutoDao;
import br.com.casadaspeliculas.entity.produto.Produto;

@Stateless
public class ProdutoServiceImpl implements ProdutoService {

	@Inject
	private ProdutoDao produtoDao;

	@Override
	public void save(Produto produto) {
		produtoDao.save(produto);
	}

	@Override
	public List<Produto> findAll() {
		return produtoDao.findAll();
	}

	@Override
	public List<Produto> findProdutosAtivos() {
		return produtoDao.findProdutosAtivos();
	}

	@Override
	public Produto findProdutoById(Long id){
		return produtoDao.findProdutoById(id);
	}
}
