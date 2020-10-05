package br.com.luisbsl.agibankanalisedados.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import br.com.luisbsl.agibankanalisedados.model.Venda;
import br.com.luisbsl.agibankanalisedados.model.Vendedor;
import br.com.luisbsl.agibankanalisedados.service.ProcessadorVendas;
import br.com.luisbsl.agibankanalisedados.test.constants.DadosMocados;

/**
 * Casos de testes para classe ProcessadorVendas
 * 
 * @see {@link ProcessadorVendas}
 * @author luislima
 *
 */
class ProcessadorVendasTest {

	ProcessadorVendas processadorVendas = new ProcessadorVendas(DadosMocados.DADOS_VENDA);

	@Test
	void calcularQuantidadeClientesTest() {
		Long qtdClientes = processadorVendas.calcularQuantidadeClientes();

		assertEquals(2L, qtdClientes);
	}

	@Test
	void calcularQuantidadeVendedoresTest() {
		Long qtdVendedores = processadorVendas.calcularQuantidadeVendedores();

		assertEquals(2L, qtdVendedores);
	}

	@Test
	void filtrarDadosTipoVendaTest() {
		List<Venda> vendas = processadorVendas.filtrarDadosTipoVenda().collect(Collectors.toList());

		assertNotNull(vendas);
		assertFalse(vendas.isEmpty());
		assertEquals(2, vendas.size());
	}

	@Test
	void filtrarVendaMaisCaraTest() {
		Venda vendaMaisCara = processadorVendas.filtrarVendaMaisCara();
		
		assertNotNull(vendaMaisCara);
		assertEquals("10", vendaMaisCara.getId());
		assertEquals(1199d, vendaMaisCara.getValorTotal());
	}

	@Test
	void filtrarPiorVendaTest() {
		Venda piorVenda = processadorVendas.filtrarPiorVenda();

		assertNotNull(piorVenda);
		assertEquals("08", piorVenda.getId());
	}

	@Test
	void filtrarPiorVendedorTest() {
		Vendedor piorVendedor = processadorVendas.filtrarPiorVendedor();

		assertNotNull(piorVendedor);
		assertEquals("3245678865434", piorVendedor.getCpf());
	}

	@Test
	void getIdVendaMaisCaraTest() {
		String idVendaMaisCara = processadorVendas.getIdVendaMaisCara();

		assertNotNull(idVendaMaisCara);
		assertEquals("10", idVendaMaisCara);
	}
	
	@Test
	void toStringTest() {
		String vendaStr = processadorVendas.toString();
		
		assertNotNull(vendaStr);
		assertTrue(vendaStr.contains("ID Venda mais cara: 10"));
	}

}
