package br.com.casadaspeliculas.entity.servico;

/**
 * @author Nildo Beppler Jr
 *
 */
public enum TipoServico implements Comparable<TipoServico>{
	PELICULA_PARABRISA_DIANTEIRO("Parabrisa Dianteiro"),
	PELICULA_DIANTEIRO_ESQUERDO("Dianteiro Esquerdo"),
	PELICULA_DIANTEIRO_DIREITO("Dianteiro Direito"),
	PELICULA_TRASEIRO_ESQUERDO("Traseiro Esquerdo"),
	PELICULA_TRASEIRO_DIREITO("Traseiro Direito"),
	PELICULA_PARABRISA_TRASEIRO("Parabrisa Traseiro");
	
	private final String descricao;

	private TipoServico(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
