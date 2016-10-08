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
	<fmt:bundle basename="properties.messages" prefix="message.bonusbans.">
		<div class="mid_content_admin">
		<div class="hotel_sign">
			<h1>- In the Middle of Europe -</h1>
		</div>
<h2><fmt:message key="title" /></h2>
		<table class="table table-bordered table-hover" id="bonusbans">
			<tr>
				<th><fmt:message key="clientId" /></th>
				<th><fmt:message key="surname" /></th>
				<th><fmt:message key="name" /></th>
				<th><fmt:message key="email" /></th>
				<th><fmt:message key="discount" /></th>
				<th><fmt:message key="discountchanging" /></th>
				<th><fmt:message key="status" /></th>
				<th><fmt:message key="banoperation" /></th>
			</tr>
			<c:forEach var="client" items="${clients}">
				<tr>
					<td>${client.clientId}</td>
					<td>${client.surname}</td>
					<td>${client.name}</td>
					<td>${client.email}</td>
					<td>${client.discount}</td>
					<td><form name="setDiscountForm" method="POST" action="${pageContext.request.contextPath}/controller">
							<input type="hidden" name="command" value="set_discount" />
							<input type="hidden" name="clientId" value="${client.clientId}" />
							<input type="text" name="newdiscount" pattern="\d{1,2}" value="0" />
							<input type="submit" value="<fmt:message key="setdiscount" />" />
						</form>
					</td>
					<td>${client.banned}</td>
					<c:if test="${client.banned  eq 'true'}">
						<td><form name="unbanClientForm" method="POST" action="${pageContext.request.contextPath}/controller">
							<input type="hidden" name="command" value="unban_client" />
							<input type="hidden" name="clientId" value="${client.clientId}" />
							<input type="submit" value="<fmt:message key="unban" />" />
						</form></td>
					</c:if>
					<c:if test="${client.banned  eq 'false'}">
						<td><form name="banClientForm" method="POST" action="${pageContext.request.contextPath}/controller">
							<input type="hidden" name="command" value="ban_client" />
							<input type="hidden" name="clientId" value="${client.clientId}" />
							<input type="submit" value="<fmt:message key="ban" />" />
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