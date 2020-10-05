package br.com.luisbsl.agibankanalisedados.model.builder;

import java.util.List;

import br.com.luisbsl.agibankanalisedados.model.Item;
import br.com.luisbsl.agibankanalisedados.model.Venda;

/**
 * Construtor de Vendas utilizando do padrão Builder para facilitar na leitura, manuteção e testes
 * 
 * @author luislima
 *
 */
public class VendaBuilder implements IDadoBuilder {
	
	private Venda venda;
	
	public VendaBuilder() {
		venda = new Venda();
	}
	
	public VendaBuilder comId(String id) {
		venda.setId(id);
		return this;
	}
	
	public VendaBuilder comVendedorNome(String vendedorNome) {
		venda.setVendedorNome(vendedorNome);
		return this;		
	}
	
	public VendaBuilder comItens(List<Item> itens) {
		venda.setItens(itens);
		return this;
	}
	
	@Override
	public Venda build() {
		isDadoValido(venda);
		return venda;
	}

}
