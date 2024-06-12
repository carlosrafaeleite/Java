<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous">
</script>
<meta charset="ISO-8859-1">
<title>Load jquery Pai</title>
</head>
<body>
<h1>Página Pai</h1>

<a href="../index.jsp">Voltar ao Menu</a><p>

<input type="button" value="Carregando" onclick="carregar();">

<div id="carregarPaginaFilha"></div>

</body>

<script type="text/javascript">
function carregar(){
	$("#carregarPaginaFilha").load('LoadJqueryFilha.jsp');
}

</script>
</html>