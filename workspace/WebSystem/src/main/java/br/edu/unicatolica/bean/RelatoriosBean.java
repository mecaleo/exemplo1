package br.edu.unicatolica.bean;

import javax.faces.bean.ManagedBean;

import br.edu.unicatolica.filter.ProdutoVendaFilter;
import br.edu.unicatolica.jasper.Relatorios;

@ManagedBean
public class RelatoriosBean {

	private Relatorios rel;
	private ProdutoVendaFilter produtoVendaFiltro;

	public RelatoriosBean() {
		rel = new Relatorios();
		produtoVendaFiltro = new ProdutoVendaFilter();
	}

	public void geraRelatorioProdutosMaisVendidos() {
		try {
			rel.gerarRelatorioProdutosMaisVendidos(produtoVendaFiltro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void gerarRelatorioInventario() {
		try {
			rel.gerarRelatorioDeInventario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ProdutoVendaFilter getProdutoVendaFiltro() {
		return produtoVendaFiltro;
	}

	public void setProdutoVendaFiltro(ProdutoVendaFilter produtoVendaFiltro) {
		this.produtoVendaFiltro = produtoVendaFiltro;
	}

}
