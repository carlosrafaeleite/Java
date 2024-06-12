<%@page import="DAO.DaoLogin"%>
<%@page import="DAO.DaoUsuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src = "//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js" > </script> 
<script src = " resourse/JavaScripts/jquery.maskMoney.min.js " > </script> 


 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resourse/css/estiloProduto.css" type="text/css">
<title>Cadastro de Produtos</title>
</head>
<body>

	<header>
	<h1 id="titulo">Cadastro de Produtos</h1>
	<h3 id="SubTitulo">Bem vindo</h3>
	</header>

	<section>

	<form action="produtoServlets" method="post" id="formpro" onsubmit=" return validaCadProduto()? true : false;">

		<DIV id="topo">

			<p class="pProduto" id="produtoLabel">Código</p>
			<input class="inputProduto" type="text" id="idProduto"
				readonly="readonly" name="idProduto" value="${prod.idProduto}">
			<p>
			<p class="pProduto" id="produtoLabel">Produto</p>
			<input  class="inputProduto" type="text" id="nomeProduto" name="nomeProduto"
				value="${prod.nomeProduto}">
			<p>
			<p class="pProduto" id="produtoquant">Quantidade</p>
			<input class="inputProduto" onkeypress="somenteNumeros(this);"  type="text" id="QuantProduto"
				name="quantProduto" value="${prod.quantProduto}"/>
			<p>
			<p class="pProduto" id="produtovalor">Valor</p>
			
			<input class="inputProduto" data-thousands="." data-decimal="," data-prefix="R$ "  type="text" id="ValorProduto" name="valorProduto" 
				value="${prod.valorEmTexto}">
			<p>

				<input class="btnProduto" type="submit" id="salvar" value="Salvar Produto">
				
				
			<p>
			<p>
			<p></p>
			<p>
				<input class="btnProduto" type="submit" id="reset" name="reset" value="Cancelar"
					onclick="document.getElementById('formpro').action = 'produtoServlets?acao=reset'">
			</p>
		</DIV>

	</form>
	</section>

	<center>
		<h2>Produtos Cadastrados</h2>
		<h2 style="color: red">${msg}</h2>
	</center>

	<table class="table table-responsive" align="center">

		<tbody>
		<thead>
			<tr>
				<th>id</th>
				<th>Produto</th>
				<th>Quantidade</th>
				<th>Valor</th>
				<th>Deletar</th>
				<th>Alterar</th>
			</tr>

		</thead>

		<c:forEach items="${produto}" var="prod">

			<tr>

				<td><c:out value="${prod.idProduto}"></c:out></td>
				<td><c:out value="${prod.nomeProduto}"></c:out></td>
				<td><c:out value="${prod.quantProduto}"></c:out></td>
				
				<!-- <td><c:out value="${prod.valorProduto}"></c:out></td> -->
				
				<fmt:setLocale value="pt_BR"/>
				<td><fmt:formatNumber type="currency" maxFractionDigits="2" minFractionDigits="2" value="${prod.valorProduto}" /></td>

				<td><a
					href="produtoServlets?acao=delete&prod=${prod.idProduto}&produto=${prod.nomeProduto}"><img
						src="resourse/imagens/deleta.png"></a></td>

				<td><a
					href="produtoServlets?acao=editar&prod=${prod.nomeProduto}"><img
						src="resourse/imagens/edita.png"></a></td>

			</tr>

		</c:forEach>
		</tbody>


	</table>


	<script src="resourse/JavaScripts/validaCampoCadProduto.js"></script>

	
	<script>

        
    $ ( function ()  { 
      $ ( "#ValorProduto" ).maskMoney(); 
    }) 
  </script>
	

    

</body>
</html>