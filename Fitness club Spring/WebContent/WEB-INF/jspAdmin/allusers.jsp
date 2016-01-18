<%@page import="by.bsu.kisel.enums.SportType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../jspMenu/menu.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="tittle.allusers"/></title>
        <link href="css/mainStyle.css" rel="stylesheet" type="text/css" />
    </head>
	<body>
         <c:set var="allUserStatements" value="${allUserStatements}"/> 
         <c:if test="${not empty allUserStatements}"> 
         <form id="ShowUserGroups" method="POST" action="Controller"> 
             <input type="hidden" id="command" name="command" value="view">
             <input type="hidden" id="entity" name="entity" value="abonement"> 
             <div id="AllUsers" align="center"> 
                 <table class="main">                 
                 <caption><fmt:message key="caption.allusers"/></caption> 
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
                 <c:forEach var="userStatement" items="${allUserStatements}"> 
                     <tr align="center"> 
                         <td><input type="radio" 
                                            id="user_select"  
                                            name="userid_select"  
                                            value=" ${userStatement.user.id}"></td> 
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
                 
                  <fmt:message key="label.show.user.groups" var="show_user_grooupsbtn"/> 
                  <input type="submit" class="peoplebtn" value="${show_user_grooupsbtn}"/> 
             </div> 
         	</form>             
         </c:if> 
         </br></br> 
         <c:set var="adminUserGroupSporttypes" value="${adminUserGroupSporttypes}"/> 
         <c:if test="${not empty adminUserGroupSporttypes}"> 
         <form id="DeleteUserFromGroups" method="POST" action="Controller"> 
             <input type="hidden" id="command" name="command" value="delete"> 
             <input type="hidden" id="entity" name="entity" value="admin"> 
             <div id="userGroups" align="center">             
                 <table class="main">
                     <caption><fmt:message key="caption.usergroups"/> ${userId}</caption> 
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
                     <c:forEach var="group_sporttype" items="${adminUserGroupSporttypes}"> 
                         <tr align="center"> 
 				<td><input type="checkbox"  
                                            id="delete_group_select"  
                                            name="delete_group_select"  
                                            value=" ${group_sporttype.id}"></td> 
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
 				<td>${group_sporttype.id}</td> 
                <td>${group_sporttype.sporttype.caloriesburned}</td> 
                <td>${group_sporttype.daysOfWeek}</td> 
 				<td>${group_sporttype.timeStart}</td> 
 				<td>${group_sporttype.duration}</td> 
 				<td>${group_sporttype.costAbonement}</td> 
                         </tr> 
                         </c:forEach> 
                 </table> 
                 
                  <fmt:message key="label.delete.user.from.group" var="delete_user_from_groupsbtn"/> 
                  <input type="submit" class="peoplebtn" value="${delete_user_from_groupsbtn}"/> 
             </div> 
         </form> 
         </c:if> 
         <c:if test="${not empty passive_user}"> 
             <p align="center"> 
                 <font size="4" color="#191970">
                     <fmt:message key="message.user"/> ${userId} <fmt:message key="message.nogroups"/> 
                 </font> 
             </p> 
         </c:if> 
         </br></br></br> 
     </body> 
</html>
