package br.edu.unicatolica.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.unicatolica.bo.ProdutoBO;
import br.edu.unicatolica.dao.ProdutoDAO;
import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.jsf.util.MessagesView;
import br.edu.unicatolica.jsf.util.ProdutoFilter;

@ManagedBean
@ViewScoped
public class ProdutoBean {

	private Produto produto;
	private String consulta;
	private MessagesView m;
	private ProdutoFilter produtoFilter;

	public ProdutoBean() {
		novo();
		m = new MessagesView();
		consulta = "";
		produtoFilter = new ProdutoFilter();
	}

	public String acao() {
		if(produto.getCodigo() != null) {
			return "Atualizar";
		} else {
			return "Salvar";
		}
	}

	public void novo() {
		produto = new Produto();
	}

	public void salvar() {
		try {
			if (produto.getCodigo() == null) {
				m.info("Produto cadastrado com sucesso!");
			} else {
				m.info("Dados do produto atualizados com sucesso!");
			}
			ProdutoBO.getInstance().salvar(produto);
			novo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excluir() {
		try {
			ProdutoBO.getInstance().excluir(produto);
			m.info("Produto excluído com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	public List<Produto> listarProdutos() {
		return ProdutoDAO.getInstance().listar(consulta);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public ProdutoFilter getProdutoFilter() {
		return produtoFilter;
	}

	public void setProdutoFilter(ProdutoFilter produtoFilter) {
		this.produtoFilter = produtoFilter;
	}

}
