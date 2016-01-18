<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
    	<title><spring:message code="tittle.error"/></title>
    	<link href="css/errorStyle.css" rel="stylesheet" type="text/css" />
    </head>
    <body class="error">
    	<div>    	
	        <spring:message code="message.error"/> 
			<a href="logout.html"><spring:message code="reference.tologin"/></a>
    	</div>
    </body>
</html>
