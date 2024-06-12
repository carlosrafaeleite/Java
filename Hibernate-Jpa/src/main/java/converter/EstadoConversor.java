package converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import Util.HibernateUtil;
import model.Estados;

@FacesConverter(forClass = Estados.class, value = "estadoconverter")
public class EstadoConversor implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoestado) {
		
		EntityManager entityManager = HibernateUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Estados estados = entityManager.find(Estados.class, Long.parseLong(codigoestado)); 
		return estados;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object estado) {
		
		if(estado == null){
			return null;
		}
		
		if(estado instanceof Estados) {
			return ((Estados)estado).getId().toString();
		}else {
			return estado.toString();
					
		}
		
		
		
	}

}
