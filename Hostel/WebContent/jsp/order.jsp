<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<fmt:setLocale value="${lang}" scope="session"/>
<c:set var="lastPage" value="${pageContext.request.requestURI}" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
	<c:import url="fragment/navigation.jsp" charEncoding="UTF-8"/>
	<fmt:bundle basename="properties.messages" prefix="message.order.">
	<div class="mid_content">
		<div class="hotel_sign">
			<h1>APARTMENT PAGE- In the Middle of Europe -</h1>
		</div>
	<div><c:if test="${empty clientId or clientId eq '0'}">
		<fmt:message key="notRegistred" />
	</c:if></div>
	<div><fmt:message key="approve" /></div><br />
	<div><fmt:message key="reminder" /></div>
	<div><label><fmt:message key="dateIn" />: </label> ${dateIn}</div> 
	<div><label><fmt:message key="dateOut" />: </label> ${dateOut}</div>
	<div><label><fmt:message key="number" />: </label> ${number}</div>
	<form name="bookingForm" method="POST" action="${pageContext.request.contextPath}/controller">
		<input type="hidden" name="command" value="booking" />

		<input type="submit" class="btn btn-success" value="<fmt:message key="booking" />" />
	</form>
	<form name="payingForm" method="POST" action="${pageContext.request.contextPath}/controller">
		<input type="hidden" name="command" value="payment" />

		<input type="submit" class="btn btn-success" value="<fmt:message key="payment" />" />
	</form>
	<form name="refuseForm" method="POST" action="${pageContext.request.contextPath}/controller">
		<input type="hidden" name="command" value="refuse_demand" />

		<input type="submit" class="btn btn-warning" value="<fmt:message key="refuse" />" />
	</form>
	<br />
	</div>
	</fmt:bundle>
	<c:import url="fragment/footer.jsp" charEncoding="UTF-8"/>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>