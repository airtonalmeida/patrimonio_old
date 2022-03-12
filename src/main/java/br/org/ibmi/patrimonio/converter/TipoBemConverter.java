package br.org.ibmi.patrimonio.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import br.gov.frameworkdemoiselle.util.Beans;
import br.org.ibmi.patrimonio.business.TipoBemBC;
import br.org.ibmi.patrimonio.domain.TipoBem;

@Named
@FacesConverter(forClass =TipoBem.class)
public class TipoBemConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		Object result=null;
		if(value != null){
			TipoBemBC tpBembc = Beans.getReference(TipoBemBC.class);
			result = tpBembc.load(Long.parseLong(value)); 
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		String result = "";
		
		if (value != null) {
			result = ((TipoBem) value).getCodigo().toString();
		}
		return result;
	}


}
