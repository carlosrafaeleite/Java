<%@ page language="java" contentType="text/html;  charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Contatos</title>
</head>
<body>

	
	<h3>Com jsp</h3>
	${concatenacao}
<br />
	<h3>Com Struts</h3>
	
	<s:label value="nome" />
	<s:property value="nome" />
	<br />

	<s:label value="senha" />
	<s:property value="senha"/>
	<br />
	
	<s:label value="email" />
	<s:property value="email" />
	<br />
	
	<s:label value="area" />
	<s:property value="area" />
	<br />
	
	<s:label value="sexo" />
	<s:property value="sexo" />
	<br />
	
	<s:label value="dia" />
	<s:property value="dia" />
	<br />
	
	<s:label value="esporte" />
	<s:property value="esporte" />
	<br />
	
	
	
	
	




</body>
</html>