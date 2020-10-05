package br.com.luisbsl.agibankanalisedados.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import br.com.luisbsl.agibankanalisedados.test.constants.DadosMocados;

/**
 * Casos de testes para classe ExtratorDadosEnum
 * 
 * @see {@link ExtratorDadosEnum}
 * @author luislima
 *
 */
class ExtratorDadosEnumTest {
	
	@Test
	void getRegexPadraoTest_deveRetornar_RegexPadrao_Vendedor() {
		String regexVendedor = ExtratorDadosEnum.VENDEDOR.getRegexPadrao();
		
		assertNotNull(regexVendedor);
		assertEquals(DadosMocados.REGEX_PADRAO_VENDEDOR, regexVendedor);
	}
	
	@Test
	void getRegexPadraoTest_deveRetornar_RegexPadrao_Cliente() {
		String regexCliente = ExtratorDadosEnum.CLIENTE.getRegexPadrao();
		
		assertNotNull(regexCliente);
		assertEquals(DadosMocados.REGEX_PADRAO_CLIENTE, regexCliente);
	}
	
	@Test
	void getRegexPadraoTest_deveRetornar_RegexPadrao_Venda() {
		String regexVenda = ExtratorDadosEnum.VENDA.getRegexPadrao();
		
		assertNotNull(regexVenda);
		assertEquals(DadosMocados.REGEX_PADRAO_VENDA, regexVenda);
	}
	
	@Test
	void getRegexPadraoTest_deveRetornar_RegexPadrao_Item() {
		String regexItem = ExtratorDadosEnum.ITEM.getRegexPadrao();
		
		assertNotNull(regexItem);
		assertEquals(DadosMocados.REGEX_PADRAO_ITEM, regexItem);
	}

}
