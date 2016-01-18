<%@page import="by.bsu.kisel.enums.SportType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<c:import url="../jspMenu/menu.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="tittle.usercabinet"/></title>
        <link href="css/mainStyle.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/validation.js"></script> 
    </head>
    <body>
        <c:if test="${not empty greeting}">
           <p class="mainGreeting">
                <spring:message code="message.greeting"/> 
            </p>
        </c:if>
        
        <c:set var="login" value="${login}"/>
        <c:set var="userstatement" value="${userstatement}"/>
           
        <textarea >
	        <spring:message code="textarea.login"  /> ${login}
	        <spring:message code="textarea.number_of_abonements" />  ${userstatement.numberOfAbonements} 
	        <spring:message code="textarea.discount" />  ${userstatement.discountPercent} % 
	        <spring:message code="textarea.cost" />  $ ${userstatement.generalCost} 
        </textarea>
            
        <c:set var="group_sporttypes" value="${group_sporttypes}"/>
        <c:if test="${not empty group_sporttypes}">
        <sf:form id="Delete" method="POST" action="userWantsToDeleteFromGroup.html" commandName="group">
                   
            <div id="UserGroups" align="center">
                <table class="main">
                    <tr>
			<th/>
            <th><spring:message code="table.sporttype"/></th>
			<th><spring:message code="table.group.id"/></th>
			<th><spring:message code="table.calories_burned"/></th>
			<th><spring:message code="table.group.days_of_week"/></th>
			<th><spring:message code="table.group.starttime"/></th>
			<th><spring:message code="table.group.duration" /></th>
            <th><spring:message code="table.group.cost"/></th>
                    </tr>
                    <c:forEach var="group_sporttype" items="${group_sporttypes}">
                        <tr>
				<td><sf:checkbox path="selectedIds"				
								 id="${group_sporttype.id}"                                   
                                 value=" ${group_sporttype.id}"/></td>
                                <td>
            <c:set var="sporttype" value="${group_sporttype.sporttype.sportType}"/>
            <c:if test="${sporttype eq 'FITBALL'}"><spring:message code="sport.fitball"/></c:if>
            <c:if test="${sporttype eq 'EXERCISE_ROOM'}"><spring:message code="sport.exerciseroom"/></c:if>
            <c:if test="${sporttype eq 'AEROBICS'}"><spring:message code="sport.aerobics"/></c:if>
            <c:if test="${sporttype eq 'PILATES'}"><spring:message code="sport.pilates"/></c:if>
            <c:if test="${sporttype eq 'BELLY_DANCE'}"><spring:message code="sport.bellydance"/></c:if>
            <c:if test="${sporttype eq 'DANCE_MIX'}"><spring:message code="sport.dance_mix"/></c:if>
            <c:if test="${sporttype eq 'STRATCHING'}"><spring:message code="sport.straching"/></c:if>
            <c:if test="${sporttype eq 'YOGA'}"><spring:message code="sport.yoga"/></c:if>
                                </td>
				<td>${group_sporttype.id}</td>
                <td>${group_sporttype.sporttype.caloriesburned}</td>
                <td>${group_sporttype.daysOfWeek}</td>
				<td>${group_sporttype.timeStart}</td>
				<td>${group_sporttype.duration}</td>
				<td>${group_sporttype.costAbonement}</td>
                        </tr>
                    </c:forEach>
                </table>
                               
               <spring:message code="label.delete" var="deletebtn"/>
               <input type="submit" class="deletebtn" value="${deletebtn}" onclick="return check('Delete')"/>
               
            </div>
        </sf:form>
        </c:if>
        
    </body>
</html>
