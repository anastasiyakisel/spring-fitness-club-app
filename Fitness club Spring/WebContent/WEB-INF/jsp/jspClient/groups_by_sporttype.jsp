<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:import url="order.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Groups</title>
        <link href="css/mainStyle.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
    	<c:set var="sportGroups" value="${sportGroups}"/>
        <sf:form id="signUp" method="POST" commandName="group" action="signup.html">            
            <div id="SportGroups" align="center">
                <table class="main">
                    <tr>
			<th/>
			<th><spring:message code="table.group.id"/></th>
			<th><spring:message code="table.group.capacity"/></th>
			<th><spring:message code="table.group.days_of_week"/></th>
			<th><spring:message code="table.group.starttime"/></th>
			<th><spring:message code="table.group.duration" /></th>
            <th><spring:message code="table.group.cost"/></th>
                    </tr>
                
            <c:forEach var="sportGroup" items="${sportGroups}">
                    <tr align="center">
						<td>						
							<sf:checkbox path="selectedIds" id="${sportGroup.id}" value="${sportGroup.id}"/>
						</td>
						<td>${sportGroup.id}</td>
		                <td>${sportGroup.capacity}</td>
						<td>${sportGroup.daysOfWeek}</td>
						<td>${sportGroup.timeStart}</td>
						<td>${sportGroup.duration}</td>
						<td>${sportGroup.costAbonement}</td>
		                <c:if test="${sportGroup.capacity-sportGroup.peopleRegistered==0}">
		                	<td><spring:message code="message.nospace"/></td>
		                </c:if>
                    </tr>
                </c:forEach>    
                </table>

                <spring:message code="label.signup" var="signupbtn"/>
                <input type="submit" class="signupbtn" value="${signupbtn}" onclick="return check('signUp')"/>
            </div>
        </sf:form>
    </body>
</html>
