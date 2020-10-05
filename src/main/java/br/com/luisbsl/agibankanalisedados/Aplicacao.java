package br.com.luisbsl.agibankanalisedados;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.luisbsl.agibankanalisedados.service.LeitorEntradaInformacaoVendas;

/**
 * Classe principal do Sistema Análise de Vendas
 * O Sistema será iniciado e estará escutando o diretório HOMEPATH/data/in
 * Logo que novos arquivos forem inseridos no diretório será lido cada arquivo e extraído as informações de vendas
 * Após extração será gerado um arquivo de reltório no diretório HOMEPATH/data/out
 * 
 * @see LeitorEntradaInformacaoVendas#iniciar()
 * @author luislima
 *
 */
public class Aplicacao {
	private static final Logger LOGGER = Logger.getLogger(Aplicacao.class.getName());

	public static void main(String[] args) {
		try {
			LeitorEntradaInformacaoVendas.iniciar();
		} catch (IOException | InterruptedException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			LOGGER.log(Level.WARNING, "Processo de extração interrompido!", e);
			Thread.currentThread().interrupt();
		}
	}
}