<%@page import="DAO.DaoLogin"%>
<%@page import="DAO.DaoUsuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resourse/css/estiloTelefone.css" type="text/css">



</script>





<title>Cadastro de Telefones</title>
</head>
<body>

	<header>
	<h1 id="titulo">Telefone Usuário</h1>
	<h3 id="SubTitulo">Bem vindo</h3>
	</header>



	<section id="secao1">

	<form action="telefoneServlets" method="post" id="formTelefone">

		<DIV id="topo2">

			<div id="centroTel">

				<p class="pTelefone" id="cadCodigo">Código</p>
				<input class="inputTelefone" type="text" id="userId" readonly="readonly"
					name="userId" value="${userEscolhido}"><br>
				<p>
				
				<p class="pTelefone" id="cadNome">Nome</p>
				<input class="inputTelefone" type="text" id="userNome" name="userNome"
					readonly="readonly" value="${nomeEscolhido}"  ><br>
					
				<p>
					<input class="btnTelefone" type="submit" id="salvar" name="salvar"
						value="Salvar Telefone">
				<p>
				
			</div>

			<div id="centroTel2">

				<p class="pTelefone" id="cadTelefone">Telefone</p>
				<input class="inputTelefone" type="text" id="telefone" name="telefone"><br>
				<p>
				
				<p class="pTelefone" id="tipoTelefone">Tipo</p>
				<select class="comboTelefone" name="tipos" id="tipos">
					<option value="residencial">Residêncial</option>
					<option value="celular">Celular</option>
					<option value="comercial">Comercial</option>
				</select> <br>
				
				<p>
				
				<p>
					<input class="btnTelefone" type="submit" id="voltar" name="voltar"
						value="Finalizar e Voltar" onclick="document.getElementById('formTelefone').action = 'telefoneServlets?acao=voltar'">
				<p>
				
			</div>
	</form>

	</section>

	<center>
		<h2>Telefones Cadastrados</h2>
		<h2 style="color: red">${msg}</h2>
	</center>




	<table class="table table-responsive" align="center">

		<tbody>
		<thead>
			<tr>
				<th>id</th>
				<th>Id Telefone</th>
				<th>Telefone</th>
				<th>Tipo</th>
				<th>Excluir</th>

			</tr>

		</thead>

		<c:forEach items="${telefones}" var="fone">

			<tr>
				<td><c:out value="${fone.idUser}"></c:out></td>
				<td><c:out value="${fone.idFoneUser}"></c:out></td>
				<td><c:out value="${fone.tefoneUser}"></c:out></td>
				<td><c:out value="${fone.tipoTelefone}"></c:out></td>

				<td><a href="telefoneServlets?acao=deleteFone&foneId=${fone.idFoneUser}"><img
						src="resourse/imagens/deleta.png"></a></td>
			</tr>
		</c:forEach>
		</tbody>


	</table>



	<script src="resourse/JavaScripts/buscaCep.js"></script>
	<script src="resourse/JavaScripts/limpaCampos.js"></script>
</body>
</html>