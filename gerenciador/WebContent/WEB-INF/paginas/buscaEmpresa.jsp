<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resultado da Pesquisa</title>
</head>
<body>
	<div align="center" style="padding-top:5%">
		<h2>Resultado da Busca</h2>			
		<p><a href="index.jsp">Voltar</a></p>				
	</div>
	<div>
		<ul>
			<!-- JSTL é a TAGLIB padrão do Java com JSP -->
			<!-- forEach está dentro da biblioteca CORE, que no caso é chamada de "c" -->
			<c:forEach var="empresa" items="${empresas}">
				<li>${empresa.id}: ${empresa.nome}</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>