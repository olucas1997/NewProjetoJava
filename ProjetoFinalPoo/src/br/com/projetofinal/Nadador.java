package br.com.projetofinal;

public class Nadador extends Atleta{

	private String estilo;

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public Nadador(String nome, String numero, String estilo) {
		super(nome, numero);
		this.estilo = estilo;
	}

	@Override
	public String toString() {
		return super.toString() + " Nadador [estilo=" + estilo + "]";
	}
	
	
	
	
}
