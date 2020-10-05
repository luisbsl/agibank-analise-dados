package br.com.luisbsl.agibankanalisedados.enums;

import br.com.luisbsl.agibankanalisedados.service.ExtratorDadosCliente;
import br.com.luisbsl.agibankanalisedados.service.ExtratorDadosVenda;
import br.com.luisbsl.agibankanalisedados.service.ExtratorDadosVendedor;
import br.com.luisbsl.agibankanalisedados.service.IExtratorDados;

/**
 * Enum principal de Tipo De Dado (Vendedor, Cliente ou Venda)
 * Retorna o Serviço Extrator específico para cada Tipo De Dado
 * Centralizando a construção dos Serviços Extratores 
 * Possibilitando escalabilidade na aplicação, acoplamento e redução de condicinais no código
 * 
 * @author luislima
 *
 */
public enum TipoDadoEnum {
	
	VENDEDOR("001") {
		@Override
		public IExtratorDados obterExtratorDado() {
			return new ExtratorDadosVendedor(ExtratorDadosEnum.VENDEDOR);
		}
	},
	CLIENTE("002") {
		@Override
		public IExtratorDados obterExtratorDado() {
			return new ExtratorDadosCliente(ExtratorDadosEnum.CLIENTE);
		}
	},
	VENDA("003") {
		@Override
		public IExtratorDados obterExtratorDado() {
			return new ExtratorDadosVenda(ExtratorDadosEnum.VENDA);
		}
	};
	
	public abstract IExtratorDados obterExtratorDado();
	
	private String identificador;
	
	TipoDadoEnum(String identificador) {
		this.identificador = identificador;
	}

	/**
	 * 
	 * @return retorna Identificador do Tipo De Dado (001, 002, 003)
	 */
	public String getIdentificador() {
		return identificador;
	}

}
