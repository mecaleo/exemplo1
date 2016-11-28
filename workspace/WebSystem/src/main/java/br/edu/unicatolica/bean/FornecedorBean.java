package br.edu.unicatolica.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.unicatolica.bo.FornecedorBO;
import br.edu.unicatolica.dao.FornecedorDAO;
import br.edu.unicatolica.entity.Fornecedor;
import br.edu.unicatolica.exceptions.TelefoneException;
import br.edu.unicatolica.exceptions.VazioException;
import br.edu.unicatolica.jsf.util.MessagesView;

@ManagedBean
@ViewScoped
public class FornecedorBean {

	private Fornecedor fornecedor;
	private MessagesView m;
	private String consulta;

	public FornecedorBean() {
		fornecedor = new Fornecedor();
		m = new MessagesView();
		consulta = "";
	}

	public void salvarOuAtualizar() {
		try {
			if (fornecedor.getCodigo() == null) {
				FornecedorBO.getInstance().salvar(fornecedor);
				m.info("Fornecedor cadastrado com sucesso!");
				novo();
			} else {
				FornecedorBO.getInstance().atualizar(fornecedor);
				m.info("Dados do fornecedor atualizados com sucesso!");
				novo();
			}
		} catch (VazioException | TelefoneException e) {
			m.alerta(e.getMessage());
		}
	}
	
	public void excluir() {
		try {
			FornecedorBO.getInstance().excluir(fornecedor);
			m.info("Fornecedor excluído com sucesso!");
			novo();
		} catch (VazioException e) {
			m.alerta(e.getMessage());
		}
	}

	public String renomeaButao() {
		if (fornecedor.getCodigo() == null) {
			return "Salvar";
		} else {
			return "Atualizar";
		}
	}

	public void pesquisar() {
		listarFornecedores();
	}

	public void novo() {
		if (fornecedor.getCodigo() != null) {
			fornecedor = new Fornecedor();
		}

	}

	public List<Fornecedor> listarFornecedores() {
		return FornecedorDAO.getInstance().listar(consulta);
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

}
