package br.com.luisbsl.agibankanalisedados.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.luisbsl.agibankanalisedados.enums.ExtratorDadosEnum;
import br.com.luisbsl.agibankanalisedados.exception.ConversaoDadoException;
import br.com.luisbsl.agibankanalisedados.model.Dado;
import br.com.luisbsl.agibankanalisedados.model.Linha;
import br.com.luisbsl.agibankanalisedados.model.builder.VendedorBuilder;

public class ExtratorDadosVendedor extends ExtratorDados {
	
	private static final Logger LOGGER = Logger.getLogger(ExtratorDadosVendedor.class.getName());
	
	public ExtratorDadosVendedor(ExtratorDadosEnum extratorTipoDadoEnum) {
		super(extratorTipoDadoEnum);
	}

	@Override
	public Dado converter(Linha linhaArquivo, List<String> dadosLinha) {
		LOGGER.log(Level.INFO, () -> "Convertendo dados para Tipo "+extratorTipoDadoEnum);
		try {
			return new VendedorBuilder()
					.comCpf(dadosLinha.get(1))
					.comNome(dadosLinha.get(2))
					.comSalario(Double.valueOf(dadosLinha.get(3)))
					.build();
		} catch (NumberFormatException e) {
			throw new ConversaoDadoException(linhaArquivo.toString()+"\r\nNão foi possível converter para o Tipo de Dados - "+extratorTipoDadoEnum); 
		}
		
	}

}
