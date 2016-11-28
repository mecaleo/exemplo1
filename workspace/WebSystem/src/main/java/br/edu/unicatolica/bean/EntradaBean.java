package br.edu.unicatolica.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.unicatolica.dao.ProdutoDAO;
import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.entity.ProdutoEntrada;
import br.edu.unicatolica.filter.EntradaFilter;
import br.edu.unicatolica.jsf.util.MessagesView;
import br.edu.unicatolica.jsf.util.ProdutoFilter;

@ManagedBean
@ViewScoped
public class EntradaBean {

	private EntradaFilter filtro;
	private ProdutoEntrada produtoEntrada;
	private ProdutoFilter produtoFiltro;
	private MessagesView m;
	private List<ProdutoEntrada> listaItens;

	public EntradaBean() {
		filtro = new EntradaFilter();
		produtoEntrada = new ProdutoEntrada();
		produtoFiltro = new ProdutoFilter();
		m = new MessagesView();
		listaItens = new ArrayList<>();
	}

	public void buscarProduto() {
		Produto p = new Produto();
		p = ProdutoDAO.getInstance().getProduto(produtoFiltro);
		if (p != null) {
			produtoEntrada.setProduto(p);
			produtoEntrada.setValorUnit(p.getPrecoCusto());
			produtoEntrada.setValorTotal(p.getPrecoCusto());
		} else {
			produtoEntrada = new ProdutoEntrada();
			m.error("Produto não encontrado!");
		}
	}

	public void calcularValorTotalDoItem() {
		try {
			double valorTotal = produtoEntrada.getQtdeEntrada() * produtoEntrada.getValorUnit();
			produtoEntrada.setValorTotal(valorTotal);
		} catch (NumberFormatException e) {
			m.error("Valor digitado é inválido!");
		}
	}

	public void addItem() {
		if(produtoEntrada.getProduto() == null) {
			m.error("Selecione um produto!");
		} else if(produtoEntrada.getQtdeEntrada() == null) {
			m.error("Informe a quantidade!");
		} else if(produtoEntrada.getValorUnit() == 0.0) {
			m.error("O valor unitário deve ser maior que 0,00!");
		} else {
			listaItens.add(produtoEntrada);
			produtoEntrada = new ProdutoEntrada();
			produtoFiltro = new ProdutoFilter();
		}
	}

	public EntradaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(EntradaFilter filtro) {
		this.filtro = filtro;
	}

	public ProdutoEntrada getProdutoEntrada() {
		return produtoEntrada;
	}

	public void setProdutoEntrada(ProdutoEntrada produtoEntrada) {
		this.produtoEntrada = produtoEntrada;
	}

	public ProdutoFilter getProdutoFiltro() {
		return produtoFiltro;
	}

	public void setProdutoFiltro(ProdutoFilter produtoFiltro) {
		this.produtoFiltro = produtoFiltro;
	}

	public List<ProdutoEntrada> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<ProdutoEntrada> listaItens) {
		this.listaItens = listaItens;
	}

}
