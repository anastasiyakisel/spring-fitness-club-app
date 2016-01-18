<%@page import="by.bsu.kisel.enums.SportType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>

<html>
<head>
	<link href="<c:url value="/css/mainStyle.css" />" rel="stylesheet" type="text/css" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title><spring:message code="tittle.login" /></title>
</head>
<body>
	<c:import url="/WEB-INF/jsp/jspf/locale_menu.jsp" />

	<sf:form action="index.html" commandName="user">
		<div class="loginForm">

			<sf:label path="login">
				<spring:message code="signature.login" />
			</sf:label>
			<sf:input path="login" class="login" />

			<sf:label path="password">
				<spring:message code="signature.password" />
			</sf:label>
			<sf:password path="password" class="login" />

			<spring:message code="label.login" var="submit" />
			<input type="submit" value="${submit}" class="login" />
		</div>
	</sf:form>
</body>
</html>
