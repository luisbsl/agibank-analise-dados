package br.com.luisbsl.agibankanalisedados.model.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.luisbsl.agibankanalisedados.exception.DadoInvalidoException;
import br.com.luisbsl.agibankanalisedados.model.Cliente;
import br.com.luisbsl.agibankanalisedados.model.builder.ClienteBuilder;
import br.com.luisbsl.agibankanalisedados.test.constants.DadosMocados;

/**
 * Casos de testes para classe ClienteBuilder
 * 
 * @see {@link ClienteBuilder}
 * @author luislima
 *
 */
class ClienteBuilderTest {
	
	@Test
	void buildTest() {
		Cliente cliente = new ClienteBuilder()
									.comCnpj("2345675434544345")
									.comNome("Jose da Silva")
									.comAreaNegocio("Rural")
									.build();
		
		Cliente clientTeste = (Cliente) DadosMocados.CLIENTES.stream().filter(c -> c.equals(cliente)).findFirst().get();
		
		assertNotNull(cliente);
		assertNotNull(clientTeste);
		assertEquals(cliente, clientTeste);
	}
	
	@Test
	void buildTest_deveLevantarExcecao_DadoInvalidoException_CNPJ() {
		Exception exception = assertThrows(
				DadoInvalidoException.class, 
				() -> new ClienteBuilder()
							.comCnpj("")
							.comNome("Jose da Silva")
							.comAreaNegocio("Rural")
							.build());
		
		assertTrue(exception.getMessage().contains("CNPJ"));
	}
	
	@Test
	void buildTest_deveLevantarExcecao_DadoInvalidoException_Nome() {
		Exception exception = assertThrows(
				DadoInvalidoException.class, 
				() -> new ClienteBuilder()
							.comCnpj("2345675434544345")
							.comNome("")
							.comAreaNegocio("Rural")
							.build());
		
		assertTrue(exception.getMessage().contains("Nome"));
	}
	
	@Test
	void buildTest_deveLevantarExcecao_DadoInvalidoException_AreaNegocio() {
		Exception exception = assertThrows(
				DadoInvalidoException.class, 
				() -> new ClienteBuilder()
							.comCnpj("2345675434544345")
							.comNome("Jose da Silva")
							.comAreaNegocio("")
							.build());
		
		assertTrue(exception.getMessage().contains("Area de Negócio"));
	}
	
}
