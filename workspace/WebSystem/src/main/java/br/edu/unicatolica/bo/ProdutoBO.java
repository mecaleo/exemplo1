package br.edu.unicatolica.bo;

import br.edu.unicatolica.dao.ProdutoDAO;
import br.edu.unicatolica.entity.Produto;
import br.edu.unicatolica.jsf.util.ProdutoFilter;

public class ProdutoBO {
	
	private static ProdutoBO instance;
	
	public static ProdutoBO getInstance() {
		if(instance == null) {
			instance = new ProdutoBO();
		}
		return instance;
	}
	
	public void salvar(Produto p) {
		Produto temp = p;
		p.setDescricao(temp.getDescricao().toUpperCase());
		p.setEstoque(0.00);
		ProdutoDAO.getInstance().salvar(p);
	}
	
	public void excluir(Produto p) {
		ProdutoDAO.getInstance().excluir(p);
	}
	
	public Produto getProduto(ProdutoFilter pf) {
		if(ProdutoDAO.getInstance().getProduto(pf) != null) {
			return ProdutoDAO.getInstance().getProduto(pf);
		}
		return null;
	}
}
