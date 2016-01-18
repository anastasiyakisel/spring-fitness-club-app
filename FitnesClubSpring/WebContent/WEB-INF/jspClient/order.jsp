<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../jspMenu/menu.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="tittle.order"/></title>
        <link href="css/mainStyle.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/validation.js"></script>
    </head>
    <body>
        <p align="center">
        <font size="4" color="#191970">
        <fmt:message key="message.choicesport"/> 
        </font>
        </p>

            <form name="view"
              method="POST" 
              action="Controller" >
              
              <input type="hidden" name="command" value="view" />
              <input type="hidden" id="entity" name="entity" value="groups" />
        <blockquote>
        <p><font color="#A80000"><input type="radio" name="view" value="1"><fmt:message key="sport.exerciseroom"/></font></p>
        <p><font color="#A80000"/><input type="radio" name="view" value="2"><fmt:message key="sport.aerobics"/> </p>
        <p><font color="#A80000"/><input type="radio" name="view" value="3"><fmt:message key="sport.pilates"/> </p>
        <p><font color="#A80000"/><input type="radio" name="view" value="4"><fmt:message key="sport.yoga"/> </p>
        <p><font color="#A80000"/><input type="radio" name="view" value="5"><fmt:message key="sport.fitball"/> </p>
        <p><font color="#A80000"/><input type="radio" name="view" value="6"><fmt:message key="sport.bellydance"/> </p>
        <p><font color="#A80000"/><input type="radio" name="view" value="7"><fmt:message key="sport.dance_mix"/> </p>
        <p><font color="#A80000"/><input type="radio" name="view" value="8"><fmt:message key="sport.straching"/> </p>
        
        <input Type="submit" Value="<fmt:message key="label.radio"/>"> 
  
        </blockquote>
        </form>
        </body>
</html>
