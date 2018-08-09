package br.com.casadaspeliculas.service.servico;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.casadaspeliculas.dao.ServicoDao;
import br.com.casadaspeliculas.entity.senha.Senhas;
import br.com.casadaspeliculas.entity.servico.Servico;
import br.com.casadaspeliculas.entity.veiculo.Marca;
import br.com.casadaspeliculas.entity.veiculo.Modelo;

@Stateless
public class ServicoServiceImpl implements ServicoService {

	@Inject
	private ServicoDao servicoDao;

	@Override
	public void save(Servico servico) {
		servicoDao.save(servico);
	}

	@Override
	public List<Servico> findAll() {
		return servicoDao.findAll();
	}

	@Override
	public List<Servico> findAllByDay() {
		return servicoDao.findAllByDay(new Date());
//		DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
//		df.setLenient (false); 
//		try {
//			return servicoDao.findAllByDay(df.parse ("22/01/2015"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
	}

	@Override
	public List<Marca> getMarcas() {
		return servicoDao.getMarcas();
	}

	@Override
	public List<Modelo> getModelos(Long idMarca) {
		return servicoDao.getModelos(idMarca);
	}

	@Override
	public Senhas getSenhas() {
		return servicoDao.getSenhas();
	}


}
