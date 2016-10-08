<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale value="${lang}" scope="session"/>
<c:set var="lastPage" value="${pageContext.request.requestURI}" scope="session"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
	<c:import url="fragment/navigation.jsp" charEncoding="UTF-8"/>
		<div class="mid_content">
	<div class="hotel_sign">
		<h1>- In the Middle of Europe -</h1>
	</div>
 	<fmt:bundle basename="properties.messages" prefix="message.registration.">	<br />
		<form name="registrationForm" method="POST" action="${pageContext.request.contextPath}/controller">
			<input type="hidden" name="command" value="register" />
			<h3><fmt:message key="prompt" /></h3> 
			<fmt:message key="login" /> 
			<br /> <input type="text" name="login" value="" pattern="\w{3,20}" /><br />
			<fmt:message key="password" /> 
			<br /> <input type="password" name="password" value="" /><br />
			<fmt:message key="surname" /> 
			<br /> <input type="text" name="surname" value="" pattern="\w{3,20}" /><br />
			<fmt:message key="name" /> 
			<br /> <input type="text" name="name" value="" pattern="\w{3,20}" /> <br />
			<fmt:message key="email" /> 
			<br /> <input type="email" name="email" value="" /> <br /><br />
			<input class="btn btn-success" type="submit" value="<fmt:message key="register"/>"/>
			<input class="btn btn-warning" type="reset" value="<fmt:message key="reset"/>"/>
		</form> 
	</fmt:bundle>
	</div>
	<c:import url="fragment/footer.jsp" charEncoding="UTF-8"/> 
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>