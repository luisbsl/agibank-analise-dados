package br.com.luisbsl.agibankanalisedados.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.io.CharSink;
import com.google.common.io.Files;

import br.com.luisbsl.agibankanalisedados.utils.SistemaOperacionalUtils;

/**
 * Serviço que consome informações processadas e grava no arquivo de saída Ex:
 * nome-arquivo-entrada-1579128937751.txt
 * 
 * @see ProcessadorVendas#toString()
 * @author luislima
 *
 */
public final class EscritorSaidaInformacaoVendas {

	private static final Logger LOGGER = Logger.getLogger(EscritorSaidaInformacaoVendas.class.getName());

	private static final String DIRETORIO_SAIDA_PADRAO = SistemaOperacionalUtils.getDiretorioArvore(Arrays.asList("data", "out"));

	private EscritorSaidaInformacaoVendas() {
	}

	/**
	 * Escreve no arquivo de saída informações processadas de Venda
	 * 
	 * @see ProcessadorVendas
	 * 
	 * @param nomeArquivoEntrada nome do arquivo utilizado para extração dos dados de Venda
	 * @param processadorVendas servico de processamento de informações de saída
	 * @throws IOException exceção levantada quando ocorrer problemas na escrita do arquivo
	 */
	public static void escrever(String nomeArquivoEntrada, ProcessadorVendas processadorVendas) throws IOException {
		String nomeArquivoSaida = getNomeArquivoSaida(nomeArquivoEntrada, false);
		LOGGER.log(Level.INFO, () -> "Escrevendo dados processados da Venda. Arquivo - " + nomeArquivoSaida);
		escreverNoArquivo(nomeArquivoSaida, processadorVendas.toString());
	}
	
	/**
	 * Escreve no arquivo de saída informações de exceções que ocorreram 
	 * durante o processo de extração
	 * 
	 * @param nomeArquivoEntrada nome do arquivo utilizado para extração dos dados de Venda
	 * @param erro mensagem de erro da Exceção
	 * @throws IOException exceção levantada quando ocorrer problemas na escrita do arquivo
	 */
	public static void escreverErro(String nomeArquivoEntrada, String erro) throws IOException {
		String nomeArquivoSaida = getNomeArquivoSaida(nomeArquivoEntrada, true);
		LOGGER.log(Level.INFO, () -> "Escrevendo exceções ocorridas no processo de extração dos Dados da Venda. Arquivo - " + nomeArquivoSaida);
		escreverNoArquivo(nomeArquivoSaida, erro);
	}

	/**
	 * Escreve informações processadas ou exceções que ocorream durante a extração de Dados da Venda
	 * 
	 * @param nomeArquivoSaida nome do arquivo utilizado como relatório de Vendas ou exceções que ocorreram durante o processo de extração
	 * @param informacao 
	 * @throws IOException
	 */
	
	/**
	 * 
	 * @param nomeArquivoSaida
	 * @param informacao
	 * @throws IOException
	 */
	private static void escreverNoArquivo(String nomeArquivoSaida, String informacao) throws IOException {
		SistemaOperacionalUtils.verificarDiretorio(DIRETORIO_SAIDA_PADRAO);
		Path pathArquivoSaida = Paths.get(nomeArquivoSaida);
		CharSink sink = Files.asCharSink(pathArquivoSaida.toFile(), StandardCharsets.UTF_8);
		sink.write(informacao);
	}

	/**
	 * Cria o nome do arquivo de saída utilizando como préfixo o nome do arquivo de entrada
	 * 
	 * @param nomeArquivoEntrada nome do arquivo de entrada extraído
	 * @param isErro caso seja um arquivo de erro deverá setar como TRUE, senão FALSE
	 * @return retorna o nome do arquivo de entrada concatenado com milisegundos
	 * caso seja arquivo de erro, concatena também com -erro
	 */
	private static String getNomeArquivoSaida(String nomeArquivoEntrada, Boolean isErro) {
		String nomeArquivoSaida = DIRETORIO_SAIDA_PADRAO
									.concat(Files.getNameWithoutExtension(nomeArquivoEntrada)).replace(" ", "_");
		
		if(Boolean.TRUE.equals(isErro)) {
			nomeArquivoSaida += "_erros";
		}
		
		return nomeArquivoSaida.concat(".done.dat");
	}

}
