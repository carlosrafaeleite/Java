function validaCadProduto() {

	if (document.getElementById("nomeProduto").value == '') {

		window.alert('Insira um Produto');
		return false;

	} else

	if (document.getElementById("QuantProduto").value == '') {

		window.alert('Insira uma Quantidade');

		return false;

	} else

	if (document.getElementById("ValorProduto").value == '') {

		window.alert('Insira um Valor');
		return false;
	} else

		return true;

}