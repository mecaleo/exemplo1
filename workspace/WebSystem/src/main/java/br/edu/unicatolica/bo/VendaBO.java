package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.VendaDAO;
import br.edu.unicatolica.entity.Venda;

public class VendaBO {

	private static VendaBO instance;

	public static VendaBO getInstance() {
		if(instance == null) {
			instance = new VendaBO();
		}
		return instance;
	}

	public void finalizar(Venda venda) throws Exception {
		if (venda.getCliente() == null) {
			throw new Exception("Por favor selecione um cliente!");
		}
		VendaDAO.getInstance().salvar(venda);
	}
}
