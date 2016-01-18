package by.bsu.kisel.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import by.bsu.kisel.constants.ParameterConstants;


@Aspect
@Component("IncidentThrowsAdvice")
public class IncidentThrowsAdvice {
	
	private static Logger logger = Logger.getLogger(IncidentThrowsAdvice.class);
	
	@Pointcut("execution(* by.bsu.kisel.command.Command.execute(..))")
	public void throwException(){}
	
	@AfterThrowing(pointcut="throwException()", throwing="e")
	public void handleException(JoinPoint joinPoint, Throwable e){
		
	    String arguments = Arrays.toString(joinPoint.getArgs());
	    	    
	    HttpServletRequest request = (HttpServletRequest)joinPoint.getArgs()[0];
	    request.setAttribute(ParameterConstants.PARAMETER_ERROR, e.getMessage());
	    logger.error(e.getMessage());
	    System.out.println(e.getMessage());
	   
	}
	
}
