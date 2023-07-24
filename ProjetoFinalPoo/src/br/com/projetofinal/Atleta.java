package br.com.projetofinal;

import java.io.Serializable;

public class Atleta implements Serializable{

	private String nome;
	private String numero;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Atleta(String nome, String numero) {
		super();
		this.nome = nome;
		this.numero = numero;
	}
	@Override
	public String toString() {
		String retorno;

		retorno =  "--------------------------" +
				"--------------------------\n" +
				"Nome: " + this.getNome() +  "\n" +
				"Número: " + this.getNumero() + "\n";
		return retorno;
	}
	
	
	
	
}
