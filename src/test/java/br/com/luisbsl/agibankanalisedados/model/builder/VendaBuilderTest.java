package br.com.luisbsl.agibankanalisedados.model.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import br.com.luisbsl.agibankanalisedados.exception.DadoInvalidoException;
import br.com.luisbsl.agibankanalisedados.model.Venda;
import br.com.luisbsl.agibankanalisedados.model.builder.VendaBuilder;
import br.com.luisbsl.agibankanalisedados.test.constants.DadosMocados;

/**
 * Casos de testes para classe VendaBuilder
 * 
 * @see {@link VendaBuilder}
 * @author luislima
 *
 */
class VendaBuilderTest {

	@Test
	void buildTest() {
		Venda venda = new VendaBuilder()
							.comId("10")
							.comItens(Arrays.asList(
										DadosMocados.ITENS.get(0),
										DadosMocados.ITENS.get(1),
										DadosMocados.ITENS.get(2)
									 ))
							.comVendedorNome("Pedro")
							.build();
		
		Venda vendaTeste = (Venda) DadosMocados.VENDAS.stream().filter(v -> v.equals(venda)).findFirst().get();
		
		assertNotNull(venda);
		assertNotNull(vendaTeste);
		assertEquals(venda, vendaTeste);
	}
	
	@Test
	void buildTest_deveLevantarExcecao_DadoInvalidoException_ID() {
		Exception exception = assertThrows(
				DadoInvalidoException.class, 
				() -> new VendaBuilder()
							.comId(null)
							.comItens(Arrays.asList(
										DadosMocados.ITENS.get(0),
										DadosMocados.ITENS.get(1),
										DadosMocados.ITENS.get(2)
									 ))
							.comVendedorNome("Pedro")
							.build());
		
		assertTrue(exception.getMessage().contains("ID"));
	}
	
	@Test
	void buildTest_deveLevantarExcecao_DadoInvalidoException_VendedorNome() {
		Exception exception = assertThrows(
				DadoInvalidoException.class, 
				() -> new VendaBuilder()
							.comId("10")
							.comItens(Arrays.asList(
										DadosMocados.ITENS.get(0),
										DadosMocados.ITENS.get(1),
										DadosMocados.ITENS.get(2)
									 ))
							.comVendedorNome(null)
							.build());
		
		assertTrue(exception.getMessage().contains("Vendedor"));
	}
	
	@Test
	void buildTest_deveLevantarExcecao_DadoInvalidoException_Itens() {
		Exception exception = assertThrows(
				DadoInvalidoException.class, 
				() -> new VendaBuilder()
							.comId("10")
							.comItens(null)
							.comVendedorNome("Pedro")
							.build());
		
		assertTrue(exception.getMessage().contains("Itens"));
	}

}
