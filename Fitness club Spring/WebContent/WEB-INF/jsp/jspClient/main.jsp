<%@page import="by.bsu.kisel.enums.SportType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="tittle.main" /></title>
<link href="css/mainStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:import url="../jspMenu/menu.jsp" />
	<c:set var="login" value="${login}" />
	<p class="mainGreeting">
		<spring:message code="message.welcome" />
		, ${login} !
	</p>
	<p class="workTime">
		<spring:message code="message.timetable" />
	</p>
	<c:set var="alldiscounts" value="${alldiscounts}" />
	<c:if test="${not empty alldiscounts}">
		<table class="rightTable">
			<caption>
				<spring:message code="caption.discount" />
			</caption>
			<tr>
				<th><spring:message code="table.user.abonement" /></th>
				<th><spring:message code="table.user.discount" /> %</th>
			</tr>
			<c:forEach var="discount" items="${alldiscounts}">
				<tr>
					<td>${discount.numberOfAbonements}</td>
					<td>${discount.discountPercent}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
