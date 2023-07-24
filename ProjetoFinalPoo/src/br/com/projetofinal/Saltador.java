package br.com.projetofinal;

public class Saltador extends Atleta {
	private String altura;

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public Saltador(String nome, String numero, String altura) {
		super(nome, numero);
		this.altura = altura;
	}

	@Override
	public String toString() {
		return super.toString() + " Saltador [altura=" + altura + "]";
	}
	
	
}
