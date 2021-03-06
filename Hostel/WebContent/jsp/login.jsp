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
	<fmt:bundle basename="properties.messages" prefix="message.login.">	<br />
		<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
			<input type="hidden" name="command" value="login" />
			<h3><fmt:message key="prompt" /></h3> 
			<fmt:message key="login" /> <span>(admin1)</span>
			<br /> <input type="text" name="login" value="" /><br />
			<fmt:message key="pass" /> <span>(abc111)</span>
			<br /> <input type="password" name="password" value="" /> <br />
			<c:if test="${loginError}">
				<fmt:message key="loginError" />
				<br />
			</c:if><br />
			<input class="btn btn-success" type="submit" value=<fmt:message key="log_in"/> />
			<input class="btn btn-primary" type="button" value=<fmt:message key="registration" /> onclick="location.href='${pageContext.request.contextPath}/controller?command=registration_page_jump'" />
		</form>
	</fmt:bundle>
	</div>
	<c:import url="fragment/footer.jsp" charEncoding="UTF-8"/>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>