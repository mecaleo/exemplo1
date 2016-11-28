package br.edu.unicatolica.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.unicatolica.bo.UsuarioBO;
import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.jsf.util.MessagesView;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private MessagesView m;
	private String consulta;

	public UsuarioBean() {
		usuario = new Usuario();
		m = new MessagesView();
	}

	public void novo() {
		usuario = new Usuario();
	}

	public void salvar() {
		try {
			UsuarioBO.getInstance().salvar(usuario);
			m.info("Usuário cadastrado com sucesso!");
			novo();
		} catch (Exception e) {
			e.printStackTrace();
			m.error(e.getMessage());
		}

	}

	public void atualizar() {
		try {
			UsuarioBO.getInstance().atualizar(usuario);
			m.info("Dados do usuário atualizados com sucesso!");
			System.out.println("deu certo");
			novo();
		} catch (Exception e) {
			e.printStackTrace();
			m.error(e.getMessage());
		}
	}

	public void excluir() {
		try {
			UsuarioBO.getInstance().excluir(usuario);
			m.info("Usuário excluído com sucesso!");
			novo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Usuario> listar() {
		// novo();
		try {
			if (consulta == null) {
				consulta = "";
			}
			return UsuarioDAO.getInstance().listar(consulta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

}
