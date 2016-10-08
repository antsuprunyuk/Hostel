<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<fmt:setLocale value="${lang}" scope="session"/>
<c:set var="lastPage" value="${pageContext.request.requestURI}" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<fmt:bundle basename="properties.messages" prefix="message.main.">
	<br />
	<div class="orderForm">
	<h3><fmt:message key="orderTitle" /></h3>
	<form name="demandForm" method="POST" action="${pageContext.request.contextPath}/controller">
		<input type="hidden" name="command" value="demand" />
		<input type="hidden" name="hostelId" value="1" />
		<label><fmt:message key="number" /></label>
		<input type="number" name="number" value="1" min="1" max="20"/><br />
		<label><fmt:message key="dateIn" /></label>
		<input type="date" name="dateIn" value="" required="required"/><br />
		<label><fmt:message key="dateOut" /></label>
		<input type="date" name="dateOut" value="" required="required"/><br />
		<input class="btn btn-success" type="submit" value="<fmt:message key="demand" />" />
		
		<c:if test="${noDatesError}">
				<fmt:message key="dateError" />
				<br />
		</c:if>
		<c:if test="${noNumbersError}">
				<fmt:message key="numberError" />
				<br />
		</c:if>
	</form>
	</div>
	<br />
	</fmt:bundle>
	</div>
	<c:import url="fragment/footer.jsp" charEncoding="UTF-8"/>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>