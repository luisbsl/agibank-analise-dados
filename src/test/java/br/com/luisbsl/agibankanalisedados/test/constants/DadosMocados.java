package br.com.luisbsl.agibankanalisedados.test.constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.luisbsl.agibankanalisedados.model.Dado;
import br.com.luisbsl.agibankanalisedados.model.Item;
import br.com.luisbsl.agibankanalisedados.model.builder.ClienteBuilder;
import br.com.luisbsl.agibankanalisedados.model.builder.ItemBuilder;
import br.com.luisbsl.agibankanalisedados.model.builder.VendaBuilder;
import br.com.luisbsl.agibankanalisedados.model.builder.VendedorBuilder;

public final class DadosMocados {
	
	private DadosMocados() {
	}
	
	public static final String REGEX_PADRAO_VENDEDOR = "(^\\d{3})(ç)(\\d+)(ç)([A-zÀ-ú] ?)*(\\d+)(\\.?)(\\d+)";
	public static final String REGEX_PADRAO_CLIENTE = "(^\\d{3})(ç)(\\d+)(ç)([A-zÀ-ú] ?)*(ç)([A-z])+";
	public static final String REGEX_PADRAO_VENDA = "(\\d{3})(ç)(\\d{2})(ç)(\\[)(((\\d+-)){2}(\\d+\\.?\\d+,?)){1,}(\\])(ç)(([A-zÀ-ú] ?))*";
	public static final String REGEX_PADRAO_ITEM = "(\\d+)-(\\d+)-(\\d+)((\\.)(\\d+))?";
	
	public static final String LINHA_TIPO_VENDEDOR = "001ç3245678865434çPauloç40000.99";
	public static final String LINHA_TIPO_CLIENTE = "002ç2345675433444345çEduardo PereiraçRural";
	public static final String LINHA_TIPO_VENDA = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
	
	public static final String ID_TIPO_DADO_VENDEDOR = "001";
	public static final String ID_TIPO_DADO_CLIENTE = "002";
	public static final String ID_TIPO_DADO_VENDA = "003";
	
	public static final List<String> DADOS_LINHA_VENDEDOR = Arrays.asList("001", "3245678865434", "Paulo", "40000.99");
	public static final List<String> DADOS_LINHA_CLIENTE = Arrays.asList("002", "2345675434544345", "Jose da Silva", "Rural");
	public static final List<String> DADOS_LINHA_VENDA = Arrays.asList("003", "10", "[1-10-100,2-30-2.50,3-40-3.10]", "Pedro");
	
	public static final List<Dado> VENDEDORES =
			Arrays.asList(
					new VendedorBuilder()
							.comCpf("1234567891234")
							.comNome("Pedro")
							.comSalario(50000.0)
							.build(),
					new VendedorBuilder()
							.comCpf("3245678865434")
							.comNome("Paulo")
							.comSalario(40000.99)
							.build()
			);
	
	public static final List<Dado> CLIENTES = 
			Arrays.asList(
					new ClienteBuilder()
							.comCnpj("2345675434544345")
							.comNome("Jose da Silva")
							.comAreaNegocio("Rural")
							.build(),
					new ClienteBuilder()
							.comCnpj("2345675433444345")
							.comNome("Eduardo Pereira")
							.comAreaNegocio("Rural")
							.build()
			);
	
	public static final List<Item> ITENS = 
			Arrays.asList(
					new ItemBuilder()
							.comId(1)
							.comQuantidade(10)
							.comPreco(100.0)
							.build(),
					new ItemBuilder()
							.comId(2)
							.comQuantidade(30)
							.comPreco(2.5)
							.build(),
					new ItemBuilder()
							.comId(3)
							.comQuantidade(40)
							.comPreco(3.1)
							.build(),
							
					new ItemBuilder()
							.comId(1)
							.comQuantidade(34)
							.comPreco(10.0)
							.build(),
					new ItemBuilder()
							.comId(2)
							.comQuantidade(33)
							.comPreco(1.5)
							.build(),
					new ItemBuilder()
							.comId(3)
							.comQuantidade(40)
							.comPreco(0.1)
							.build()
			);
	
	public static final List<Dado> VENDAS =
			Arrays.asList(
					new VendaBuilder()
							.comId("10")
							.comItens(Arrays.asList(ITENS.get(0), ITENS.get(1), ITENS.get(2)))
							.comVendedorNome("Pedro")
							.build(),
					new VendaBuilder()
							.comId("08")
							.comItens(Arrays.asList(ITENS.get(3), ITENS.get(4), ITENS.get(5)))
							.comVendedorNome("Paulo")
							.build()
			);
	
	public static final List<Dado> DADOS_VENDA = Stream
					.concat(Stream.concat(VENDEDORES.stream(), CLIENTES.stream()), VENDAS.stream())
					.collect(Collectors.toList());

}
