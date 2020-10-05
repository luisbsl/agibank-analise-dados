package br.com.luisbsl.agibankanalisedados.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.luisbsl.agibankanalisedados.contants.ExtratorDadosContants;
import br.com.luisbsl.agibankanalisedados.enums.ExtratorDadosEnum;
import br.com.luisbsl.agibankanalisedados.exception.LinhaArquivoInvalidaException;
import br.com.luisbsl.agibankanalisedados.model.Dado;
import br.com.luisbsl.agibankanalisedados.model.Linha;
import br.com.luisbsl.agibankanalisedados.utils.ExtratorDadosUtils;

/**
 * Serviço principal para processo de extração dos Dados da Linha do arquivo
 * O Tipo de dado específico para extração é passado no construtor  
 * 
 * @author luislima
 *
 */
public abstract class ExtratorDados implements IExtratorDados {
	
	private static final Logger LOGGER = Logger.getLogger(ExtratorDados.class.getName());
	protected ExtratorDadosEnum extratorTipoDadoEnum;
	
	public ExtratorDados(ExtratorDadosEnum extratorTipoDadoEnum) {
		this.extratorTipoDadoEnum = extratorTipoDadoEnum;
	}
	
	@Override
	public Boolean validarPadraoLinha(String linhaConteudo) {
		return ExtratorDadosUtils.validarPadraoLinha(extratorTipoDadoEnum.getRegexPadrao(), linhaConteudo);
	}
	
	@Override
	public Dado extrair(Linha linhaArquivo) {
		LOGGER.log(Level.INFO, () -> "Validando extraçao de dados Tipo "+extratorTipoDadoEnum);
		final String linhaConteudo = linhaArquivo.getConteudo();
		if(Boolean.FALSE.equals(validarPadraoLinha(linhaConteudo))) {
			throw new LinhaArquivoInvalidaException(linhaArquivo.toString()+"\r\nFormato inválido para o Tipo de Dados - "+extratorTipoDadoEnum);
		}
		
		LOGGER.log(Level.INFO, () -> "Extraindo dados Tipo "+extratorTipoDadoEnum);
		List<String> dados = ExtratorDadosUtils.extrairDados(linhaArquivo.getConteudo(), ExtratorDadosContants.DELIMITADOR_DADOS_PADRAO);
		return converter(linhaArquivo, dados);
	}

}
