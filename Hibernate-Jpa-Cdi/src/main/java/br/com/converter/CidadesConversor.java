package br.com.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.Util.HibernateUtil;
import br.com.model.Cidades;
import br.com.model.Estados;

@Named
public class CidadesConversor implements Converter, Serializable {
	
	@Inject
	private EntityManager entityManager;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigocidade) {


		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Cidades cidades = entityManager.find(Cidades.class, Long.parseLong(codigocidade));
		return cidades;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cidade) {

		if(cidade == null) {
			return null;
		}
		if(cidade instanceof Cidades) {
			return ((Cidades) cidade).getId().toString();
		}else {
			return cidade.toString();
					
		}
		
		
		
	}

}
