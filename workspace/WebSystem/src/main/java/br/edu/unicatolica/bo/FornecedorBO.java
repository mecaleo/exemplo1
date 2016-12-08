package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.FornecedorDAO;
import br.edu.unicatolica.entity.Fornecedor;
import br.edu.unicatolica.exceptions.TelefoneException;
import br.edu.unicatolica.exceptions.VazioException;

public class FornecedorBO {

	private static FornecedorBO instance;

	public static FornecedorBO getInstance() {
		if (instance == null) {
			instance = new FornecedorBO();
		}
		return instance;
	}

	public void salvar(Fornecedor forn) throws VazioException, TelefoneException {
		if (forn.getNomeFantasia().equals("") || forn.getLogradouro().equals("") || forn.getTelefone().equals("")) {
			throw new VazioException("Preencha os campos obrigatórios!");
		}
		if (forn.getTelefone().length() != 13) {
			throw new TelefoneException("Telefone inválido!");
		}
		Fornecedor temp = forn;
		forn.setNomeFantasia(temp.getNomeFantasia().toUpperCase());
		FornecedorDAO.getInstance().salvar(forn);
	}

	public void atualizar(Fornecedor forn) throws VazioException, TelefoneException {
		if (forn.getNomeFantasia().equals("") || forn.getLogradouro().equals("") || forn.getTelefone().equals("")) {
			throw new VazioException("Preencha os campos obrigatórios!");
		}
		if (forn.getTelefone().length() != 13) {
			throw new TelefoneException("Telefone inválido!");
		}
		FornecedorDAO.getInstance().editar(forn);

	}
	
	public void excluir(Fornecedor forn) throws VazioException{
		if(forn.getCodigo() == null) {
			throw new VazioException("Selecione um fornecedor!");
		}
		FornecedorDAO.getInstance().excluir(forn);
	}

}
