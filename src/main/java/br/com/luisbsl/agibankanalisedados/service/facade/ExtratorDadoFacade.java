package br.com.luisbsl.agibankanalisedados.service.facade;

import java.util.Arrays;
import java.util.Optional;

import br.com.luisbsl.agibankanalisedados.contants.ExtratorDadosContants;
import br.com.luisbsl.agibankanalisedados.enums.TipoDadoEnum;
import br.com.luisbsl.agibankanalisedados.exception.TipoDadoNaoEncontradoException;
import br.com.luisbsl.agibankanalisedados.model.Dado;
import br.com.luisbsl.agibankanalisedados.model.Linha;
import br.com.luisbsl.agibankanalisedados.service.IExtratorDados;
import br.com.luisbsl.agibankanalisedados.utils.ExtratorDadosUtils;

/**
 * 
 * Serviço que implementa o Padrão Facade para abstrair regras de negócios
 * Direciona o processso de extração para o Tipo De Dado especifico (Vendedor, Cliente ou Venda)
 * 
 * @author luislima
 *
 */
public class ExtratorDadoFacade {
	
	private Linha linhaArquivo;
	
	/**
	 * 
	 * @param linhaArquivo dados da linha do arquivo
	 */
	public ExtratorDadoFacade(Linha linhaArquivo) {
		this.linhaArquivo = linhaArquivo;
	}
	
	/**
	 * Abstrai a chamada do metodo extrair correspondete ao Extrator correspondente
	 * ao Tipo de Dado da linha	
	 * 
	 * @return retorna Objeto correspondente ao Tipo De Dado da linha passada no construtor
	 */
	public Dado extrair() {
		return obterExtratorDadoPeloTipo().extrair(linhaArquivo);
	}
	
	/**
	 * 
	 * Utiliza do TipoDadoEnum para fabricar o Extrator responsável pelo 
	 * Tipo de Dado correspondente a determinada linha do arquivo 
	 * 
	 * @see {@link TipoDadoEnum}
	 * 
	 * @exception levanta exceção {@link TipoDadoNaoEncontradoException} caso o Tipo de Dado da linha não seja válido
	 * @return retorna o Serviço de extração correspondente ao Tipo de Dado (001, 002 ou 003)
	 */
	private IExtratorDados obterExtratorDadoPeloTipo() {
		
		Boolean tipoEncontrado = ExtratorDadosUtils
									.validarPadraoLinha(ExtratorDadosContants.PADRAO_REGEX_TIPO_DADO, linhaArquivo.getConteudo());
		
		if(Boolean.TRUE.equals(tipoEncontrado)) {
			Optional<TipoDadoEnum> tipoDadoEnum = Arrays
					.stream(TipoDadoEnum.values())
					.filter(e -> e.getIdentificador().equals(ExtratorDadosUtils.extrairTipoDado(linhaArquivo.getConteudo())))
					.findFirst();

			if(tipoDadoEnum.isPresent()) {
				return tipoDadoEnum.get().obterExtratorDado();
			}
		}
		
		throw new TipoDadoNaoEncontradoException(linhaArquivo.toString()+"\r\n");
		
	}

}
