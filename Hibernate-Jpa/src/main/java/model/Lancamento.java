package model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity	
public class Lancamento implements Serializable {
	

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String numeroNota;
	private String empresaOrigem;
	private String empresaDestino;
	private String entregar;
	private String[] horarioEntrega;
	
	
	
	@ManyToOne(optional = false)
	private Usuario usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroNota() {
		return numeroNota;
	}
	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}
	public String getEmpresaOrigem() {
		return empresaOrigem;
	}
	public void setEmpresaOrigem(String empresaOrigem) {
		this.empresaOrigem = empresaOrigem;
	}
	public String getEmpresaDestino() {
		return empresaDestino;
	}
	public void setEmpresaDestino(String empresaDestino) {
		this.empresaDestino = empresaDestino;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getEntregar() {
		return entregar;
	}
	public void setEntregar(String entregar) {
		this.entregar = entregar;
	}
	
	public String[] getHorarioEntrega() {
		return horarioEntrega;
	}
	public void setHorarioEntrega(String[] horarioEntrega) {
		this.horarioEntrega = horarioEntrega;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", numeroNota=" + numeroNota + ", empresaOrigem=" + empresaOrigem
				+ ", empresaDestino=" + empresaDestino + ", entregar=" + entregar + ", horarioEntrega="
				+ Arrays.toString(horarioEntrega) + ", usuario=" + usuario + "]";
	}
	

}
