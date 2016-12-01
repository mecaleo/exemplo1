package br.edu.unicatolica.jsf.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.unicatolica.dao.FornecedorDAO;
import br.edu.unicatolica.entity.Fornecedor;

@FacesConverter(forClass = Fornecedor.class)
public class FornecedorConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Fornecedor forn = null;
		if(value != null) {
			Long id = new Long(value);
			forn = FornecedorDAO.getInstance().getFornecedor(id);
		}
		return forn;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value != null) {
			Fornecedor forn = (Fornecedor) value;
			return forn.getCodigo() == null ? null : forn.getCodigo().toString();
		}
		return "";
	}

}
