<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>        
        <title><fmt:message key="tittle.order"/></title>
        <link href="css/mainStyle.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/validation.js"></script>
    </head>
    <body>
    	<c:import url="../jspMenu/menu.jsp"/>
    	
        <p class="orderQuest">
        	<fmt:message key="message.choicesport"/> 
        </p>

            <form name="view"
              method="POST" 
              action="Controller" >
              
              <input type="hidden" name="command" value="view" />
              <input type="hidden" id="entity" name="entity" value="groups" />
	        <blockquote>
		        <input type="radio" name="view" value="1" class="radioLine"><fmt:message key="sport.exerciseroom"/>
		        <input type="radio" name="view" value="2" class="radioLine"><fmt:message key="sport.aerobics"/>
		        <input type="radio" name="view" value="3" class="radioLine"><fmt:message key="sport.pilates"/>
		        <input type="radio" name="view" value="4" class="radioLine"><fmt:message key="sport.yoga"/>
		        <input type="radio" name="view" value="5" class="radioLine"><fmt:message key="sport.fitball"/>
		        <input type="radio" name="view" value="6" class="radioLine"><fmt:message key="sport.bellydance"/>
		        <input type="radio" name="view" value="7" class="radioLine"><fmt:message key="sport.dance_mix"/>
		        <input type="radio" name="view" value="8" class="radioLine"><fmt:message key="sport.straching"/>
		        
		        <br/>
		        <input Type="submit" Value="<fmt:message key="label.radio"/>" class="radioBtn" onclick="return check()">   
	        </blockquote>
        </form>
        </body>
</html>
