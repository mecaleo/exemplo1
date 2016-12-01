package br.edu.unicatolica.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.unicatolica.bo.EntradaBO;
import br.edu.unicatolica.dao.ProdutoDAO;
import br.edu.unicatolica.dao.ProdutoEntradaDAO;
import br.edu.unicatolica.entity.Entrada;
import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.entity.ProdutoEntrada;
import br.edu.unicatolica.exceptions.VazioException;
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
	private Entrada entrada;
	
	public EntradaBean() {
		novo();
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
	
	public void removerItem() {
		if(listaItens.size() != 0) {
			listaItens.remove(produtoEntrada);
		}
	}
	
	public Double subtotal() {
		double total = 0.00;
		for(ProdutoEntrada pe : listaItens) {
			total += pe.getValorTotal();
		}
		entrada.setValorTotal(total);
		return total;
	}
	
	public void finalizar() {
		try {
			entrada.setDataEntrada(new Date());
			EntradaBO.getInstance().salvar(entrada);
			for(ProdutoEntrada pe : listaItens) {
				pe.setEntrada(entrada);
				ProdutoEntradaDAO.getInstance().salvar(pe);
				ProdutoDAO.getInstance().acrescentarEstoque(pe);
				pe = new ProdutoEntrada();
			}
			m.info("Entrada finalizada com sucesso!");
			novo();
		} catch (VazioException e) {
			m.error(e.getMessage());
		}
	}

	public void novo() {
		filtro = new EntradaFilter();
		produtoEntrada = new ProdutoEntrada();
		produtoFiltro = new ProdutoFilter();
		m = new MessagesView();
		listaItens = new ArrayList<>();
		entrada = new Entrada();
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

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	
}
