package mz.com.venda.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import mz.com.venda.hibernate.session.HibernateUtil;
import mz.com.venda.model.Provincia;

@FacesConverter(forClass = Provincia.class)
public class ProvinciaConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()) {
			return (Provincia) HibernateUtil.getCurrentSession().get(Provincia.class, new Long(codigo));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objecto) {
		if (objecto != null) {
			Provincia provincia = (Provincia) objecto;
			
			return provincia.getProv_id()!=null && provincia.getProv_id()>0? provincia.getProv_id().toString():null;
		}

		return null;
	}

}
