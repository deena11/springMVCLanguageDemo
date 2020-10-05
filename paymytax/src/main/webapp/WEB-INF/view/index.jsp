<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
     <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" type="text/css"
href="${pageContext.request.contextPath}/css/home.css">
</head>
<body>
<blockquote class="blockquote text-center">
  <p class="mb-0"><spring:message code="app.title"></spring:message> </p>
</blockquote>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="dropdown">
  <button class="dropbtn">Language</button>
  <div class="dropdown-content">
    <a href="?language=en">English</a>
    <a href="?language=fr">French</a>
    
  </div>
</div>
  
  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="/user"><spring:message code="app.link1"></spring:message></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="/report"><spring:message code="app.link2"></spring:message></a>
    </li>
      </ul>
</nav>
<h1>${message}</h1>


</body>
</html>