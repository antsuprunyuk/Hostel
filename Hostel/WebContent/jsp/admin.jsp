<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<fmt:setLocale value="${lang}" scope="session"/>
<c:set var="lastPage" value="${pageContext.request.requestURI}" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
	<c:import url="fragment/adminnavigation.jsp" charEncoding="UTF-8"/>
	<fmt:bundle basename="properties.messages" prefix="message.admin.">
	<div class="mid_content_admin">
		<div class="hotel_sign">
			<h1>- In the Middle of Europe -</h1>
		</div>
		<h2><fmt:message key="title" /></h2>
	</div>
	</fmt:bundle>
	<c:import url="fragment/footer.jsp" charEncoding="UTF-8"/>
	<c:if test="${clientId ne '0'}">
		<jsp:forward page="jsp/main.jsp"/> 
	</c:if>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>