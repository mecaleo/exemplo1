package br.edu.unicatolica.jsf.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.unicatolica.dao.ClienteDAO;
import br.edu.unicatolica.entity.Cliente;

@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Cliente cliente = null;
		if(value != null) {
			Long id = new Long(value);
			return ClienteDAO.getInstance().clienteSelecionado(id);
		}
		return cliente;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value != null) {
			Cliente cliente = (Cliente) value;
			return cliente.getCodigo() == null ? null : cliente.getCodigo().toString();
		}
		return "";
		
	}

}
