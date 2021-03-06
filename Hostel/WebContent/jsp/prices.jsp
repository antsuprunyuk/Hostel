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
	<fmt:bundle basename="properties.messages" prefix="message.price.">
	<div class="mid_content">
		<div class="hotel_sign">
			<h1>- In the Middle of Europe -</h1>
		</div>
	<br />
		<div id="price_text">
			<h2><fmt:message key="pricePageWelcome" /></h2><br />
			<div>
				<fmt:message key="priceText" />
				<h2><fmt:message key="price" /></h2>
			</div>	
		</div>
	</div>
	</fmt:bundle>
	<c:import url="fragment/footer.jsp" charEncoding="UTF-8"/>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>