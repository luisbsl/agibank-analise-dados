package br.com.luisbsl.agibankanalisedados.model;

/**
 * Classe que representa uma Linha do aquivo
 * Utilizada para facilitar o transporte de informções:
 * - aos Serviços Extratores
 * - às Exceções
 * - aos Logs
 * 
 * @author luislima
 *
 */
public class Linha {

	private String conteudo;
	private Integer numero;

	public Linha(String conteudo, Integer numero) {
		super();
		this.conteudo = conteudo;
		this.numero = numero;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Linha Nº "+numero+" - "+conteudo;
	}

}
