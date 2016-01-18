<%@page import="by.bsu.kisel.enums.SportType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="tittle.main" /></title>
<link href="css/mainStyle.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<c:import url="../jspMenu/menu.jsp" />
	<c:set var="login" value="${login}" />
	<p class="mainGreeting">
		<fmt:message key="message.welcome" />
		, ${login} !
	</p>
	<p class="workTime">
		<fmt:message key="message.timetable" />
	</p>
	<c:set var="alldiscounts" value="${alldiscounts}" />
	<c:if test="${not empty alldiscounts}">
		<table class="rightTable">
			<caption>
				<fmt:message key="caption.discount" />
			</caption>
			<tr>
				<th><fmt:message key="table.user.abonement" /></th>
				<th><fmt:message key="table.user.discount" /> %</th>
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
