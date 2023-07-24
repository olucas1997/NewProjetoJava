package br.com.projetofinal;

public class Corredor extends Atleta {

	private String velocidade;

	public String getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(String velocidade) {
		this.velocidade = velocidade;
	}

	public Corredor(String nome, String numero, String velocidade) {
		super(nome, numero);
		this.velocidade = velocidade;
	}

	@Override
	public String toString() {
		return super.toString() + " Corredor [velocidade=" + velocidade + "]";
	}
	
	
}
