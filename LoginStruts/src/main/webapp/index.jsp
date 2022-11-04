<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tela de Login</title>
</head>
<body>

	<h1>Faça seu Login</h1>
	
	
	<s:form action="contato">
	
			
			
			<s:textfield id="nome" name="nome"  label="Nome"></s:textfield><br/>
			
			<s:textfield id="senha" name="senha" label="Senha"></s:textfield><br/>
			<s:submit name="btn" id="btn" value="Enviar" />
		   
	
	
	</s:form>
	
	
	

</body>
</html>