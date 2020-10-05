package br.com.luisbsl.agibankanalisedados.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Splitter;

import br.com.luisbsl.agibankanalisedados.contants.ExtratorDadosContants;

/**
 * Principal utilitário para processo de extração de Dados pelos Serviços Extratores
 * Utiliza de expressões regulares e de splits de Strings com delimitadores específicos
 * para Tipo De Dado
 * 
 * @author luislima
 *
 */
public final class ExtratorDadosUtils {

	private ExtratorDadosUtils() {
	}

	/**
	 * Extração de todos os dados da linha do arquivo
	 * 
	 * @param linha do arquivo
	 * @param delimitador utilizado para quebrar a linha em partes
	 * @return retorna a lista de dados para extração
	 */
	public static List<String> extrairDados(String linha, String delimitador) {
		return Splitter.on(delimitador).trimResults().omitEmptyStrings().splitToList(linha);
	}
	
	/**
	 * Verifica a integridade do padrão da linha do arquivo
	 * Utilizando de regex para valição
	 * 
	 * @param padrao específico da linha
	 * @param linha do arquivo
	 * @return retorna True caso o padrão da linha esteja correto e False caso não esteja
	 */
	public static Boolean validarPadraoLinha(String padrao, String linha) {
		Pattern pattern = Pattern.compile(padrao);
	    Matcher matcher = pattern.matcher(linha);
	    return matcher.find();
	}
	
	/**
	 * Extração do Tipo de Dado da linha do arquivo (001, 002, 003)
	 * 
	 * @param linha do arquivo para extração dos dados dos itens de Venda
	 * @return retorna tipo de dado da linha 
	 */
	public static String extrairTipoDado(String linha) {
		Pattern pattern = Pattern.compile(ExtratorDadosContants.PADRAO_REGEX_TIPO_DADO);
	    Matcher matcher = pattern.matcher(linha);
	    matcher.find();
	    return matcher.group();	    
	}
	
	/**
	 * Extração de todos os dados dos Itens da linha do arquivo
	 * 
	 * @param linha do arquivo para extração dos dados dos itens de Venda
	 * @return retornar lista contendo os dados dos Itens
	 */
	public static List<String> extrairDadosItens(String linha) {
		List<String> dadosItem = new ArrayList<>();
		Pattern pattern = Pattern.compile(ExtratorDadosContants.PADRAO_REGEX_DADOS_ITEM);
	    Matcher matcher = pattern.matcher(linha);
	    while(matcher.find()) {
	    	dadosItem.add(matcher.group());
	    }
	    return dadosItem;
	}

}
