<%@page import="by.bsu.kisel.enums.SportType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../jspMenu/menu.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="tittle.usercabinet"/></title>
        <link href="css/mainStyle.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <c:if test="${not empty greeting}">
           <p class="mainGreeting">
                <fmt:message key="message.greeting"/> 
            </p>
        </c:if>
        
        <c:set var="login" value="${login}"/>
        <c:set var="userstatement" value="${userstatement}"/>
           
        <textarea>
	        <fmt:message key="textarea.login"/> ${login}
	        <fmt:message key="textarea.number_of_abonements"/>  ${userstatement.numberOfAbonements}
	        <fmt:message key="textarea.discount"/>  ${userstatement.discountPercent} %
	        <fmt:message key="textarea.cost"/>  $ ${userstatement.generalCost} 
        </textarea>
            
        <c:set var="group_sporttypes" value="${group_sporttypes}"/>
        <c:if test="${not empty group_sporttypes}">
        <form id="Delete" method="POST" action="Controller">
            <input type="hidden" id="command" name="command" value="delete">        
            <input type="hidden" id="entity" name="entity" value="groups" />
            <div id="UserGroups" align="center">
                <table class="main">
                    <tr>
			<th/>
                        <th><fmt:message key="table.sporttype"/></th>
			<th><fmt:message key="table.group.id"/></th>
			<th><fmt:message key="table.calories_burned"/></th>
			<th><fmt:message key="table.group.days_of_week"/></th>
			<th><fmt:message key="table.group.starttime"/></th>
			<th><fmt:message key="table.group.duration" /></th>
                        <th><fmt:message key="table.group.cost"/></th>
                    </tr>
                    <c:forEach var="group_sporttype" items="${group_sporttypes}">
                        <tr>
				<td><input type="checkbox" 
                                           id="delete_select" 
                                           name="delete_select" 
                                           value=" ${group_sporttype.group.id}"></td>
                                <td>
            <c:set var="sporttype" value="${group_sporttype.sporttype.sportType}"/>
            <c:if test="${sporttype eq 'FITBALL'}"><fmt:message key="sport.fitball"/></c:if>
            <c:if test="${sporttype eq 'EXERCISE_ROOM'}"><fmt:message key="sport.exerciseroom"/></c:if>
            <c:if test="${sporttype eq 'AEROBICS'}"><fmt:message key="sport.aerobics"/></c:if>
            <c:if test="${sporttype eq 'PILATES'}"><fmt:message key="sport.pilates"/></c:if>
            <c:if test="${sporttype eq 'BELLY_DANCE'}"><fmt:message key="sport.bellydance"/></c:if>
            <c:if test="${sporttype eq 'DANCE_MIX'}"><fmt:message key="sport.dance_mix"/></c:if>
            <c:if test="${sporttype eq 'STRATCHING'}"><fmt:message key="sport.straching"/></c:if>
            <c:if test="${sporttype eq 'YOGA'}"><fmt:message key="sport.yoga"/></c:if>
                                </td>
				<td>${group_sporttype.group.id}</td>
                                <td>${group_sporttype.sporttype.caloriesburned}</td>
                                <td>${group_sporttype.group.daysOfWeek}</td>
				<td>${group_sporttype.group.timeStart}</td>
				<td>${group_sporttype.group.duration}</td>
				<td>${group_sporttype.group.costAbonement}</td>
                        </tr>
                    </c:forEach>
                </table>
                               
               <fmt:message key="label.delete" var="deletebtn"/>
               <input type="submit" class="deletebtn" value="${deletebtn}"/>
               
            </div>
        </form>
        </c:if>
        
    </body>
</html>
