<html xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:spring="http://www.springframework.org/tags"
	>

<div id="headerWrapper">
	<div id="logoWrapper">
		<p id="stars">
			<span>&#9733;</span><span>&#9733;</span><span>&#9733;</span><span>&#9733;</span><span>&#9733;</span>
		</p>
	
		<p id="title" contenteditable="true" spellcheck="false">
			<span>100 poods</span> 
			<span><spring:message code="letter.1" /></span>
			<span><spring:message code="letter.2" /></span> 
			<span><spring:message code="letter.3" /></span> 
			<span><spring:message code="letter.4" /></span> 
			<span><spring:message code="letter.5" /></span>
		</p>
		
		<p id="slogan">
			<a href="<%= request.getParameter("logotypeLink") %>">
				Fitness Club Home Page
			</a>
		</p>
		</div>
						
		<div id="localeMenu">
			<a href="?locale=en_US"><img alt="usa" src="<%= request.getParameter("pathToLocaleUSA") %>" width="32" height="32" /></a> 
			<a href="?locale=ru_RU"><img alt="ru" src="<%= request.getParameter("pathToLocaleRUS") %>" width="32" height="32" /></a>
		</div>	
								
</div>

</html>
