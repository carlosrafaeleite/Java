<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<style type="text/css"> @import url("../resourse/css/senhaLogin.css"); </style>
<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous">
</script>


<title>Login e Senha</title>
</head>
<body>
	<header>
		<h1>Autenticação de Usuário</h1>
		<nav>
			<ul>
				<li><a href="../index.jsp">Inicio</a></li>
				<li><a href="acesso.jsp">Acesso</a></li>
			</ul>
		</nav>
	</header>
	<section>
	 <div id="bloco">
		<form action="AutenticacaoUser" method="post" id="FormAcesso">
		<div>
				<input type="hidden" id="url" name="url" value="<%=request.getParameter("url")%>">
		</div>
			<div >
				<input class="input" id="login" name="login" placeholder="Nome Usuário" type="text" />
			</div>
	
			<div>
				<input class="input" id="senha" name="senha"  placeholder="Senha Usuário" type="text" />
			</div>
		
			<div>
				<input class="btn" type="submit" id="enviarUsuario" Value="Autenticar" />
			</div>
			
			  <div class="texto"  id="texto3"><a href="http://www.globo.com">Esqueceu a senha</a></div>
              <div class="texto"  id="texto4"><a href="http://www.facebook.com">Cadastre-se</a></div>
		</form>
		</div>
			
	</section>
	<center> <h2 style="color: red" >${msg}</h2></center> 

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