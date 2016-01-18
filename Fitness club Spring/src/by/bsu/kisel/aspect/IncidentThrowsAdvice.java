package by.bsu.kisel.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Controller;
import by.bsu.kisel.constants.PageConstants;

/**
 * This class implements the AOP aspect.
 * 
 * @author Anastasiya Kisel
 * 
 */
@Aspect
@Controller
public class IncidentThrowsAdvice {

	private static Logger logger = Logger.getLogger(IncidentThrowsAdvice.class);

	/**
	 * Provides AOP aspect logic in case controller class throws an exception.	 * 
	 * @param e - exception object
	 * @return String page
	 */
	@AfterThrowing(pointcut = "within(by.bsu.kisel.springmvc.controller.*)", throwing = "e")
	public String handleException(Throwable e) {

		System.out.print("Aspect for exceptions is working - " + e.getMessage());
		return PageConstants.ERROR_PAGE_PATH;
	}

}
