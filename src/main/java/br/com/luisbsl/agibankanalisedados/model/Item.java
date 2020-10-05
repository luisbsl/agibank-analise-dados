package br.com.luisbsl.agibankanalisedados.model;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * Classe que representa um Item extraído do arquivo
 * 
 * @author luislima
 *
 */
public class Item implements Dado, Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "ID do Item não pode ser nulo")
	private Integer id;

	@NotNull(message = "Quantidade do Item não pode ser nula")
	private Integer quantidade;

	@NotNull(message = "Preço do Item não pode ser nulo")
	private Double preco;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	/**
	 * 
	 * @return retorna o valor total do item (preco * quantidade)
	 */
	public Double getValorTotal() {
		return preco * quantidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		} 
		Item that = (Item) obj;
		if(that.id == null || id == null) {
			return false;
		}
		return Objects.equals(id, that.id);
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", quantidade=" + quantidade + ", preco=" + preco + "]";
	}

}
