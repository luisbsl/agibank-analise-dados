package br.com.luisbsl.agibankanalisedados.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.luisbsl.agibankanalisedados.contants.ExtratorDadosContants;
import br.com.luisbsl.agibankanalisedados.enums.ExtratorDadosEnum;
import br.com.luisbsl.agibankanalisedados.exception.ConversaoDadoException;
import br.com.luisbsl.agibankanalisedados.exception.LinhaArquivoInvalidaException;
import br.com.luisbsl.agibankanalisedados.model.Dado;
import br.com.luisbsl.agibankanalisedados.model.Item;
import br.com.luisbsl.agibankanalisedados.model.Linha;
import br.com.luisbsl.agibankanalisedados.model.builder.ItemBuilder;
import br.com.luisbsl.agibankanalisedados.utils.ExtratorDadosUtils;

public class ExtratorDadosItem extends ExtratorDados {
	
	private static final Logger LOGGER = Logger.getLogger(ExtratorDadosItem.class.getName());
	
	public ExtratorDadosItem(ExtratorDadosEnum extratorTipoDadoEnum) {
		super(extratorTipoDadoEnum);
	}

	public List<Item> extrairItens(Linha linhaArquivo) {
		LOGGER.log(Level.INFO, () -> "Validando extraçao de dados Tipo "+extratorTipoDadoEnum);
		final String linhaConteudo = linhaArquivo.getConteudo();
		if(Boolean.FALSE.equals(validarPadraoLinha(linhaConteudo))) {
			throw new LinhaArquivoInvalidaException(linhaArquivo.toString()+"\r\nFormato inválido para o Tipo de Dados - "+extratorTipoDadoEnum);
		}
		
		List<Item> itens = new ArrayList<>();
		List<String> dadosItensLista = ExtratorDadosUtils.extrairDadosItens(linhaArquivo.getConteudo());
		for(String dadosItem: dadosItensLista) {			
			LOGGER.log(Level.INFO, () -> "Extraindo dados Tipo "+extratorTipoDadoEnum);
			List<String> dados = ExtratorDadosUtils.extrairDados(dadosItem, ExtratorDadosContants.DELIMITADOR_DADOS_ITEM);
			
			itens.add((Item) converter(linhaArquivo, dados));
		}
		
		return itens;
	}
	
	@Override
	public Dado converter(Linha linhaArquivo, List<String> dados) {
		LOGGER.log(Level.INFO, () -> "Convertendo dados para Tipo "+extratorTipoDadoEnum);
		try {
			return new ItemBuilder()
					.comId(Integer.valueOf(dados.get(0)))
					.comQuantidade(Integer.valueOf(dados.get(1)))
					.comPreco(Double.valueOf(dados.get(2)))
					.build();
		} catch (NumberFormatException e) {
			throw new ConversaoDadoException(linhaArquivo.toString()+"\r\nNão foi possível converter para o Tipo de Dados - "+extratorTipoDadoEnum); 
		}
		
	}

}
