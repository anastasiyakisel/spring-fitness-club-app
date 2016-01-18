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
        <title><spring:message code="tittle.alltrainings"/></title>
        <link href="css/mainStyle.css" rel="stylesheet" type="text/css" /> 
        <script type="text/javascript" src="js/validation.js"></script> 
    </head>
    <body>

        <c:set var="group_sporttypes" value="${group_sporttypes}"/>
        <c:if test="${not empty group_sporttypes}">
        
        <sf:form id="ViewUsers" method="POST" action="viewUsersOfGroup.html" commandName="group">
            
            <input type="hidden" id="entity" name="entity" value="user">
            <div id="AllGroups" align="center">
            
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
                    <th><spring:message code="table.group.capacity"/></th>
                    <th><spring:message code="table.group.people_reqistered"/></th>
                </tr>
                
                <c:forEach var="group_sporttype" items="${group_sporttypes}">
                    <tr>
                        <td><sf:radiobutton id="group_select" 
                                            path="id" 
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
                       <td>${group_sporttype.capacity}</td>
                       <td>${group_sporttype.peopleRegistered}</td>
                   </tr>
                </c:forEach>
                </table>
                
                <spring:message code="label.people" var="peoplebtn"/>
                <input type="submit" class="peoplebtn" value="${peoplebtn}" onclick="return check('ViewUsers')"/>
            </div>
        </sf:form>
        </c:if>
        
        <c:set var="number" value="${number}"/>
        <c:set var="userStatements" value="${userStatements}"/>
        <c:if test="${not empty userStatements}">
        <sf:form id="ClientsOfGroup" method="POST" action="deleteUsersFromGroup.html" commandName="statement">
            
            <input type="hidden" id="numberOfGroup" name="numberOfGroup" value="${number}">
            <div id="ClientsOfGroup" align="center">
            <table class="main">                
                <caption><spring:message code="caption.clients"/> ${number} </caption>
                 <tr>
                    <th/>
                    <th><spring:message code="table.user.id"/></th>
                    <th><spring:message code="table.user.post"/></th>
                    <th><spring:message code="table.user.fname"/></th>
                    <th><spring:message code="table.user.lname"/></th>
                    <th><spring:message code="table.user.address"/></th>
                    <th><spring:message code="table.user.telephone" /></th>
                    <th><spring:message code="table.user.description"/></th>
                    <th><spring:message code="table.user.abonement"/></th>
                    <th><spring:message code="table.user.discount"/></th>
                    <th><spring:message code="table.user.generalcost"/></th>
                </tr>
                <c:forEach var="userStatement" items="${userStatements}">
                    <tr >
                        <td><sf:checkbox   path="selectedIds" 
                                           id="${userStatement.user.id}" 
                                           value=" ${userStatement.user.id}"/></td>
                       <td>${userStatement.user.id}</td>
                       <td>${userStatement.user.post}</td>
                       <td>${userStatement.user.firstName}</td>
                       <td>${userStatement.user.lastName}</td>
                       <td>${userStatement.user.address}</td>
                       <td>${userStatement.user.telephone}</td>
                       <td>${userStatement.user.description}</td>
                       <td>${userStatement.numberOfAbonements}</td>
                       <td>${userStatement.discountPercent}</td>
                       <td>${userStatement.generalCost}</td>
                    </tr>
                </c:forEach>
            </table>

               <spring:message code="label.user.delete" var="deleteuserbtn"/>
               <input type="submit" class="peoplebtn" value="${deleteuserbtn}" onclick="return check('ClientsOfGroup')"/>
            </div>
        </sf:form>
        </c:if>
        
        <c:if test="${not empty emptygroup}">
            <p class="emptyGroup" align="center">
               <spring:message code="message.emptygroup"/> ${number}
            </p>
        </c:if>
        
    </body>
</html>
