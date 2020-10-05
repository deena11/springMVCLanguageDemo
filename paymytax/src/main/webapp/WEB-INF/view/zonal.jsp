<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
     <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">
/* function reportData(){
	
		var year = document.getElementById("year").value;
		$.get('reportData/'+year,function(res){
			if(res.status=="SUCCESS")
				$('#report').show();
			else
				console.log("FAilure");
			console.log(res);
		});
} */
$(document).ready(function(){
	//$('#report').hide();
	var date = new Date();
	var currentYear = date.getFullYear();
	$('#year').prop('min',1900);
	$('#year').prop('max',currentYear);
	$('#btn').c
});
</script>
</head>
<body style="background-color:beige">
<blockquote class="blockquote text-center">
  <p class="mb-0"><spring:message code="app.title"></spring:message> </p>
</blockquote>
<div style="color: Red; background-color:peachpuff">
<h1 class="text-center"><spring:message code="zonal.reportpage.header"></spring:message></h1>
<a href="/greet" style="text-align:left" class="btn btn-danger">
<spring:message code="app.home"></spring:message></a>
</div>
<div class="conatiner text-centered"  style="text-align:center">
<spring:message code="zonal.reportpage.year"></spring:message>
<form:form action="/reportData" method="get">
<input type="number" name="year" id="year" required="required">
<input type="submit"  class="btn btn-primary" id="btn" value = <spring:message code="form.submit"></spring:message>>
</form:form>
</div>
<div id="report" class="container text-centered">
<h1 style="color:blue">${message}</h1>
<table class="table table-bordered table-sm table-hover" >
<thead class="thead-dark">
    <tr>
      <th scope="col">Zone</th>
      <th scope="col">Status</th>
      <th scope="col">Cost</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="map" items="${results}">
  <tr>
  <td rowspan="2">${map.key}</td>
  <c:forEach var="innerMap" items="${map.value}">
  <td>${innerMap.key}</td>
  <td>&#8377;${innerMap.value}</td>
  </tr>
  </c:forEach>
 
  </c:forEach>
  
  </tbody>
</table>
</div>

</body>
</html>