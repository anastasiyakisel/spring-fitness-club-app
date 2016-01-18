<%@page import="by.bsu.kisel.enums.SportType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<c:set var="loc" value="${actionBean.context.locale}" />
    <c:if test="${param.loc != null}">
        <fmt:setLocale value="${param.loc}" scope="session" />
    </c:if>
    <fmt:setBundle basename="messages" var="messagesBundle" scope="session"/>
    
    <html>
    <head>
    	<link href="css/mainStyle.css" rel="stylesheet" type="text/css" />  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title><fmt:message key="tittle.login"/></title>
    </head>
    <body>  
        <c:import url="/WEB-INF/jspf/locale_menu.jsp"/>
    <form name="login" method="POST" action="Controller" >
        <div class="loginForm">
            <input type="hidden" name="command" value="login" style="hidden: right"/>
            
            <fmt:message key="signature.login" />
            <input type="text" name="login" value="" class="login"/>
            
            <fmt:message key="signature.password"/>
            <input type="password" name="password" value="" class="login"/>
               
            <fmt:message key="label.login" var="submit"/>
            <input type="submit" value="${submit}" class="login"/>
        </div>
     </form>
    </body>
</html>

