package br.com.luisbsl.agibankanalisedados.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Classe que representa um Vendedor extraído do arquivo
 * 
 * @author luislima
 *
 */
public class Vendedor implements Dado, Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "CPF do Vendedor não pode ser nulo ou vazio")
	private String cpf;
	
	@NotBlank(message = "Nome do Vendedor não pode ser nulo ou vazio")
	private String nome;
	
	@NotNull(message = "Salário do Vendedor não pode ser nulo")
	private Double salario;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedor other = (Vendedor) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Vendedor [cpf=" + cpf + ", nome=" + nome + ", salario=" + salario + "]";
	}

}
