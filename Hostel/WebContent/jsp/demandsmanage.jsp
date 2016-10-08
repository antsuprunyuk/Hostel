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
	<c:import url="fragment/adminnavigation.jsp" charEncoding="UTF-8"/>
	<fmt:bundle basename="properties.messages" prefix="message.demandsmanage.">
		<div class="mid_content_admin">
		<div class="hotel_sign">
			<h1>- In the Middle of Europe -</h1>
		</div>
	<h2><fmt:message key="title" /></h2>
		<table class="table table-bordered  table-hover">
			<tr>
				<th><fmt:message key="clientId" /></th>
				<th><fmt:message key="dateInColumn" /></th>
				<th><fmt:message key="dateOutColumn" /></th>
				<th><fmt:message key="placeNumberColumn" /></th>
				<th><fmt:message key="demandTypeColumn" /></th>
				<th><fmt:message key="decisionColumn" /></th>
				<th><fmt:message key="approvement" /></th>
			</tr>
			<c:forEach var="demand" items="${demands}">
				<tr>
					<td>${demand.clientId}</td>
					<td>${demand.dateIn}</td>
					<td>${demand.dateOut}</td>
					<td>${demand.placeNumber}</td>
					<td>${demand.purpose}</td>
					<td>${demand.decision}</td>
					<c:if test="${demand.decision  eq 'AWAITING' or demand.decision  eq 'APPROVED'}">
						<td><form name="disapproveDemand" method="POST" action="${pageContext.request.contextPath}/controller">
							<input type="hidden" name="command" value="disapprove_demand" />
							<input type="hidden" name="demandId" value="${demand.demandId}" />
							<input type="submit" value="<fmt:message key="disapproveDemand" />" />
						</form></td>
					</c:if>
					<c:if test="${demand.decision  eq 'AWAITING' or demand.decision  eq 'DISAPPROVED'}">
						<td><form name="approveDemand" method="POST" action="${pageContext.request.contextPath}/controller">
							<input type="hidden" name="command" value="approve_demand" />
							<input type="hidden" name="demandId" value="${demand.demandId}" />
							<input type="submit" value="<fmt:message key="approveDemand" />" />
						</form></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
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