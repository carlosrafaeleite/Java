package daos;

import java.io.Serializable;

import javax.ejb.Stateful;

import models.Estados;

@Stateful
public class DaoEstado<TIPO> extends DaoGenerico<Estados> implements Serializable{


	private static final long serialVersionUID = 1L;
	
	public DaoEstado(){
		
		super();
	
		classePersistente  = Estados.class;
	}
	
	
	

}
