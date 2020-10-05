package br.com.luisbsl.agibankanalisedados.service;

import br.com.luisbsl.agibankanalisedados.model.Venda;
import br.com.luisbsl.agibankanalisedados.model.Vendedor;

/**
 * 
 * @author luislima
 *
 */
public interface IProcessarVendas {
	
	/**
	 * Calcula quantidade de clientes numa Lista de Dados de Venda
	 * 
	 * @return Long retorna Total de clientes do arquivo extraído
	 */
	public Long calcularQuantidadeClientes();
	
	/**
	 * Calcula quantidade de vendedores numa Lista de Dados de Venda
	 * 
	 * @return Long retorna Total de vendedores do arquivo extraído
	 */
	public Long calcularQuantidadeVendedores();
	
	/**
	 * Filtra Venda com maior valor
	 * 
	 * @return Venda retorna Venda Mais Cara do arquivo extraído
	 */
	public Venda filtrarVendaMaisCara();
	
	/**
	 * Filtra Venda com menor valor
	 * 
	 * @return Venda retorna Pior Venda arquivo extraído
	 */
	public Venda filtrarPiorVenda();
	
	/**
	 * Retorna ID da Venda com maior valor
	 * 
	 * @return Long retorna ID Venda mais cara do arquivo extraído
	 */
	public String getIdVendaMaisCara();
	
	/**
	 * Retorna vendedor que teve a pior venda
	 * 
	 * @return Vendedor retorna Pior Vendedor arquivo extraído
	 */
	public Vendedor filtrarPiorVendedor();

}
