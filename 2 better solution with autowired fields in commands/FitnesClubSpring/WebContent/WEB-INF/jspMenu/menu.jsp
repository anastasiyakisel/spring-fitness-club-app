<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../jspf/locale_menu.jsp"/>
<link rel="stylesheet" href="css/menuStyle.css" type="text/css" /> 
<html>
<c:set var="login" value="${login}"/>
<c:if test="${login!=null}">
    <c:if test="${param.loc != null}">
        <fmt:setLocale value="${param.loc}" scope="session" />
    </c:if>
<div id="tabs10">    
     <ul id="menu" style="margin-bottom: 10px">
     
        <li>
            <a href="Controller?command=view&entity=discount"><span><fmt:message key="menu.main"/></span></a>
        </li>
        
	<li>
            <a href="Controller?command=view&entity=order"><span><fmt:message key="menu.order"/></span></a>
        </li>
        
        <c:if test="${userrole==1}">
            <li>
                <a href="Controller?command=view&entity=admin"><span><fmt:message key="menu.alltrainings"/></span></a>
            </li>
            
            <li>
                <a href="Controller?command=view&entity=users"><span><fmt:message key="menu.allusers"/></span></a>
            </li>
        </c:if>
            
        <li>
            <a href="Controller?command=view&entity=statement"><span><fmt:message key="menu.usercabinet"/></span></a>
        </li>
        
        <li>
           <a href="Controller?command=logout"><span><fmt:message key="menu.logout"/></span></a>
        </li>
</ul>
</div>
</c:if> 
<html>
    