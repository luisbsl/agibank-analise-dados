package br.com.luisbsl.agibankanalisedados.exception;

import br.com.luisbsl.agibankanalisedados.enums.TipoDadoEnum;
import br.com.luisbsl.agibankanalisedados.utils.ExtratorDadosUtils;

/**
 * Exceção que ocorre quando Tipo De Dado da linha não foi encontrado
 * 
 * @see TipoDadoEnum
 * @see ExtratorDadosUtils#extrairTipoDado(String)
 * @author luislima
 *
 */
public class TipoDadoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String MSG_ERRO = "Tipo de dado não encontrado. (tipos esperados: 001, 002, 003)";
	
	public TipoDadoNaoEncontradoException(String informacaoLinha) {
		super(informacaoLinha+MSG_ERRO);
	}

}
