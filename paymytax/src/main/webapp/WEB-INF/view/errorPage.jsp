<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
      <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page for tax payment system</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/errorPage.css" type="text/css">
</head>
<body>
<blockquote class="blockquote text-center">
  <p class="mb-0"><spring:message code="app.title"></spring:message> </p>
</blockquote>
<div class="shadow-lg p-3 mb-5  rounded err-head" >
<h1 class="text-center"><spring:message code="app.errorpage.header"></spring:message></h1>
<a href="/greet" class="btn btn-danger err-home">
<spring:message code="app.home"></spring:message></a>
</div>
<div class="container card">
<div class="card-body">
<table class="table">
<tr>
<td>
<h5 class="card-title"><spring:message code="error.status"></spring:message></h5></td>
<td><p class="card-text">${status}</p></td></tr>
<tr>

<tr>
<td>
<h5 class="card-title"><spring:message code="error.status.message"></spring:message></h5></td>
<td><p class="card-text">${statusMessage}</p></td></tr>
<tr>

<tr>
<td>
<h5 class="card-title"><spring:message code="error.message"></spring:message></h5></td>
<td><p class="card-text">${errorMessage}</p></td></tr>
<tr>
</table>
</div></div>
</body>
</html>