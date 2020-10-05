package br.com.luisbsl.agibankanalisedados.service.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.luisbsl.agibankanalisedados.exception.TipoDadoNaoEncontradoException;
import br.com.luisbsl.agibankanalisedados.model.Cliente;
import br.com.luisbsl.agibankanalisedados.model.Item;
import br.com.luisbsl.agibankanalisedados.model.Linha;
import br.com.luisbsl.agibankanalisedados.model.Venda;
import br.com.luisbsl.agibankanalisedados.model.Vendedor;
import br.com.luisbsl.agibankanalisedados.service.facade.ExtratorDadoFacade;
import br.com.luisbsl.agibankanalisedados.test.constants.DadosMocados;

/**
 * Casos de testes para classe ExtratorDadoFacade
 * 
 * @see {@link ExtratorDadoFacade}
 * @author luislima
 *
 */
class ExtratorDadoFacadeTest {
	

	@Test
	void extrairTest_deveRetornar_Vendedor() {
		ExtratorDadoFacade facade = new ExtratorDadoFacade(new Linha(DadosMocados.LINHA_TIPO_VENDEDOR, 10));
		Vendedor vendedor = (Vendedor) facade.extrair();
		
		assertNotNull(vendedor);
		assertEquals("3245678865434", vendedor.getCpf());
		assertEquals("Paulo", vendedor.getNome());
	}
	
	@Test
	void extrairTest_deveRetornar_Cliente() {
		ExtratorDadoFacade facade = new ExtratorDadoFacade(new Linha(DadosMocados.LINHA_TIPO_CLIENTE, 10));
		Cliente cliente = (Cliente) facade.extrair();
		
		assertNotNull(cliente);
		assertEquals("2345675433444345", cliente.getCnpj());
		assertEquals("Eduardo Pereira", cliente.getNome());
	}
	
	@Test
	void extrairTest_deveRetornar_Venda() {
		ExtratorDadoFacade facade = new ExtratorDadoFacade(new Linha(DadosMocados.LINHA_TIPO_VENDA, 10));
		Venda venda = (Venda) facade.extrair();
		List<Item> itens = venda.getItens();
		
		assertNotNull(venda);
		assertEquals("10", venda.getId());
		assertEquals(1199d, venda.getValorTotal());
		assertFalse(itens.isEmpty());
		assertEquals(1000d, venda.getItens().get(0).getValorTotal());
	}
	
	@Test
	void extrairTest_deveLancarExcecao_TipoDadoNaoEncontradoException() {
		Exception exception = assertThrows(
				TipoDadoNaoEncontradoException.class, 
				() -> new ExtratorDadoFacade(new Linha("004", 10)).extrair());
		
		System.out.println(exception.getMessage());
		assertTrue(exception.getMessage().contains("Tipo de dado não encontrado. (tipos esperados: 001, 002, 003)"));
	}
	
	public static void main(String[] args) {
		new ExtratorDadoFacade(new Linha("004", 10)).extrair();
	}

}
