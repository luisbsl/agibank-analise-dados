package br.com.luisbsl.agibankanalisedados.exception;

/**
 * Exceção que ocorre quando o Dado não contempla todos os campos necessário
 * ou quando está nulo
 * 
 * @author luislima
 *
 */
public class DadoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DadoInvalidoException(String mensagemErro) {
		super(mensagemErro);
	}

}
