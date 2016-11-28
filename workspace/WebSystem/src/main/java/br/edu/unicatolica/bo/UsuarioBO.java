package br.edu.unicatolica.bo;
import java.io.Serializable;

import br.edu.unicatolica.dao.UsuarioDAO;
import br.edu.unicatolica.entity.Usuario;

public class UsuarioBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static UsuarioBO instance;
	
	public UsuarioBO() {
		
	}
	
	public static UsuarioBO getInstance() {
		if(instance == null) {
			instance = new UsuarioBO();
		}
		return instance;
	}
	
	public boolean autenticar(Usuario us) {
		if(UsuarioDAO.getInstance().autenticar(us) == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void salvar(Usuario us) throws Exception{
		if(us.getNomeUsuario().equals("") || us.getSenha().equals("")) {
			throw new Exception("Preencha todos os campos para efetivar o cadastro!");
		}
		UsuarioDAO.getInstance().salvar(us);
	}
	
	public void atualizar(Usuario us) throws Exception {
		if(us.getNomeUsuario().equals("") || us.getSenha().equals("")) {
			throw new Exception("Preencha todos os campos para atualizar os dados!");
		}
		UsuarioDAO.getInstance().atualizar(us);
	}
	
	public void excluir(Usuario us) {
		UsuarioDAO.getInstance().excluir(us);
	}
}
