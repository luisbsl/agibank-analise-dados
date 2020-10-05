package br.com.luisbsl.agibankanalisedados.service;

import java.util.List;

import br.com.luisbsl.agibankanalisedados.model.Dado;
import br.com.luisbsl.agibankanalisedados.model.Linha;

/**
 * Contrato utilizado por todos os Serviços Extratores
 * 
 * @author luislima
 *
 */
public interface IExtratorDados {
	
	public Boolean validarPadraoLinha(String linha);
	public Dado converter(Linha linhaArquivo, List<String> dados);
	public Dado extrair(Linha linhaArquivo);

}
