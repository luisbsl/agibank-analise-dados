package br.com.luisbsl.agibankanalisedados.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.luisbsl.agibankanalisedados.enums.ExtratorDadosEnum;
import br.com.luisbsl.agibankanalisedados.exception.ConversaoDadoException;
import br.com.luisbsl.agibankanalisedados.model.Dado;
import br.com.luisbsl.agibankanalisedados.model.Linha;
import br.com.luisbsl.agibankanalisedados.model.builder.VendaBuilder;

public class ExtratorDadosVenda extends ExtratorDados {
	
	private static final Logger LOGGER = Logger.getLogger(ExtratorDadosVenda.class.getName());
	
	public ExtratorDadosVenda(ExtratorDadosEnum extratorTipoDadoEnum) {
		super(extratorTipoDadoEnum);
	}

	@Override
	public Dado converter(Linha linhaArquivo, List<String> dados) {
		LOGGER.log(Level.INFO, () -> "Convertendo dados para Tipo "+extratorTipoDadoEnum);
		try {
			return new VendaBuilder()
							.comId(dados.get(1))
							.comVendedorNome(dados.get(3))
							.comItens(new ExtratorDadosItem(ExtratorDadosEnum.ITEM).extrairItens(linhaArquivo))
							.build();
		} catch (Exception e) {
			throw new ConversaoDadoException(linhaArquivo.toString()+"\r\nNão foi possível converter para o Tipo de Dados - "+extratorTipoDadoEnum); 
		}
	}

}
