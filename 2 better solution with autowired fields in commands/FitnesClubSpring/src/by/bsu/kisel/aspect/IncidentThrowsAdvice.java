package by.bsu.kisel.aspect;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class IncidentThrowsAdvice {
	
	private static Logger logger = Logger.getLogger(IncidentThrowsAdvice.class);
	
	@Pointcut("execution(* by.bsu.kisel.command.Command.execute(..))")
	public void throwException(){}
	
	@AfterThrowing("throwException()")
	public void handleException(JoinPoint joinPoint, Throwable e){
		System.out.println("Okay - we're in the handler...");

	    Signature signature = joinPoint.getSignature();
	    String methodName = signature.getName();
	    String stuff = signature.toString();
	    String arguments = Arrays.toString(joinPoint.getArgs());
	    logger.info("Write something in the log... We have caught exception in method: "
	        + methodName + " with arguments "
	        + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
	        + e.getMessage(), e);
	}
	
}
