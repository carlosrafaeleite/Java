<%@ page language="java" contentType="text/html;  charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<%@page import="br.com.struts.produtos.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bem Vindo</title>
</head>
<body>
	<h1>Trabalhos</h1>

	<h3>Struts</h3>

	<s:form action="contato">

		<s:textfield name="nome" id="nome" label="nome" />
		<br />

		<s:textfield name="senha" id="senha" label="senha" />
		<br />
		<p />

		<s:textfield name="email" id="email" label="email" />
		<br />

		<s:radio list="{'M','F'}" name="sexo" label="sexo" />
		<br />
		<s:select label="tempo" name="dia" list="{'Chuva','Sol'}"
			headerKey="-1" headerValue="Clima" />
		<br />

		<s:select multiple="true" value="esporte" label="esporte"
			name="esporte" list="{'Sim','Nao'}" headerKey="-1"
			headerValue="Esporte" />
		<br />
		
		<s:checkbox name ="ckeck" value="false" label="Checando o box"/>
		<br/>
		
		<s:textarea name="area" id="area" label="area" cols="30" rows="5" />

 
		<s:submit name="btn" id="btn" value="Enviar" />
		<s:reset name="Rbtn" id="Rbtn" value="apagar" />


	</s:form>
	
	
	<table  border="1"  >
	
	<tr>
	<th>Codigo</th>
	<th>Nome</th>
	<th>Preço</th>
	</tr>
	
	<s:iterator value="produtos" var="pro"> <s:property value="pro."/> </s:iterator>
	
	<s:iterator value="produtos" var=>
	
	<tr>
		<s:property value="#pro.codig"/>
	
	</tr>
	<tr>
		<s:property value="#pro.nomeproduto"/>
		
	</tr>
	<tr>
		<s:property value="#pro.preco"/>
	
	</tr>
	
	
	</s:iterator>
	
	
	
	</table>
	



</body>
</html>