package br.com.luisbsl.agibankanalisedados.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import br.com.luisbsl.agibankanalisedados.utils.SistemaOperacionalUtils;

/**
 * Casos de testes para classe SistemaOperacionalUtils
 * 
 * @see {@link SistemaOperacionalUtils}
 * @author luislima
 *
 */
class SistemaOperacionalUtilsTest {
	
	@Test
	void getDiretorioArvoreTest() {
		String diretorioArvore = SistemaOperacionalUtils.getDiretorioArvore(Arrays.asList("data", "in"));
		
		assertNotNull(diretorioArvore);
		assertEquals(System.getProperty("user.home")+"/data/in/", diretorioArvore);
	}
	
	@Test
	void getDiretorioArvoreSeparadorTest_Unix() {
		String separador = SistemaOperacionalUtils.getDiretorioArvoreDelimitador();
		
		assertNotNull(separador);
		assertEquals("/", separador);
	}
	
	@Test
	void verificarDiretorio() throws IOException {
		SistemaOperacionalUtils.verificarDiretorio(SistemaOperacionalUtils.getDiretorioArvore(Arrays.asList("data", "in")));
		SistemaOperacionalUtils.verificarDiretorio(SistemaOperacionalUtils.getDiretorioArvore(Arrays.asList("data", "out")));
		
		assertTrue(Boolean.TRUE.equals(new File(SistemaOperacionalUtils.getDiretorioArvore(Arrays.asList("data", "in"))).exists()));
		assertTrue(Boolean.TRUE.equals(new File(SistemaOperacionalUtils.getDiretorioArvore(Arrays.asList("data", "out"))).exists()));
	}

}
