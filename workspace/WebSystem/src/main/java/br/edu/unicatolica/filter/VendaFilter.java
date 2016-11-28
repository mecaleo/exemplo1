package br.edu.unicatolica.filter;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.unicatolica.entity.Cliente;

public class VendaFilter {

	private Cliente cliente;
	private Date dataInicial;
	private Date dataFinal;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

}
