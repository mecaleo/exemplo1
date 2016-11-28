package br.edu.unicatolica.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.Usuario;
import br.edu.unicatolica.jsf.util.MessagesView;

@ManagedBean
@SessionScoped
public class LoginBean {

	private Usuario usuario;
	private MessagesView m;

	public LoginBean() {
		usuario = new Usuario();
		m = new MessagesView();
	}

	public void autenticar() {
		try {
			if (UsuarioDAO.getInstance().autenticar(usuario) != null) {
				usuario = UsuarioDAO.getInstance().autenticar(usuario);
				FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
			} else {
				m.error("Usuário ou senha incorretos!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void sair() {
		try {
			usuario = new Usuario();
			FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void validar() {
		if(usuario.getCodigo() == null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
