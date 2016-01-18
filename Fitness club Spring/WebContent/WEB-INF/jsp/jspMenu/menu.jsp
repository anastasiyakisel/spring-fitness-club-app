<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:import url="../jspf/locale_menu.jsp"/>
<link rel="stylesheet" href="css/menuStyle.css" type="text/css" /> 
<html>
<c:set var="login" value="${login}"/>
<c:if test="${login!=null}">

<div id="tabs10">    
     <ul id="menu" style="margin-bottom: 10px">
     
        <li>
            <a href="home.html"><span><spring:message code="menu.main"/></span></a>
        </li>
        
	    <li>
            <a href="order.html"><span><spring:message code="menu.order"/></span></a>
        </li>
        
        <c:if test="${userrole==1}">
            <li>
                <a href="viewAllTrainings.html"><span><spring:message code="menu.alltrainings"/></span></a>
            </li>
            
            <li>
                <a href="viewAllUserStatements.html"><span><spring:message code="menu.allusers"/></span></a>
            </li>
        </c:if>
            
        <li>
            <a href="usercabinet.html"><span><spring:message code="menu.usercabinet"/></span></a>
        </li>
        
        <li>
           <a href="logout.html"><span><spring:message code="menu.logout"/></span></a>
        </li>
</ul>
</div>
</c:if> 
<html>
    