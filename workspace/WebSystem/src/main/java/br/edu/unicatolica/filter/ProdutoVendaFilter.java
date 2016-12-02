package br.edu.unicatolica.filter;

import java.util.Date;

import br.edu.unicatolica.entity.Venda;

public class ProdutoVendaFilter {

	private Venda venda;
	private Date dataInicial;
	private Date dataFinal;

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

}
