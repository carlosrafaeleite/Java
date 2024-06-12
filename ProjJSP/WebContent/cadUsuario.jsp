<%@page import="beans.usuarioBeans"%>
<%@page import="DAO.DaoLogin"%>
<%@page import="DAO.DaoUsuario"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; "
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resourse/css/estilo2.css" type="text/css">
<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous">
</script>


<title>Cadastro de Usuários</title>
</head>
<body>

	<header>
	<h1 id="titulo">Cadastro de Usuário</h1>

	</header>



	<section>

	<form action="salvarUsuario" method="post" id="formUser"
		enctype="multipart/form-data">

		<div id="topo">

			<div id="centro1">
			
				
				
				<p  class="pUsuario" id="cadCodigo">Código</p>
				<input  class="inpUsuarios" type="text" id="id" readonly="readonly"
					Placeholder="#" name="id" value="${user.id}"><br>
				<p>
				<p class="pUsuario" id="cadLogin">Nome</p>
				<input class="inpUsuarios" type="text" id="Login"
					name="LoginUsuario" value="${user.login}"><br>
				<p>
				<p class="pUsuario" id="cadSenha">Senha</p>
				<input class="inpUsuarios" type="text" id="Senha"
					name="SenhaUsuario" value="${user.senha}"><br>
				<p>
				<p class="pUsuario" id="cadCep">Cep</p>
				<input class="inpUsuarios" type="text" id="cep" name="cep"
					value="${user.cep}"><br>
				<p>
				<p class="pUsuario" id="cadRua">Rua</p>
				<input class="inpUsuarios" type="text" id="rua" name="rua"
					value="${user.rua}"><br>
				<p>
				
				<span class="pUsuario">Ativo:</span> 
				<input class="quadradinho"  type="checkbox" id="ativo" name="ativo"
				<%
				
				if(request.getAttribute("user") != null){
					
					usuarioBeans user = (usuarioBeans) request.getAttribute("user");
					if(user.isAtivo()){
						out.println(" ");
						out.println("checked=\"checked\"");
						out.println(" ");
					}
					
				}
				
				%>
				
				>
			
				<select class="select" id="cargo" name="cargo" >
					<option value="NaoInformado"
					
					 <%
				
				if(request.getAttribute("user") != null){
					
					usuarioBeans user = (usuarioBeans) request.getAttribute("user");
					if(user.getCargo().equalsIgnoreCase("NaoInformado")){
						out.println(" ");
						out.println("selected=\"selected\"");
						out.println(" ");
					}
					
				}
	
				%>
				
				>[--Cargo--]</option>
					<option value="Administrador"
					<%
				
				if(request.getAttribute("user") != null){
					
					usuarioBeans user = (usuarioBeans) request.getAttribute("user");
					if(user.getCargo().equalsIgnoreCase("Administrador")){
						out.println(" ");
						out.println("selected=\"selected\"");
						out.println(" ");
					}
					
				}
				
				%>
				
				>Administrador</option>
				
					<option value="Gerente"
					<%
				
				if(request.getAttribute("user") != null){
					
					usuarioBeans user = (usuarioBeans) request.getAttribute("user");
					if(user.getCargo().equalsIgnoreCase("Gerente")){
						out.println(" ");
						out.println("selected=\"selected\"");
						out.println(" ");
					}
					
				}
				
				%>>Gerente</option>
					<option value="Recepcionista" <%
				
				if(request.getAttribute("user") != null){
					
					usuarioBeans user = (usuarioBeans) request.getAttribute("user");
					if(user.getCargo().equalsIgnoreCase("Recepcionista")){
						out.println(" ");
						out.println("selected=\"selected\"");
						out.println(" ");
					}
					
				}
				
				%>
				>recepcionista</option>
					
					<option value="Serviços Gerais"<%
				
				if(request.getAttribute("user") != null){
					
					usuarioBeans user = (usuarioBeans) request.getAttribute("user");
					if(user.getCargo().equalsIgnoreCase("Serviços Gerais")){
						out.println(" ");
						out.println("selected=\"selected\"");
						out.println(" ");
					}
					
				}
				
				%>
				
				>Serviços Gerais</option>
				</select>	
							
				
			</div>

			<div id="centro2">

				<p class="pUsuario" id="cadBairro">Bairro</p>
				<input class="inpUsuarios" type="text" id="bairro" name="bairro"
					value="${user.bairro}"><br>
				<p>
				<p class="pUsuario" id="cadCidade">Cidade</p>
				<input class="inpUsuarios" type="text" id="cidade" name="cidade"
					value="${user.cidade}"><br>
				<p>
				<p class="pUsuario" id="cadEstado">Estado</p>
				<input class="inpUsuarios" type="text" id="estado" name="estado"
					value="${user.estado}"><br>
				<p>
				<p class="pUsuario" id="cadIbge">IBGE</p>
				<input class="inpUsuarios" type="text" id="ibge" name="ibge"
					value="${user.ibge}"><br>

				<p class="pUsuario" id="foto">Foto</p>
				<p>
				

					<label for="inpFoto" class="labelFoto" id="labelFoto" ></label>
					 <input type="file" class="inpFoto" id="inpFoto" name="inpFoto">
						<input type="text" class="recebe" id="recebe" name="recebe"	readonly="readonly" value = "${user.fotoBase64}" > 
						<input type="button" class="btn" id="btn" name="btn" value="Trocar" onclick="buscaCampo()">
				<p>
				
				<input type="radio" name="sexo" id="sexo"
				
				<%
				
				if(request.getAttribute("user") != null){
					
					usuarioBeans user = (usuarioBeans) request.getAttribute("user");
					if(user.getSexo().equalsIgnoreCase("masculino")){
						out.println(" ");
						out.println("checked=\"checked\"");
						out.println(" ");
					}
					
				}
				
				%>
				value="masculino"></input>
				
				<span class="pUsuario">Masculino</span>
				<input  type="radio" name="sexo" id="sexo"
				
				<%
				
				if(request.getAttribute("user") != null){
					
					usuarioBeans user = (usuarioBeans) request.getAttribute("user");
					if(user.getSexo().equalsIgnoreCase("feminino")){
						out.println(" ");
						out.println("checked=\"checked\"");
						out.println(" ");
					}
					
				}
				
				%>
				
				 value="feminino" ></input>
				<span class="pUsuario" >Feminino</span>
				
				
			</div>

			<div id="botao">

				<table id="Btns" align="center">

					<tr>

						<td><input class="btnUsuario" type="submit" id="salvar"
							name="salvar" value="Salvar Usuário"></td>

						<td></td>

						<td><input class="btnUsuario" type="submit" id="reset"
							name="reset" value="Cancelar"
							onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
					</tr>

				</table>
			</div>
	</form>

	</section>
	<center>
	<form method="post" action="servletPesquisaCli">

		<h2 style="font: bold 14pt Arial;">Consultar Usuários</h2>
			
		<input class="inpUsuarios" type="text" id="consultar" name="consultar" style="width: 500px;">
		<input class="btnUsuario" type="submit" id="btn_consultar" value="Consultar"  style="padding:4px">
	
	</form>
	</center>
	<center>
	<p></p>
	
		<h2 style="color: red">${msg}</h2>
	</center>




	<table class="table table-responsive" align="center">

		<tbody>
		<thead>
			<tr>
				<th>id</th>
				<th>Foto</th>
				<th>Login</th>
				<th>Senha</th>
				<th>Cep</th>
				<th>Rua</th>
				<th>Bairro</th>
				<th>Cidade</th>
				<th>Estado</th>
				<th>IBGE</th>
				<th>Deletar</th>
				<th>Alterar</th>
				<th>Telefone</th>
			</tr>

		</thead>

		<c:forEach items="${usuario}" var="user">

			<tr>
			
				<td><c:out value="${user.id}"></c:out></td>
				
				<c:if test="${user.fotoBase64Miniatura.isEmpty()== false}">
				<td><a href="salvarUsuario?acao=download&user=${user.id}">
				<img src='<c:out value="${user.fotoBase64Miniatura}"/>' alt="Imagem User" title="Imagem User" width="32px" height="32px" /> </a></td>
				</c:if>	
				
				<c:if test="${user.fotoBase64Miniatura.isEmpty()== true || user.fotoBase64Miniatura == null}">
				<td><img alt="Imagem Padrao" src="resourse/imagens/imgPadrao.png"  width="32px" height="32px" onclick="alert('Não divulgou a foto')"></td>
				</c:if>	
				
				
				<td><c:out value="${user.login}"></c:out></td>
				<td><c:out value="${user.senha}"></c:out></td>
				<td><c:out value="${user.cep}"></c:out></td>
				<td><c:out value="${user.rua}"></c:out></td>
				<td><c:out value="${user.bairro}"></c:out></td>
				<td><c:out value="${user.cidade}"></c:out></td>
				<td><c:out value="${user.estado}"></c:out></td>
				<td><c:out value="${user.ibge}"></c:out></td>
				
				

				<td><a onclick="return confirm('excluir mesmo');" href="salvarUsuario?acao=delete&user=${user.id}"><img
						src="resourse/imagens/deleta.png"></a></td>

				<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
						src="resourse/imagens/edita.png"></a></td>

				<td><a
					href="telefoneServlets?acao=addFone&userId=${user.id}&usernome=${user.login}"><img
						src="resourse/imagens/telefone.png"></a></td>
			</tr>
		</c:forEach>
		</tbody>


	</table>



	</div>
	</div>
	</div>
	</div>

	<script src="resourse/JavaScripts/buscaCep.js"></script>
	<script src="resourse/JavaScripts/buscaCampo.js"></script>
	<script src="resourse/JavaScripts/limpaCampos.js"></script>
</body>
</html>