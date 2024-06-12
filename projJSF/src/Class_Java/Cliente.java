package Class_Java;

public class Cliente {
	
	private String NomeCliente;
	private String SenhaCliente;
	
	
	public String getNomeCliente() {
		return NomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		NomeCliente = nomeCliente;
	}
	public String getSenhaCliente() {
		return SenhaCliente;
	}
	public void setSenhaCliente(String senhaCliente) {
		SenhaCliente = senhaCliente;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NomeCliente == null) ? 0 : NomeCliente.hashCode());
		result = prime * result + ((SenhaCliente == null) ? 0 : SenhaCliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (NomeCliente == null) {
			if (other.NomeCliente != null)
				return false;
		} else if (!NomeCliente.equals(other.NomeCliente))
			return false;
		if (SenhaCliente == null) {
			if (other.SenhaCliente != null)
				return false;
		} else if (!SenhaCliente.equals(other.SenhaCliente))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cliente [NomeCliente=" + NomeCliente + ", SenhaCliente=" + SenhaCliente + "]";
	}
	
	

}
