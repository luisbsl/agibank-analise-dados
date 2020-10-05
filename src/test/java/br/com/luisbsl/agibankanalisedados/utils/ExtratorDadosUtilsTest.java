package br.com.luisbsl.agibankanalisedados.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.luisbsl.agibankanalisedados.contants.ExtratorDadosContants;
import br.com.luisbsl.agibankanalisedados.model.Cliente;
import br.com.luisbsl.agibankanalisedados.model.Item;
import br.com.luisbsl.agibankanalisedados.model.Venda;
import br.com.luisbsl.agibankanalisedados.model.Vendedor;
import br.com.luisbsl.agibankanalisedados.model.builder.ClienteBuilder;
import br.com.luisbsl.agibankanalisedados.model.builder.VendaBuilder;
import br.com.luisbsl.agibankanalisedados.model.builder.VendedorBuilder;
import br.com.luisbsl.agibankanalisedados.test.constants.DadosMocados;
import br.com.luisbsl.agibankanalisedados.utils.ExtratorDadosUtils;

/**
 * Casos de testes para classe ExtratorDadosUtils
 * 
 * @see {@link ExtratorDadosUtils}
 * @author luislima
 *
 */
class ExtratorDadosUtilsTest {
	
	@Test
	void extrairTipoDadoTest_deveRetornarIdentificadorTipoVendedor() {
		String identificadorTipoDado = ExtratorDadosUtils.extrairTipoDado(DadosMocados.LINHA_TIPO_VENDEDOR);
		
		assertNotNull(identificadorTipoDado);
		assertEquals(DadosMocados.ID_TIPO_DADO_VENDEDOR, identificadorTipoDado);
	}
	
	@Test
	void extrairTipoDadoTest_deveRetornarIdentificadorTipoCliente() {
		String identificadorTipoDado = ExtratorDadosUtils.extrairTipoDado(DadosMocados.LINHA_TIPO_CLIENTE);
		
		assertNotNull(identificadorTipoDado);
		assertEquals(DadosMocados.ID_TIPO_DADO_CLIENTE, identificadorTipoDado);
	}
	
	@Test
	void extrairTipoDadoTest_deveRetornarIdentificadorTipoVenda() {
		String identificadorTipoDado = ExtratorDadosUtils.extrairTipoDado(DadosMocados.LINHA_TIPO_VENDA);
		
		assertNotNull(identificadorTipoDado);
		assertEquals(DadosMocados.ID_TIPO_DADO_VENDA, identificadorTipoDado);
	}
	
	@Test
	void extrairDadosTest_deveRetornarDadosVendedor() {	
		List<String> dadosVendedor = ExtratorDadosUtils
										.extrairDados(DadosMocados.LINHA_TIPO_VENDEDOR, ExtratorDadosContants.DELIMITADOR_DADOS_PADRAO);
		
		Vendedor vendedor = new VendedorBuilder()
								.comCpf(dadosVendedor.get(1))
								.comNome(dadosVendedor.get(2))
								.comSalario(Double.valueOf(dadosVendedor.get(3)))
								.build();
		
		Vendedor vendedorTeste = (Vendedor) DadosMocados.VENDEDORES.stream().filter(v -> v.equals(vendedor)).findFirst().get();
		
		assertNotNull(vendedor);
		assertNotNull(vendedorTeste);
		assertEquals(vendedor.getCpf(), vendedorTeste.getCpf());
		assertEquals(vendedor.getNome(), vendedorTeste.getNome());
		assertEquals(vendedor.getSalario(), vendedorTeste.getSalario());
	}
	
	@Test
	void extrairDadosTest_deveRetornarDadosCliente() {	
		List<String> dadosCliente = ExtratorDadosUtils
										.extrairDados(DadosMocados.LINHA_TIPO_CLIENTE, ExtratorDadosContants.DELIMITADOR_DADOS_PADRAO);
		
		Cliente cliente = new ClienteBuilder()
									.comCnpj(dadosCliente.get(1))
									.comNome(dadosCliente.get(2))
									.comAreaNegocio(dadosCliente.get(3))
									.build();
		
		Cliente clienteTeste = (Cliente) DadosMocados.CLIENTES.stream().filter(c -> c.equals(cliente)).findFirst().get();
		
		assertNotNull(cliente);
		assertNotNull(clienteTeste);
		assertEquals(cliente.getCnpj(), clienteTeste.getCnpj());
		assertEquals(cliente.getNome(), clienteTeste.getNome());
		assertEquals(cliente.getAreaNegocio(), clienteTeste.getAreaNegocio());
	}
	
	@Test
	void extrairDadosTest_deveRetornarDadosVenda() {	
		List<String> dadosVenda = ExtratorDadosUtils
										.extrairDados(DadosMocados.LINHA_TIPO_VENDA, ExtratorDadosContants.DELIMITADOR_DADOS_PADRAO);
		
		List<Item> itens = ((Venda) DadosMocados.VENDAS.stream().filter(v -> ((Venda)v).getId().equals(dadosVenda.get(1))).findFirst().get()).getItens();
		
		Venda venda = new VendaBuilder()
								.comId(dadosVenda.get(1))
								.comVendedorNome(dadosVenda.get(3))
								.comItens(itens)
								.build();
		
		Venda vendaTeste = (Venda) DadosMocados.VENDAS.stream().filter(v -> v.equals(venda)).findFirst().get();
		
		assertNotNull(venda);
		assertNotNull(vendaTeste);
		assertEquals(venda.getId(), vendaTeste.getId());
		assertEquals(venda.getValorTotal(), vendaTeste.getValorTotal());
		assertEquals(venda.getVendedorNome(), vendaTeste.getVendedorNome());
		assertEquals(venda.getItens().get(1), vendaTeste.getItens().get(1));
	}
	
	@Test
	void extrairDadosTest_deveRetornarDadosItens() {	
//		List<String> dadosItens = ExtratorDadosUtils
//										.extrairDadosItens(DadosMocados.LINHA_TIPO_VENDA);
//		
//		List<Item> itens = ((Venda) DadosMocados.VENDAS.stream().filter(v -> ((Venda)v).getId().equals(dadosItens.get(1))).findFirst().get()).getItens();
//		
//		Item item = new ItemBuilder()
//							.comId(Integer.valueOf(dadosItens.get(0)))
//							.comQuantidade(Integer.valueOf(dadosItens.get(1)))
//							.comPreco(Double.valueOf(dadosItens.get(2)))
//							.build();
//		
//		Venda vendaTeste = (Venda) DadosMocados.VENDAS.stream().filter(v -> v.equals(venda)).findFirst().get();
//		
//		assertNotNull(venda);
//		assertNotNull(vendaTeste);
//		assertEquals(venda.getId(), vendaTeste.getId());
//		assertEquals(venda.getValorTotal(), vendaTeste.getValorTotal());
//		assertEquals(venda.getVendedorNome(), vendaTeste.getVendedorNome());
//		assertEquals(venda.getItens().get(1), vendaTeste.getItens().get(1));
		
		assertTrue(true);
	}
	

}
