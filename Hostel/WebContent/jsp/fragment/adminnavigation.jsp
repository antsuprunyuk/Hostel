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
	<fmt:bundle basename="properties.messages" prefix="message.adminnavigation.">
		<nav class="navbar navbar-inverse navbar-fixed-top">
				<div class="container">
					<div class="navbar-collapse collapse">
						<ul class="nav navbar-nav">
							<li><a href="${pageContext.request.contextPath}/controller?command=demands_manage"><fmt:message key="demands" /></a></li>
							<li><a href="${pageContext.request.contextPath}/controller?command=bonus_bans"><fmt:message key="bonusbans" /></a></li>	
						</ul>	
						<ul class="nav navbar-nav navbar-right">	
							<li class="active">			
								<a href=""><fmt:message key="advertInvite"/>
								<c:choose>
									<c:when test="${not empty user}" >
										${user}
									</c:when>
									<c:otherwise>
										<fmt:message key="guest" />
									</c:otherwise>
								</c:choose></a>	
							</li>
							<c:if test="${empty clientId}">
								<li><a href="/Hostel/controller?command=login_page_jump"><fmt:message key="login" /></a></li>
							</c:if>
							<c:if test="${not empty clientId}">
								<li><a href="/Hostel/controller?command=logout"><fmt:message key="logout"/></a></li>
							</c:if>
							<li><a href="${pageContext.request.contextPath}/controller?command=switch_language&lang=en_US">Eng</a></li> 
							<li><a href="${pageContext.request.contextPath}/controller?command=switch_language&lang=ru_RU">Rus</a></li>

						</ul>
					</div>
				</div>
			</nav>
	</fmt:bundle>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>