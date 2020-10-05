package br.com.luisbsl.agibankanalisedados.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.luisbsl.agibankanalisedados.enums.ExtratorDadosEnum;
import br.com.luisbsl.agibankanalisedados.model.Dado;
import br.com.luisbsl.agibankanalisedados.model.Linha;
import br.com.luisbsl.agibankanalisedados.model.builder.ClienteBuilder;

public class ExtratorDadosCliente extends ExtratorDados {
	
	private static final Logger LOGGER = Logger.getLogger(ExtratorDadosCliente.class.getName());

	public ExtratorDadosCliente(ExtratorDadosEnum extratorTipoDadoEnum) {
		super(extratorTipoDadoEnum);
	}

	@Override
	public Dado converter(Linha linhaArquivo, List<String> dados) {
		LOGGER.log(Level.INFO, () -> "Convertendo dados para Tipo "+extratorTipoDadoEnum);
		return new ClienteBuilder()
				.comCnpj(dados.get(1))
				.comNome(dados.get(2))
				.comAreaNegocio(dados.get(3))
				.build();
	}

}
