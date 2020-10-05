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
import br.com.luisbsl.agibankanalisedados.model.Venda;
import br.com.luisbsl.agibankanalisedados.service.ExtratorDadosVenda;
import br.com.luisbsl.agibankanalisedados.test.constants.DadosMocados;

/**
 * Casos de testes para classe ExtratorDadosVenda
 * 
 * @see {@link ExtratorDadosVenda}
 * @author luislima
 *
 */
class ExtratorDadosVendaTest {
	
	private ExtratorDadosVenda extrator = (ExtratorDadosVenda) TipoDadoEnum.VENDA.obterExtratorDado();
	private Linha linhaVendaValida = new Linha(DadosMocados.LINHA_TIPO_VENDA, 20);
	private Linha linhaVendaInvalida = new Linha("002ç2345675433444345çEduardo Pereira", 10);
	
	@Test
	void validarPadraoLinhaTest_deveRetornarTrue() {
		Boolean linhaValida = extrator.validarPadraoLinha(DadosMocados.LINHA_TIPO_VENDA);
		
		assertTrue(linhaValida);
	}
	
	@Test
	void validarPadraoLinhaTest_deveRetornarFalse() {
		Boolean linhaValida = extrator.validarPadraoLinha(DadosMocados.LINHA_TIPO_CLIENTE);
		
		assertFalse(linhaValida);
	}
	
	@Test
	void converterTest() {
		Venda venda = (Venda) extrator.converter(linhaVendaValida, DadosMocados.DADOS_LINHA_VENDA);

		assertNotNull(venda);
		assertEquals("10", venda.getId());
	}
	
	@Test
	void converterTest_deveLevantarExcecao_ConversaoDadoException() {	
		Exception exception = assertThrows(
				ConversaoDadoException.class, 
				() -> extrator.converter(linhaVendaInvalida, Arrays.asList("001", "3245678865434", "Paulo", null)));
		assertTrue(exception.getMessage().contains("Não foi possível converter para o Tipo de Dados - VENDA"));
	}
	
	@Test
	public void extrairTest() {
		Venda venda = (Venda) extrator.extrair(linhaVendaValida);

		assertNotNull(venda);
		assertEquals("Pedro", venda.getVendedorNome());
	}
	
	@Test
	void extrairTest_deveLancarExcecao_LinhaArquivoInvalidaException() {
		Exception exception = assertThrows(
				LinhaArquivoInvalidaException.class, 
				() -> extrator.extrair(linhaVendaInvalida));
		
		assertTrue(exception.getMessage().contains(linhaVendaInvalida.getConteudo()));
	}

}
