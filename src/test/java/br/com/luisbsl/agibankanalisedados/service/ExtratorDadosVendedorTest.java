package br.com.luisbsl.agibankanalisedados.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import br.com.luisbsl.agibankanalisedados.enums.TipoDadoEnum;
import br.com.luisbsl.agibankanalisedados.exception.ConversaoDadoException;
import br.com.luisbsl.agibankanalisedados.exception.LinhaArquivoInvalidaException;
import br.com.luisbsl.agibankanalisedados.model.Linha;
import br.com.luisbsl.agibankanalisedados.model.Vendedor;
import br.com.luisbsl.agibankanalisedados.service.ExtratorDadosVendedor;
import br.com.luisbsl.agibankanalisedados.test.constants.DadosMocados;

/**
 * Casos de testes para classe ExtratorDadosVendedor
 * 
 * @see {@link ExtratorDadosVendedor}
 * @author luislima
 *
 */
class ExtratorDadosVendedorTest {
	private ExtratorDadosVendedor extrator = (ExtratorDadosVendedor) TipoDadoEnum.VENDEDOR.obterExtratorDado();
	private Linha linhaVendedorValida = new Linha(DadosMocados.LINHA_TIPO_VENDEDOR, 20);
	private Linha linhaVendedorInvalida = new Linha("001ç3245678865434çPauloçabc", 10);
	
	@Test
	void validarPadraoLinhaTest_deveRetornarTrue() {
		Boolean linhaValida = extrator.validarPadraoLinha(DadosMocados.LINHA_TIPO_VENDEDOR);
		
		assertTrue(linhaValida);
	}
	
	@Test
	void validarPadraoLinhaTest_deveRetornarFalse() {
		Boolean linhaValida = extrator.validarPadraoLinha(DadosMocados.LINHA_TIPO_VENDA);
		
		assertFalse(linhaValida);
	}
	
	@Test
	void converterTest() {
		Vendedor vendedor = (Vendedor) extrator.converter(linhaVendedorValida, DadosMocados.DADOS_LINHA_VENDEDOR);
		
		assertNotNull(vendedor);
		assertEquals("Paulo", vendedor.getNome());
	}
	
	@Test
	void converterTest_deveLevantarExcecao_ConversaoDadoException() {	
		Exception exception = assertThrows(
				ConversaoDadoException.class, 
				() -> extrator.converter(linhaVendedorInvalida, Arrays.asList("001", "3245678865434", "Paulo", "abc")));
		assertTrue(exception.getMessage().contains("Não foi possível converter para o Tipo de Dados - VENDEDOR"));
	}
	
	@Test
	void extrairTest() {
		Vendedor vendedor = (Vendedor) extrator.extrair(linhaVendedorValida);
		
		assertNotNull(vendedor);
		assertEquals("Paulo", vendedor.getNome());
	}
	
	@Test
	void extrairTest_deveLancarExcecao_LinhaArquivoInvalidaException() {
		Exception exception = assertThrows(
				LinhaArquivoInvalidaException.class, 
				() -> extrator.extrair(linhaVendedorInvalida));
		
		assertTrue(exception.getMessage().contains(linhaVendedorInvalida.getConteudo()));
	}

}
