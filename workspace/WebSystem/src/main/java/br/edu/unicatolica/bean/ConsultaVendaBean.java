package br.edu.unicatolica.bean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.edu.unicatolica.dao.ProdutoVendaDAO;
import br.edu.unicatolica.dao.VendaDAO;
import br.edu.unicatolica.entity.ProdutoVenda;
import br.edu.unicatolica.entity.Venda;
import br.edu.unicatolica.filter.VendaFilter;
import br.edu.unicatolica.jasper.Relatorios;

@ManagedBean
@ViewScoped
public class ConsultaVendaBean {

	private Venda venda;
	private List<Venda> listaVendas;
	private VendaFilter filtro;
	private List<ProdutoVenda> listaItens;

	public ConsultaVendaBean() {
		venda = new Venda();
		filtro = new VendaFilter();
		listaItens = new ArrayList<>();
	}

	public void consultar() throws ParseException {
		listaVendas = VendaDAO.getInstance().listarVendas(filtro);
	}

	public void gerarRelatorio() {
		try {
			Relatorios r = new Relatorios();
			r.gerarRelatorioVenda(filtro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Venda> getListaVendas() {
		return VendaDAO.getInstance().listarVendas(filtro);
	}

	public void setListaVendas(List<Venda> listaVendas) {
		this.listaVendas = listaVendas;
	}

	public VendaFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(VendaFilter filtro) {
		this.filtro = filtro;
	}

	public List<ProdutoVenda> getListaItens() {
		if (venda != null) {
			return ProdutoVendaDAO.getInstance().listaItens(venda);
		}
		return null;
	}

	public void setListaItens(List<ProdutoVenda> listaItens) {
		this.listaItens = listaItens;
	}

}
