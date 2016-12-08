package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.ClienteDAO;
import br.edu.unicatolica.entity.Cliente;

public class ClienteBO {

	private static ClienteBO instance;
	
	public static ClienteBO getInstance() {
		if(instance == null) {
			instance = new ClienteBO();
		}
		return instance;
	}
	
	public void salvar(Cliente c) {
		Cliente temp = c;
		c.setNome(temp.getNome().toUpperCase());
		ClienteDAO.getInstance().salvar(c);
	}
	
	public void excluir(Cliente c) {
		ClienteDAO.getInstance().excluir(c);
	}
}
