<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>        
        <title><spring:message code="tittle.order"/></title>
        <link href="css/mainStyle.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/validation.js"></script>
    </head>
    <body>
    	<c:import url="../jspMenu/menu.jsp"/>
    	
        <p class="orderQuest">
        	<spring:message code="message.choicesport"/> 
        </p>

        <sf:form id="viewForm" method="POST"  action="viewGroupsOfSporttype.html" commandName="sporttype">
              
	        <blockquote>
		        <p class="radioLine"><sf:radiobutton name="view" path="id" value="1" /> <spring:message code="sport.exerciseroom"/></p>
		        <p class="radioLine"><sf:radiobutton name="view" path="id" value="2" /> <spring:message code="sport.aerobics"/></p>
		        <p class="radioLine"><sf:radiobutton name="view" path="id" value="3" /> <spring:message code="sport.pilates"/></p>
		        <p class="radioLine"><sf:radiobutton name="view" path="id" value="4" /> <spring:message code="sport.yoga"/></p>
		        <p class="radioLine"><sf:radiobutton name="view" path="id" value="5" /> <spring:message code="sport.fitball"/></p>
		        <p class="radioLine"><sf:radiobutton name="view" path="id" value="6" /> <spring:message code="sport.bellydance"/></p>
		        <p class="radioLine"><sf:radiobutton name="view" path="id" value="7" /> <spring:message code="sport.dance_mix"/></p>
		        <p class="radioLine"><sf:radiobutton name="view" path="id" value="8" /> <spring:message code="sport.straching"/></p>
		        
		        <br/>
		        <input type="submit" value="<spring:message code="label.radio"/>" class="radioBtn" onclick="return check('viewForm')">   
	        </blockquote>
        </sf:form>
        </body>
</html>
