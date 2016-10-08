<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<fmt:bundle basename="properties.messages" prefix="message.header.">
	
			<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li>			
							<fmt:message key="advertInvite"/>
							<c:choose>
								<c:when test="${not empty user}" >
									${user}
								</c:when>
								<c:otherwise>
									<fmt:message key="guest" />
								</c:otherwise>
							</c:choose>	
						</li>
						<li><a href="/Hostel/jsp/login.jsp"><fmt:message key="login" /></a></li>
						<li><a href="/Hostel/controller?command=logout"><fmt:message key="logout"/></a></li>
						<li><a href="${pageContext.request.contextPath}/controller?command=switch_language&lang=en_US">En</a></li>
						<li><a href="${pageContext.request.contextPath}/controller?command=switch_language&lang=ru_RU">Ru</a></li>
	
					</ul>
				</div>
			</div>
		</nav>
	</fmt:bundle>
	<hr /><br />
</body>
</html>