<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale value="${lang}" scope="session"/>
<c:set var="lastPage" value="${pageContext.request.requestURI}" scope="session"/>
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
 	<fmt:bundle basename="properties.messages" prefix="message.paymentdetails.">	
 		<div class="mid_content">
		<div class="hotel_sign">
			<h1>- In the Middle of Europe -</h1>
		</div>
	<div id="paymentDetails">	
 	<form name="paymentDetailsForm" method="POST" action="${pageContext.request.contextPath}/controller">
 		<input type="hidden" name="command" value="payment_details" />
 		<label><fmt:message key="cardType" /></label>
 		<input type="text" name="type" pattern="(VISA)|(visa)|(MAESTRO)|(maestro)|(MASTER)|(master)" /><br />
 		<label><fmt:message key="cardNumber" /></label>
 		<input type="text" name="number" pattern="\d{16}" /><br />
 		<label><fmt:message key="cardExpirationDate" /></label>
 		<input id="dateDetails" type="text" name="expirationDateMonth" pattern="([1-9])|(1[012])" />
 		<span>/</span>
 		<input id="dateDetails" type="text" name="expirationDateYear" pattern="\d{2}" /><br />
 		<label><fmt:message key="cvv" /></label>
 		<input type="text" name="cvv" pattern="\d{3}" /><br />
		<input type="submit" class="btn btn-success" value=<fmt:message key="payOrder"/> />
	</form>
	<form name="refusePayment" method="POST" action="${pageContext.request.contextPath}/controller">
		<input type="hidden" name="command" value="refuse_payment" />
		<input type="submit" class="btn btn-warning" value=<fmt:message key="refuse" /> />
	</form>
	</div>
	</div>
	</fmt:bundle>
	<c:import url="fragment/footer.jsp" charEncoding="UTF-8"/>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>