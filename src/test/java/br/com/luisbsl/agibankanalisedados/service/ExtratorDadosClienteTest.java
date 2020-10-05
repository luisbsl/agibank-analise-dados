package br.com.luisbsl.agibankanalisedados.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import br.com.luisbsl.agibankanalisedados.enums.TipoDadoEnum;
import br.com.luisbsl.agibankanalisedados.exception.DadoInvalidoException;
import br.com.luisbsl.agibankanalisedados.exception.LinhaArquivoInvalidaException;
import br.com.luisbsl.agibankanalisedados.model.Cliente;
import br.com.luisbsl.agibankanalisedados.model.Linha;
import br.com.luisbsl.agibankanalisedados.service.ExtratorDadosCliente;
import br.com.luisbsl.agibankanalisedados.test.constants.DadosMocados;

/**
 * Casos de testes para classe ExtratorDadosCliente
 * 
 * @see {@link ExtratorDadosCliente}
 * @author luislima
 *
 */
class ExtratorDadosClienteTest {
	
	private ExtratorDadosCliente extrator = (ExtratorDadosCliente) TipoDadoEnum.CLIENTE.obterExtratorDado();
	private Linha linhaClienteValida = new Linha(DadosMocados.LINHA_TIPO_CLIENTE, 20);
	private Linha linhaClienteInvalida = new Linha("002ç2345675433444345çEduardo Pereira", 10);
	
	@Test
	void validarPadraoLinhaTest_deveRetornarTrue() {
		Boolean linhaValida = extrator.validarPadraoLinha(DadosMocados.LINHA_TIPO_CLIENTE);
		
		assertTrue(linhaValida);
	}
	
	@Test
	void validarPadraoLinhaTest_deveRetornarFalse() {
		Boolean linhaValida = extrator.validarPadraoLinha(DadosMocados.LINHA_TIPO_VENDA);
		
		assertFalse(linhaValida);
	}
	
	@Test
	void converterTest() {
		Cliente cliente = (Cliente) extrator.converter(linhaClienteValida, DadosMocados.DADOS_LINHA_CLIENTE);
		
		assertNotNull(cliente);
		assertEquals("Jose da Silva", cliente.getNome());
	}
	
	@Test
	void converterTest_deveLevantarExcecao_DadoInvalidoException() {	
		Exception exception = assertThrows(
				DadoInvalidoException.class, 
				() -> extrator.converter(linhaClienteInvalida, Arrays.asList("001", "3245678865434", "Paulo", null)));
		assertTrue(exception.getMessage().contains("Area de Negócio do cliente não pode ser nula ou vazia"));
	}
	
	@Test
	void extrairTest() {
		Cliente cliente = (Cliente) extrator.extrair(linhaClienteValida);
		
		assertNotNull(cliente);
		assertEquals("Eduardo Pereira", cliente.getNome());
	}
	
	@Test
	void extrairTest_deveLancarExcecao_LinhaArquivoInvalidaException() {
		Exception exception = assertThrows(
				LinhaArquivoInvalidaException.class, 
				() -> extrator.extrair(linhaClienteInvalida));
		
		assertTrue(exception.getMessage().contains(linhaClienteInvalida.getConteudo()));
	}

}
