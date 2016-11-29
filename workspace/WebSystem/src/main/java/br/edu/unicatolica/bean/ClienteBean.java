package br.edu.unicatolica.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.unicatolica.bo.ClienteBO;
import br.edu.unicatolica.dao.ClienteDAO;
import br.edu.unicatolica.entity.Cliente;
import br.edu.unicatolica.jsf.util.MessagesView;

@ManagedBean
@ViewScoped
public class ClienteBean {

	private Cliente cliente;
	private MessagesView m;
	private String consulta;

	public ClienteBean() {
		novo();
		m = new MessagesView();
	}

	public void novo() {
		cliente = new Cliente();
	}
	
	public String retornaAcao() {
		if(cliente.getCodigo() != null) {
			return "Atualizar";
		} else {
			return "Salvar";
		}
	}

	public void salvar() {
		try {

			if (cliente.getCodigo() == null) {
				ClienteBO.getInstance().salvar(cliente);
				m.info("Cliente cadastrado com sucesso!");
			} else {
				ClienteBO.getInstance().salvar(cliente);
				m.info("Dados do cliente atualizados com sucesso!");	
			}
			novo();
		} catch (Exception e) {
			m.error("Error: reporte ao desenvolvedor!");
		}
	}

	public void excluir() {
		try {
			ClienteBO.getInstance().excluir(cliente);
			m.info("Cliente excluído com sucesso!");
		} catch (Exception e) {
			m.error("Erro ao tentar excluir o cliente!");
		}
	}

	public List<Cliente> listar() {
		try {
			return ClienteDAO.getInstance().listar(consulta);
		} catch (Exception e) {
			m.error("Error");
		}
		return null;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

}
