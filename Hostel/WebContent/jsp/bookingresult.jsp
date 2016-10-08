<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale value="${lang}" scope="session"/>
<c:set var="lastPage" value="${pageContext.request.requestURI}" scope="session"/>
<html lang="en">
<head>
    <meta charset="utf-8">
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
 	<fmt:bundle basename="properties.messages" prefix="message.bookingresult.">	<br />
		<h2><fmt:message key="title" /></h2>
		<table class="table table-bordered  table-hover">
			<tr>
				<th><fmt:message key="dateInColumn" /></th>
				<th><fmt:message key="dateOutColumn" /></th>
				<th><fmt:message key="placeNumberColumn" /></th>
				<th><fmt:message key="demandTypeColumn" /></th>
				<th><fmt:message key="decisionColumn" /></th>
				<th><fmt:message key="rejection" /></th>
			</tr>
			<c:forEach var="demand" items="${demands}">
				<tr>
					<td>${demand.dateIn}</td>
					<td>${demand.dateOut}</td>
					<td>${demand.placeNumber}</td>
					<td>${demand.purpose}</td>
					<td>${demand.decision}</td>
					<c:if test="${demand.decision  eq 'AWAITING'}">
						<td><form name="rejectDemand" method="POST" action="${pageContext.request.contextPath}/controller">
							<input type="hidden" name="command" value="reject_demand" />
							<input type="hidden" name="demandId" value="${demand.demandId}" />
							<input type="submit" value="<fmt:message key="rejectDemand" />" />
						</form></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</fmt:bundle>
	</div>
	<c:import url="fragment/footer.jsp" charEncoding="UTF-8"/> 
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<c:if test="${empty clientId}">
		<jsp:forward page="jsp/main.jsp"/> 
	</c:if>
</body>
</html>