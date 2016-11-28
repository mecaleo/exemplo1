package br.edu.unicatolica.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import br.edu.unicatolica.bo.ProdutoBO;
import br.edu.unicatolica.bo.VendaBO;
import br.edu.unicatolica.dao.ClienteDAO;
import br.edu.unicatolica.dao.ProdutoDAO;
import br.edu.unicatolica.dao.ProdutoVendaDAO;
import br.edu.unicatolica.entity.Cliente;
import br.edu.unicatolica.entity.ProdutoVenda;
import br.edu.unicatolica.entity.Venda;
import br.edu.unicatolica.jsf.util.MessagesView;
import br.edu.unicatolica.jsf.util.ProdutoFilter;

@ManagedBean
@ViewScoped
public class VendaBean {

	private Venda venda;
	private List<ProdutoVenda> listaItens;
	private ProdutoVenda produtoVenda;
	private ProdutoFilter produtoFilter;
	private MessagesView m;
	private Cliente cliente;
	private List<Cliente> listaClientes;
	private boolean status;
	
	public VendaBean() {
		novo();
		produtoFilter = new ProdutoFilter();
		m = new MessagesView();
		cliente = new Cliente();
		status = false;
	}

	public void novo() {
		venda = new Venda();
		produtoVenda = new ProdutoVenda();
		listaItens = new ArrayList<>();
	}

	public void addItem() {
		double qtdeLista = 0.00;
		for (ProdutoVenda pv : listaItens) {
			if (produtoFilter.getCodigoProduto() == pv.getProduto().getCodigo()) {
				qtdeLista += pv.getQtde();
			}
		}
		if ((produtoVenda.getQtde() + qtdeLista) > produtoVenda.getProduto().getEstoque()) {
			m.alerta("Quantidade insuficiente no estoque!");
		} else {
			listaItens.add(produtoVenda);
			produtoVenda = new ProdutoVenda();
			produtoFilter = new ProdutoFilter();
		}
	}

	public void buscarProduto() {
		if (produtoFilter.getCodigoProduto() != null) {
			if (ProdutoBO.getInstance().getProduto(produtoFilter) == null) {
				m.error("Produto não encontrado!");
				produtoVenda = new ProdutoVenda();
			} else {
				produtoVenda.setProduto((ProdutoBO.getInstance().getProduto(produtoFilter)));
				produtoVenda.setVlrUnitario(produtoVenda.getProduto().getPrecoVenda());
				produtoVenda.setVlrTotal(produtoVenda.getProduto().getPrecoVenda());
			}
		} else {
			produtoVenda = new ProdutoVenda();
		}
	}

	public Double total() {
		double valor = 0.0;
		for (ProdutoVenda pv : listaItens) {
			valor += pv.getVlrTotal();
		}
		venda.setValorTotal(valor);
		return valor;
	}

	public void calculaValorTotal() {
		if (produtoVenda.getQtde() != null) {
			produtoVenda.setVlrTotal(produtoVenda.getVlrUnitario() * produtoVenda.getQtde());
		}
	}

	public void finalizar() {
		try {
			if (listaItens.size() == 0) {
				m.alerta("Lista de itens vazia!");
			} else {
				venda.setDataVenda(new Date());
				VendaBO.getInstance().finalizar(venda);
				salvarItensDaVenda();
				status = true;
				m.info("Venda finalizada com sucesso!");
			}
		} catch (Exception e) {
			m.alerta(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void salvarItensDaVenda() {
		for(ProdutoVenda item : listaItens) {
			item.setVenda(venda);
			ProdutoVendaDAO.getInstance().salvar(item);
			ProdutoDAO.getInstance().baixarEstoque(item);
			item = new ProdutoVenda();
		}
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<ProdutoVenda> getListaItens() {
		return listaItens;
	}

	public void setListaItens(List<ProdutoVenda> listaItens) {
		this.listaItens = listaItens;
	}

	public ProdutoVenda getProdutoVenda() {
		return produtoVenda;
	}

	public void setProdutoVenda(ProdutoVenda produtoVenda) {
		this.produtoVenda = produtoVenda;
	}

	public ProdutoFilter getProdutoFilter() {
		return produtoFilter;
	}

	public void setProdutoFilter(ProdutoFilter produtoFilter) {
		this.produtoFilter = produtoFilter;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListaClientes() {
		return ClienteDAO.getInstance().listar("");
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
