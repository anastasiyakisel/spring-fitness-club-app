<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="order.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Groups</title>
        <link href="css/mainStyle.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <form id="SignUp" method="POST" action="Controller">
            <input type="hidden" id="command" name="command" value="signup">
            <input type="hidden" id="entity" name="entity" value="groups">
            <div id="SportGroups" align="center">
                <table class="main">
                    <tr>
			<th/>
			<th><fmt:message key="table.group.id"/></th>
			<th><fmt:message key="table.group.capacity"/></th>
			<th><fmt:message key="table.group.days_of_week"/></th>
			<th><fmt:message key="table.group.starttime"/></th>
			<th><fmt:message key="table.group.duration" /></th>
            <th><fmt:message key="table.group.cost"/></th>
                    </tr>
                <c:set var="sportGroups" value="${sportGroups}"/>
                <c:forEach var="sportGroup" items="${sportGroups}">
                    <tr align="center">
				<td><input type="checkbox" 
                                           id="order_selection" 
                                           name="order_selection" 
                                           value="${sportGroup.id}"></td>
				<td>${sportGroup.id}</td>
                                <td>${sportGroup.capacity}</td>
				<td>${sportGroup.daysOfWeek}</td>
				<td>${sportGroup.timeStart}</td>
				<td>${sportGroup.duration}</td>
				<td>${sportGroup.costAbonement}</td>
                                <c:if test="${sportGroup.capacity-sportGroup.peopleRegistered==0}">
                                    <td><fmt:message key="message.nospace"/></td>
                                </c:if>
                    </tr>
                </c:forEach>    
                </table>

                <fmt:message key="label.signup" var="signupbtn"/>
                <input type="submit" class="signupbtn" value="${signupbtn}"/>
            </div>
        </form>
    </body>
</html>
