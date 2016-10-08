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
	<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2350.420342707671!2d27.548781014903195!3d53.90650608010024!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x46dbcfecbc9a9b9b%3A0xa90b192e09a8e5f0!2z0L_RgNC-0YHQvy4g0J_QvtCx0LXQtNC40YLQtdC70LXQuSAzLCDQnNC40L3RgdC6!5e0!3m2!1sru!2sby!4v1475619905859" width="60%" height="450px" frameborder="0" style="border:0" allowfullscreen></iframe>
	</div>
	</fmt:bundle>
	<c:import url="fragment/footer.jsp" charEncoding="UTF-8"/>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>>