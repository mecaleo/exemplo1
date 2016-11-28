package br.edu.unicatolica.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="produto_entrada")
public class ProdutoEntrada implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long codigo;
	private Produto produto;
	private Double qtdeEntrada;
	private Double valorUnit;
	private Double valorTotal;

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

	@Column(name="qtde_entrada")
	public Double getQtdeEntrada() {
		return qtdeEntrada;
	}

	public void setQtdeEntrada(Double qtdeEntrada) {
		this.qtdeEntrada = qtdeEntrada;
	}

	@Column(name="valor_unit")
	public Double getValorUnit() {
		return valorUnit;
	}

	public void setValorUnit(Double valorUnit) {
		this.valorUnit = valorUnit;
	}

	@Column(name="valor_total")
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
