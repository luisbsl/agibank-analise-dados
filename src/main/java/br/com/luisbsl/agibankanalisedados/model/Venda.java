package br.com.luisbsl.agibankanalisedados.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Classe que representa uma Venda extraída do arquivo
 * 
 * @author luislima
 *
 */
public class Venda implements Dado, Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "ID da Venda não pode ser nula")
	private String id;
	
	@NotBlank(message = "Nome do Vendedor da Venda não pode ser nulo ou vazio")
	private String vendedorNome;
	
	@NotEmpty(message = "Itens da Venda não pode ser nulo ou vazio")
	private List<Item> itens;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVendedorNome() {
		return vendedorNome;
	}

	public void setVendedorNome(String vendedorNome) {
		this.vendedorNome = vendedorNome;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	/**
	 * 
	 * @return retorna o valor total da venda (soma do valor total de todos os itens)
	 */
	public Double getValorTotal() {
		return itens.stream().mapToDouble(Item::getValorTotal).sum();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", vendedorNome=" + vendedorNome + ", itens=" + itens + "]";
	}
	
	public static void main(String[] args) {
		Item i1 = new Item();
		Item i2 = new Item();
		System.out.println(i1.equals(i2));
	}

}
