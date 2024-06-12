<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resourse/css/estilo.css"> 
<title>Tela de Login</title>
</head>
<body>

    <header>
        <h1 id="titulo">Seja Bem Vindo</h1>
        <h3 id="SubTitulo">Sistema de teste</h3>
        <center>  <h3 style="color: red">${msg}</h3></center>
    </header>
   
 <section>
    
    <form action="usuarioServlets" method="post">

        <DIV id="topo">
            <p id="Login">Login</p>
            <input type="text" id="Login" name="Login" Placeholder="Usuário"><br>
            <p id="Senha">Senha</p>  
            <input type="password" id="Senha" name="Senha" Placeholder="Passaword"><br><p>
            <input type="submit" id="Entrar" name="Entrar" value="Autentificar">
        </DIV>
        </form>
      
</body>
</html>