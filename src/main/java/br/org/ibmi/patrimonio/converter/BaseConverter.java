package br.org.ibmi.patrimonio.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import br.gov.frameworkdemoiselle.util.Beans;
import br.org.ibmi.patrimonio.business.BaseBC;
import br.org.ibmi.patrimonio.domain.Base;

@Named
@FacesConverter(forClass =Base.class)
public class BaseConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		Object result=null;
		if(value != null){
			BaseBC bc = Beans.getReference(BaseBC.class);
			result = bc.load(Long.parseLong(value)); 
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		String result = "";
		
		if (value != null) {
			result = ((Base) value).getCodigo().toString();
		}
		return result;
	}


}
