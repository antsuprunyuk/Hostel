<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error page</title>
</head>
<body>
	Request from ${pageContext.errorData.requestURI} is failed
	<br />
	Servlet name or type: ${pageContext.errorData.servletName}
	<br />
	Status code: ${pageContext.errorData.statusCode}
	<br />
	Exception: ${pageContext.errorData.throwable} 
	<br />
	<form name="crashRecoveryForm" method="POST" action="controller" >
			<input type="hidden" name="command" value="crash_recovery"/>
			<input type="submit" value="Return to previous page"/>
	</form>
	<jsp:forward page="/jsp/main.jsp"></jsp:forward>
	</body>
</html>