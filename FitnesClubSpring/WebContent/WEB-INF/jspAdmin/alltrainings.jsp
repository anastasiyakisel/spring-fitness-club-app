<%@page import="by.bsu.kisel.enums.SportType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../jspMenu/menu.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="tittle.alltrainings"/></title>
        <link href="css/mainStyle.css" rel="stylesheet" type="text/css" />  
    </head>
    <body>

        <c:set var="group_sporttypes" value="${group_sporttypes}"/>
        <c:if test="${not empty group_sporttypes}">
        
        <form id="ViewUsers" method="POST" action="Controller">
            <input type="hidden" id="command" name="command" value="view">
            <input type="hidden" id="entity" name="entity" value="user">
            <div id="AllGroups">
            
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
                    <th><fmt:message key="table.group.capacity"/></th>
                    <th><fmt:message key="table.group.people_reqistered"/></th>
                </tr>
                
                <c:forEach var="group_sporttype" items="${group_sporttypes}">
                    <tr>
                        <td><input type="radio" 
                                           id="group_select" 
                                           name="group_select" 
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
                       <td>${group_sporttype.group.capacity}</td>
                       <td>${group_sporttype.group.peopleRegistered}</td>
                   </tr>
                </c:forEach>
                </table>
                
                <fmt:message key="label.people" var="peoplebtn"/>
                <input type="submit" class="peoplebtn" value="${peoplebtn}"/>
            </div>
        </form>
        </c:if>
        
        <c:set var="number" value="${number}"/>
        <c:set var="userStatements" value="${userStatements}"/>
        <c:if test="${not empty userStatements}">
        <form id="ClientsOfGroup" method="POST" action="Controller">
            <input type="hidden" id="command" name="command" value="delete">
            <input type="hidden" id="entity" name="entity" value="user">
            <input type="hidden" id="numberOfGroup" name="numberOfGroup" value="${number}">
            <div id="ClientsOfGroup">
            <table class="main">                
                <caption><fmt:message key="caption.clients"/> ${number} </caption>
                 <tr>
                    <th/>
                    <th><fmt:message key="table.user.id"/></th>
                    <th><fmt:message key="table.user.post"/></th>
                    <th><fmt:message key="table.user.fname"/></th>
                    <th><fmt:message key="table.user.lname"/></th>
                    <th><fmt:message key="table.user.address"/></th>
                    <th><fmt:message key="table.user.telephone" /></th>
                    <th><fmt:message key="table.user.description"/></th>
                    <th><fmt:message key="table.user.abonement"/></th>
                    <th><fmt:message key="table.user.discount"/></th>
                    <th><fmt:message key="table.user.generalcost"/></th>
                </tr>
                <c:forEach var="userStatement" items="${userStatements}">
                    <tr >
                        <td><input type="checkbox" 
                                           id="user_select" 
                                           name="user_select" 
                                           value=" ${userStatement.user.id}"></td>
                       <td>${userStatement.user.id}</td>
                       <td>${userStatement.user.post}</td>
                       <td>${userStatement.user.firstName}</td>
                       <td>${userStatement.user.lastName}</td>
                       <td>${userStatement.user.address}</td>
                       <td>${userStatement.user.telephone}</td>
                       <td>${userStatement.user.description}</td>
                       <td>${userStatement.statement.numberOfAbonements}</td>
                       <td>${userStatement.statement.discountPercent}</td>
                       <td>${userStatement.statement.generalCost}</td>
                    </tr>
                </c:forEach>
            </table>

               <fmt:message key="label.user.delete" var="deleteuserbtn"/>
               <input type="submit" class="peoplebtn" value="${deleteuserbtn}"/>
            </div>
        </form>
        </c:if>
        
        <c:if test="${not empty emptygroup}">
            <p class="emptyGroup">
                    <fmt:message key="message.emptygroup"/> ${number}
            </p>
        </c:if>
        
    </body>
</html>
