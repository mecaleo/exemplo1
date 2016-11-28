package br.edu.unicatolica.jsf.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.unicatolica.dao.ProdutoDAO;
import br.edu.unicatolica.entity.Produto;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value != null) {
			Long id = new Long(value);
			ProdutoFilter pf = new ProdutoFilter();
			pf.setCodigoProduto(id);
			return ProdutoDAO.getInstance().getProduto(pf);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		if(value != null) {
			Produto produto = new Produto();
			produto = (Produto) value;
			return produto.getDescricao();
		}
		
		return "";
	}
}
