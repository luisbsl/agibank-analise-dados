package br.com.luisbsl.agibankanalisedados.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.luisbsl.agibankanalisedados.enums.ExtratorDadosEnum;
import br.com.luisbsl.agibankanalisedados.exception.ConversaoDadoException;
import br.com.luisbsl.agibankanalisedados.exception.LinhaArquivoInvalidaException;
import br.com.luisbsl.agibankanalisedados.model.Item;
import br.com.luisbsl.agibankanalisedados.model.Linha;
import br.com.luisbsl.agibankanalisedados.service.ExtratorDadosItem;
import br.com.luisbsl.agibankanalisedados.test.constants.DadosMocados;

/**
 * Casos de testes para classe ExtratorDadosItem
 * 
 * @see {@link ExtratorDadosItem}
 * @author luislima
 *
 */
class ExtratorDadosItemTest {
	
	private ExtratorDadosItem extrator = new ExtratorDadosItem(ExtratorDadosEnum.ITEM);
	private Linha linhaItemValida = new Linha(DadosMocados.LINHA_TIPO_VENDA, 20);
	private Linha linhaItemInvalida = new Linha("002ç2345675433444345çEduardo Pereira", 10);
	
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
		Item itemMocado = DadosMocados.ITENS.get(0);
		List<String> dadosItem = Arrays.asList(itemMocado.getId().toString(), itemMocado.getQuantidade().toString(), itemMocado.getPreco().toString());
		Item item = (Item) extrator.converter(linhaItemValida, dadosItem);
		
		assertNotNull(item);
		assertEquals(100.0, item.getPreco());
	}
	
	@Test
	void extrairItensTest() {
		List<Item> itens = extrator.extrairItens(linhaItemValida);
		
		assertNotNull(itens);
		assertNotEquals(0, itens.size());
	}
	
	@Test
	void extrairItensTest_deveLancarExcecao_LinhaArquivoInvalidaException() {
		Exception exception = assertThrows(
				LinhaArquivoInvalidaException.class, 
				() -> extrator.extrairItens(linhaItemInvalida));
		
		assertTrue(exception.getMessage().contains(linhaItemInvalida.getConteudo()));
	}
	
	@Test
	void converterTest_deveLevantarExcecao_ConversaoDadoException() {	
		Exception exception = assertThrows(
				ConversaoDadoException.class, 
				() -> extrator.converter(linhaItemInvalida, Arrays.asList("001", "3245678865434", "Paulo", null)));
		assertTrue(exception.getMessage().contains("Não foi possível converter para o Tipo de Dados - ITEM"));
	}

}
