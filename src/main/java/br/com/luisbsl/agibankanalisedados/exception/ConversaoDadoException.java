package br.com.luisbsl.agibankanalisedados.exception;

/**
 * Exceção que ocorre quando não é possível converter os dados da linha
 * num Objeto do tipo Dado (Vendedor, Cliente, Venda ou Item)
 * 
 * @author luislima
 *
 */
public class ConversaoDadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ConversaoDadoException(String mensagemErro) {
		super(mensagemErro);
	}

}
