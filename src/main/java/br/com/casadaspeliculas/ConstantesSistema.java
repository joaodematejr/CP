package br.com.casadaspeliculas;

public enum ConstantesSistema {
	A("Andamento");
	
	private final String descricao;
	
	private ConstantesSistema(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
