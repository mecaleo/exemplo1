package br.edu.unicatolica.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="produto_venda")
public class ProdutoVenda implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long codigo;
	private Produto produto;
	private Venda venda;
	private Double qtde;
	private Double vlrUnitario;
	private Double vlrTotal;

	@Id
	@GeneratedValue
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@ManyToOne
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@ManyToOne
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Double getQtde() {
		return qtde;
	}

	public void setQtde(Double qtde) {
		this.qtde = qtde;
	}

	@Column(name="vlr_unit")
	public Double getVlrUnitario() {
		return vlrUnitario;
	}

	public void setVlrUnitario(Double vlrUnitario) {
		this.vlrUnitario = vlrUnitario;
	}

	@Column(name="vlr_total")
	public Double getVlrTotal() {
		return vlrTotal;
	}

	public void setVlrTotal(Double vlrTotal) {
		this.vlrTotal = vlrTotal;
	}

}
