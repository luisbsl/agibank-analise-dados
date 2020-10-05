package br.com.luisbsl.agibankanalisedados.model.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.luisbsl.agibankanalisedados.exception.DadoInvalidoException;
import br.com.luisbsl.agibankanalisedados.model.Item;
import br.com.luisbsl.agibankanalisedados.model.builder.ItemBuilder;
import br.com.luisbsl.agibankanalisedados.test.constants.DadosMocados;

/**
 * Casos de testes para classe ItemBuilder
 * 
 * @see {@link ItemBuilder}
 * @author luislima
 *
 */
class ItemBuilderTest {

	@Test
	void buildTest() {
		Item item = new ItemBuilder()
							.comId(1)
							.comQuantidade(10)
							.comPreco(100.0)
							.build();
		
		Item itemTeste = DadosMocados.ITENS.stream().filter(i -> i.equals(item)).findFirst().get(); 
		
		assertNotNull(item);
		assertNotNull(itemTeste);
		assertEquals(item, itemTeste);
	}
	
	@Test
	void buildTest_deveLevantarExcecao_DadoInvalidoException_ID() {
		Exception exception = assertThrows(
				DadoInvalidoException.class, 
				() -> new ItemBuilder()
							.comId(null)
							.comQuantidade(10)
							.comPreco(100.0)
							.build());
		
		assertTrue(exception.getMessage().contains("ID"));
	}
	
	@Test
	void buildTest_deveLevantarExcecao_DadoInvalidoException_Quantidade() {
		Exception exception = assertThrows(
				DadoInvalidoException.class, 
				() -> new ItemBuilder()
							.comId(1)
							.comQuantidade(null)
							.comPreco(100.0)
							.build());
		
		assertTrue(exception.getMessage().contains("Quantidade"));
	}
	
	@Test
	void buildTest_deveLevantarExcecao_DadoInvalidoException_Preco() {
		Exception exception = assertThrows(
				DadoInvalidoException.class, 
				() -> new ItemBuilder()
							.comId(1)
							.comQuantidade(10)
							.comPreco(null)
							.build());
		
		assertTrue(exception.getMessage().contains("Preço"));
	}

}
