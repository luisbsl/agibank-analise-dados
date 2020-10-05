package br.com.luisbsl.agibankanalisedados.contants;

import br.com.luisbsl.agibankanalisedados.utils.ExtratorDadosUtils;

/**
 * Constantes utilizadas no utilitário 
 * 
 * @see ExtratorDadosUtils
 * @author luislima
 *
 */
public final class ExtratorDadosContants {

	public static final String DELIMITADOR_DADOS_PADRAO = "ç";
	public static final String DELIMITADOR_DADOS_ITEM = "-";
	
	public static final String PADRAO_REGEX_TIPO_DADO = "(^0{2}[1-3])";
	public static final String PADRAO_REGEX_DADOS_ITEM = "(\\d+)-(\\d+)-(\\d+)((\\.)(\\d+))?";


	private ExtratorDadosContants() {
	}

}
