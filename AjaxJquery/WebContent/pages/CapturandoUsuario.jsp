<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="senhaLogin.css" type="text/css" />
<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous">
	
</script>
<title>Login e Senha</title>
</head>
<body>
	<header>
		<h1>Autenticação</h1>

		<nav>
			<ul>
				<li><a href="http://localhost:8080/AulaAjaxJquery/">Inicio</a></li>
				<li><a href="pages/acesso.jsp">Acesso</a></li>
			</ul>
		</nav>
	</header>

	<section>
		<h2>Digite Seu Login e Senha</h2>
		<form id="form-1">
			<div>
				<label for="login">Login</label> <input id="login"
					placeholder="Nome Usuário" type="text" />
			</div>
			<br>
			<div>
				<label for="login">Senha</label> <input id="login"
					placeholder="Senha Usuário" type="text" />
			</div>
			<br>
			<div>
				<input type="submit" id="enviarUsuario" Value="Autenticar" />
			</div>
		</form>
	</section>





</body>

<!--
<input type="text" id="valor" placeholder="Digite uma informação">
<button value="enviar" id="enviar" onclick="retornaValor();">Enviar</button>


<script type="text/javascript">
	function retornaValor() {
		var valorInformado = $('#valor').val();
		$.ajax({
			method : "post",
			url : "Capturando",
			data : {
				valorParametro : valorInformado
			}
		}).always(function(response) {
			alert(response);

		});
	}
</script>
-->
</html>