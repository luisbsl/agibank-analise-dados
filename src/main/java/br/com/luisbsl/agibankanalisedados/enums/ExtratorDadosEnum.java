package br.com.luisbsl.agibankanalisedados.enums;

import br.com.luisbsl.agibankanalisedados.utils.ExtratorDadosUtils;

/**
 * Enum utilizado pelo utilitário ExtratorDadosUtils
 * Utilizado Para validação do padrão de linha de acordo com determinado Tipo De Dado (Vendedor, Cliente, Venda ou Item)
 * 
 * @see ExtratorDadosUtils#validarPadraoLinha(String, String)
 * @author luislima
 *
 */
public enum ExtratorDadosEnum {

	VENDEDOR("(^\\d{3})(ç)(\\d+)(ç)([A-zÀ-ú] ?)*(\\d+)(\\.?)(\\d+)"),
	CLIENTE("(^\\d{3})(ç)(\\d+)(ç)([A-zÀ-ú] ?)*(ç)([A-z])+"),
	VENDA("(\\d{3})(ç)(\\d{2})(ç)(\\[)(((\\d+-)){2}(\\d+\\.?\\d+,?)){1,}(\\])(ç)(([A-zÀ-ú] ?))*"),
	ITEM("(\\d+)-(\\d+)-(\\d+)((\\.)(\\d+))?");

	private String regexPadrao;

	private ExtratorDadosEnum(String regexPadrao) {
		this.regexPadrao = regexPadrao;
	}

	/**
	 * R
	 * @return retorna o padrão de expressão regular
	 */
	public String getRegexPadrao() {
		return regexPadrao;
	}

}
