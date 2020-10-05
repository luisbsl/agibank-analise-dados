package br.com.luisbsl.agibankanalisedados.model.builder;

import br.com.luisbsl.agibankanalisedados.model.Cliente;

/**
 * Construtor de Clientes utilizando do padrão Builder para facilitar na leitura, manuteção e testes
 * 
 * @author luislima
 *
 */
public class ClienteBuilder implements IDadoBuilder {
	
	private Cliente cliente;
	
	public ClienteBuilder() {
		cliente = new Cliente();
	}
	
	public ClienteBuilder comCnpj(String cnpj) {
		cliente.setCnpj(cnpj);
		return this;
	}
	
	public ClienteBuilder comNome(String nome) {
		cliente.setNome(nome);
		return this;
	}
	
	public ClienteBuilder comAreaNegocio(String areaNegocio) {
		cliente.setAreaNegocio(areaNegocio);
		return this;
	}

	@Override
	public Cliente build() {
		isDadoValido(cliente);
		return cliente;
	}

}
