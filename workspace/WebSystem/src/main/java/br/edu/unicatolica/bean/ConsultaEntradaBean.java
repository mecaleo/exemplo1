package br.edu.unicatolica.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.unicatolica.dao.EntradaDAO;
import br.edu.unicatolica.dao.ProdutoEntradaDAO;
import br.edu.unicatolica.entity.Entrada;
import br.edu.unicatolica.entity.ProdutoEntrada;
import br.edu.unicatolica.filter.EntradaFilter;

@ManagedBean
@ViewScoped
public class ConsultaEntradaBean {

	private Entrada entrada;
	private EntradaFilter filtro;
	private List<ProdutoEntrada> listaItens;

	public ConsultaEntradaBean() {
		novo();
	}

	public void novo() {
		entrada = new Entrada();
		filtro = new EntradaFilter();
	}

	public List<Entrada> listarEntradas() {
		return EntradaDAO.getInstance().listar(filtro);
	}

	public void pesquisar() {
		listarEntradas();
	}

	public List<ProdutoEntrada> listarItensEntrada() {
		return ProdutoEntradaDAO.getInstance().listaItens(entrada);
	}

	public void cancelarEntrada() {

	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public EntradaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(EntradaFilter filtro) {
		this.filtro = filtro;
	}

	public List<ProdutoEntrada> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<ProdutoEntrada> listaItens) {
		this.listaItens = listaItens;
	}

}
