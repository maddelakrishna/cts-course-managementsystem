<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
            <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> <spring:message code="title"/> </title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div align="center">
<h1> <spring:message  code="welcome"/> </h1>

<a href="/admin/">Admin |</a>
<a href="#">Faculty |</a>
<a href="/student/">Student</a>
</div>
</body>
</html>