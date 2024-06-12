package beans;

public class produtoBeans {
	
	private int idProduto;
	private String nomeProduto;
	private float quantProduto;
	private float valorProduto;
	
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public float getQuantProduto() {
		return quantProduto;
	}
	public void setQuantProduto(float quantProduto) {
		this.quantProduto = quantProduto;
	}
	public float getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(float valorProduto) {
		this.valorProduto = valorProduto;
	}
	
	public String getValorEmTexto() {
		
		return Float.toString(valorProduto).replace('.' , ',');
	}
	
	
	
	
}
