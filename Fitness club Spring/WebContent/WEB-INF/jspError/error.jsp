<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
    	<title><fmt:message key="tittle.error"/></title>
    	<link href="css/errorStyle.css" rel="stylesheet" type="text/css" />
    </head>
    <body class="error">
    	<div>    	
	        <fmt:message key="message.error"/> 
	    
	        <c:choose>
	            <c:when test="${errorMessage eq 'Incorrect login or password'}">
	                <a href="Controller"><fmt:message key="reference.tologin"/></a>
	            </c:when>
	            <c:otherwise>
	                <a href="main.jsp"><fmt:message key="reference.tologin"/></a>
	            </c:otherwise>
	        </c:choose>
    	</div>
    </body>
</html>
