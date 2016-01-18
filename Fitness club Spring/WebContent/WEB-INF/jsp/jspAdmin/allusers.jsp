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
        <title><spring:message code="tittle.allusers"/></title>
        <link href="css/mainStyle.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/validation.js"></script> 
    </head>
	<body>
         <c:set var="allUserStatements" value="${allUserStatements}"/> 
         <c:if test="${not empty allUserStatements}"> 
         <sf:form id="ShowUserGroups" method="POST" action="viewAdminGroupsOfUser.html" commandName="statement"> 
             
             <input type="hidden" id="entity" name="entity" value="abonement"> 
             <div id="AllUsers" align="center"> 
                 <table class="main">                 
                 <caption><spring:message code="caption.allusers"/></caption> 
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
                 <c:forEach var="statement" items="${allUserStatements}"> 
                     <tr align="center"> 
                         <td><sf:radiobutton path="id" name="user_id"  
                                             value=" ${statement.user.id}"/></td> 
                        <td>${statement.user.id}</td>
                        <td>${statement.user.post}</td>
                        <td>${statement.user.firstName}</td>
                        <td>${statement.user.lastName}</td>
                        <td>${statement.user.address}</td> 
                        <td>${statement.user.telephone}</td> 
                        <td>${statement.user.description}</td> 
                        <td>${statement.numberOfAbonements}</td> 
                        <td>${statement.discountPercent}</td> 
                        <td>${statement.generalCost}</td> 
                     </tr> 
                 </c:forEach> 
             </table> 
                 
                  <spring:message code="label.show.user.groups" var="show_user_grooupsbtn"/> 
                  <input type="submit" class="peoplebtn" value="${show_user_grooupsbtn}" onclick="return check('ShowUserGroups')"/> 
             </div> 
         	</sf:form>             
         </c:if> 
         
         <br/><br/> 
         
         <c:set var="adminUserGroupSporttypes" value="${adminUserGroupSporttypes}"/> 
         <c:if test="${not empty adminUserGroupSporttypes}"> 
         
         <sf:form id="DeleteUserFromTrainings" method="POST" action="deleteUserFromTrainings.html"  commandName="group"> 

             <div id="userGroups" align="center">             
                 <table class="main">
                     <caption><spring:message code="caption.usergroups"/> ${userId}</caption> 
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
             <c:forEach var="group" items="${adminUserGroupSporttypes}"> 
                         <tr align="center"> 
 				<td><sf:checkbox path="selectedIds" id="${group.id}"  value=" ${group.id}"/></td> 
                                 <td> 
             <c:set var="sporttype" value="${group.sporttype.sportType}"/> 
             <c:if test="${sporttype eq 'FITBALL'}"><spring:message code="sport.fitball"/></c:if> 
             <c:if test="${sporttype eq 'EXERCISE_ROOM'}"><spring:message code="sport.exerciseroom"/></c:if> 
             <c:if test="${sporttype eq 'AEROBICS'}"><spring:message code="sport.aerobics"/></c:if> 
             <c:if test="${sporttype eq 'PILATES'}"><spring:message code="sport.pilates"/></c:if> 
             <c:if test="${sporttype eq 'BELLY_DANCE'}"><spring:message code="sport.bellydance"/></c:if> 
             <c:if test="${sporttype eq 'DANCE_MIX'}"><spring:message code="sport.dance_mix"/></c:if> 
             <c:if test="${sporttype eq 'STRATCHING'}"><spring:message code="sport.straching"/></c:if> 
             <c:if test="${sporttype eq 'YOGA'}"><spring:message code="sport.yoga"/></c:if> 
                                 </td> 
 				<td>${group.id}</td> 
                <td>${group.sporttype.caloriesburned}</td> 
                <td>${group.daysOfWeek}</td> 
 				<td>${group.timeStart}</td> 
 				<td>${group.duration}</td> 
 				<td>${group.costAbonement}</td> 
                         </tr>
                         </c:forEach> 
                 </table> 
                 
                  <spring:message code="label.delete.user.from.group" var="delete_user_from_groupsbtn"/> 
                  <input type="submit" class="peoplebtn" value="${delete_user_from_groupsbtn}" onclick="return check('DeleteUserFromTrainings')"/> 
             </div>  
         </sf:form> 
         </c:if> 
         <c:if test="${not empty passive_user}"> 
             <p align="center"> 
                 <font size="4" color="#191970">
                     <spring:message code="message.user"/> ${userId} <spring:message code="message.nogroups"/> 
                 </font> 
             </p> 
         </c:if> 
         <br/><br/><br/> 
     </body> 
</html>
