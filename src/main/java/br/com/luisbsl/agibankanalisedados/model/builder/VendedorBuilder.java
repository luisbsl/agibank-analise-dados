package br.com.luisbsl.agibankanalisedados.model.builder;

import br.com.luisbsl.agibankanalisedados.model.Vendedor;

/**
 * Construtor de Vendedores utilizando do padrão Builder para facilitar na leitura, manuteção e testes
 * 
 * @author luislima
 *
 */
public class VendedorBuilder implements IDadoBuilder {
	
	private Vendedor vendedor;
	
	public VendedorBuilder() {
		vendedor = new Vendedor();
	}
	
	public VendedorBuilder comCpf(String cpf) {
		vendedor.setCpf(cpf);
		return this;
	}
	
	public VendedorBuilder comNome(String nome) {
		vendedor.setNome(nome);
		return this;
	}
	
	public VendedorBuilder comSalario(Double salario) {
		vendedor.setSalario(salario);
		return this;
	}
	
	public Vendedor build() {
		isDadoValido(vendedor);
		return vendedor;
	}

}
