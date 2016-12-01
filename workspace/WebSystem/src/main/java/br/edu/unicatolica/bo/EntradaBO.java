package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.EntradaDAO;
import br.edu.unicatolica.entity.Entrada;
import br.edu.unicatolica.exceptions.VazioException;

public class EntradaBO {

	private static EntradaBO instance;

	public static EntradaBO getInstance() {
		if (instance == null) {
			instance = new EntradaBO();
		}
		return instance;
	}

	public void salvar(Entrada entrada) throws VazioException{
		if (entrada.getCodigo() != null) {
			
		} else if (entrada.getFornecedor() == null) {
			throw new VazioException("Selecione um fornecedor!");
		} else {
			EntradaDAO.getInstance().salvar(entrada);
			
		}
	}

}
