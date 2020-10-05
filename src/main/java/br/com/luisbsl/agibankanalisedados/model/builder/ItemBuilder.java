package br.com.luisbsl.agibankanalisedados.model.builder;

import br.com.luisbsl.agibankanalisedados.model.Item;

/**
 * Construtor de Itens utilizando do padrão Builder para facilitar na leitura, manuteção e testes
 * 
 * @author luislima
 *
 */
public class ItemBuilder implements IDadoBuilder {
	
	private Item item;
	
	public ItemBuilder() {
		item = new Item();
	}
	
	public ItemBuilder comId(Integer id) {
		item.setId(id);
		return this;
	}
	
	public ItemBuilder comQuantidade(Integer quantidade) {
		item.setQuantidade(quantidade);
		return this;
	}
	
	public ItemBuilder comPreco(Double preco) {
		item.setPreco(preco);
		return this;
	}

	@Override
	public Item build() {
		isDadoValido(item);
		return item;
	}

}
