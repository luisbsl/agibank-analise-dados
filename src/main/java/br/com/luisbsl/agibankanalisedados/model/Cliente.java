package br.com.luisbsl.agibankanalisedados.model;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

/**
 * Classe que representa um Cliente extraído do arquivo
 * 
 * @author luislima
 *
 */
public class Cliente implements Dado, Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "CNPJ do cliente não pode ser nulo ou vazio")
	private String cnpj;

	@NotBlank(message = "Nome do cliente não pode ser nulo ou vazio")
	private String nome;

	@NotBlank(message = "Area de Negócio do cliente não pode ser nula ou vazia")
	private String areaNegocio;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAreaNegocio() {
		return areaNegocio;
	}

	public void setAreaNegocio(String areaNegocio) {
		this.areaNegocio = areaNegocio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cnpj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Cliente that = (Cliente) obj;
		return Objects.equals(cnpj, that.cnpj);
	}

	@Override
	public String toString() {
		return "Cliente [cnpj=" + cnpj + ", nome=" + nome + ", areaNegocio=" + areaNegocio + "]";
	}

}
