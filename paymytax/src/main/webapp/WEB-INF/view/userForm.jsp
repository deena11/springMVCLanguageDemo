<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
     <link href="/webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Self-Assessment Form</title>
<!-- <script src="https://code.jquery.com/jquery-3.5.0.js"></script> -->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script>
<script type="text/javascript" src = "${pageContext.request.contextPath}/js/main.js" >
</script>
<script type="text/javascript">
function validation()
{
	//alert("hello");
	var flag=false;
	
 	var yearOfAssessment = document.getElementById("yearOfAssessment").value;
 	var constructedYear = document.getElementById("constructedYear").value;
 	var name = document.getElementById("name").value;
 	var email = document.getElementById("eMail").value;
 	var address = document.getElementById("address").value;
 	var area = document.getElementById("area").value;
 	var tax = document.getElementById("totalTax").value;
 	
 		if(name==null || name=="")
 		{
 		document.getElementById("nameError").innerHTML="name cannot be Empty";
 		flag=true;
 		}
 	
 		if(email==null || email=="")
		{
		document.getElementById("eMailError").innerHTML="email cannot be Empty";
		flag=true;
		}
	
 		if(address==null || address=="")
		{
		document.getElementById("addressError").innerHTML="address cannot beEmpty";
		flag=true;
		}
	
 		if(area==null || name=="")
		{
		document.getElementById("areaError").innerHTML="area cannot beEmpty";
		flag=true;
		}
		if(yearOfAssessment<=constructedYear)
		{
			//alert("hello")
			document.getElementById("yearAssessmentError").innerHTML="year of assessment should be Greater than the constructed year or vice versa";
			flag=true;
		}
		
		
 		if(flag==true)
 	      {
 			
 			return false;
 		  }
 		else
 		  {
 			/* $.ajax({
 		        url: "http://localhost:8080/demo",
 		        type: "GET",
 		        success: function (response) {
 		            alert(response);
 		        },
 		        error: function (response) {
 		            alert(response);
 		        }
 		    }); */
 			return true;;
 		  }
 	

}
</script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userForm.css">
</head>
<body>
<blockquote class="blockquote text-center">
  <p class="mb-0"><spring:message code="app.title"></spring:message> </p>
</blockquote>
<div class="container">
        <div>
        <form:form action="/userForm" method="post" modelAttribute="UserTax" onSubmit="return validation()" id="form">
        <table class="table table-hover table-sm">
        <tr>
        
        <td><spring:message code="form.assessment.year"></spring:message></td>
        <td><form:input type="number" name="yearOfAssessment" id="yearOfAssessment" path="yearOfAssessment" required="required"/>
        <span id="yearAssessmentError" class="err-data"><form:errors path="yearOfAssessment" cssClass="err-data" /></span><br></td></tr>
        
        <tr><td><spring:message code="form.name"></spring:message></td>
        <td><form:input type="text" required="required" name="name" id="name" path="name"/>
        <span id="nameError" class="err-data"><form:errors path="name" cssClass="err-data"/></span><br></td></tr>
        
        <tr><td><spring:message code="form.email"></spring:message></td>
        <td><form:input type="text" name="eMail" required="required" id="eMail" path="eMail" cssErrorClass="error" />
        <span id="eMailError" class="err-data"><form:errors path="eMail" cssClass="err-data"/></span><br></td></tr>
        
        <tr><td><spring:message code="form.address"></spring:message></td>
        <td><form:textarea rows="4" cols="50" required="required" name="address" id="address" path="address"/>
        <span id="addressError" class="err-data"><form:errors path="address" cssClass="err-data"/></span><br></td></tr>
        
         <tr><td><spring:message code="form.zone"></spring:message></td><td>
         <select name="zone" id ="zone">
 			 <c:forEach items="${zoneList}" var="zone">
   			 <option value="${zone.id}">
       			 ${zone.zoneName}
    		 </option>
 			 </c:forEach>
		</select>
         <br></td></tr>
         
         <tr><td><spring:message code="form.description"></spring:message></td><td>
         <select name="description" id="description">
 			 <c:forEach items="${descriptionList}" var="descriptionName" varStatus="loop">
   			 <option value="${descriptionName}">
       			 ${descriptionName}
    		 </option>
 			 </c:forEach>
		</select>
         <br></td></tr>
         
         <tr><td><spring:message code="form.status"></spring:message></td><td>
         <select name="status" id="status">
 			 <c:forEach items="${statusList}" var="statusName" varStatus="loop">
   			 <option value="${statusName}">
       			 ${statusName}
    		 </option>
 			 </c:forEach>
		</select>
         <br></td></tr>
         
         <tr><td><spring:message code="form.constructed.year"></spring:message></td>
         <td><input type="number"  name="constructedYear" id="constructedYear" required="required" ><br></td></tr>
        
         <tr><td><spring:message code="form.area"></spring:message></td>
         <td><form:input type="number" name="area" id="area" required="required" path="area"/>
         <form:errors path="area" cssClass="err-data"></form:errors><br></td></tr>
        
         <tr><td><spring:message code="form.taxpayable"></spring:message></td><td><form:input type="number" name="totalTax" id="totalTax" path="totalTax"/><div id="taxError"></div><br></td></tr>
       
        <tr><td><a href ="/greet" class= "btn btn-danger"><spring:message code="form.cancel"></spring:message></a></td>
       
        <td><button type="submit" class="btn btn-primary"><spring:message code="form.pay"></spring:message></button></td></tr>
       
       </table></form:form></div></div>

</body>
</html>