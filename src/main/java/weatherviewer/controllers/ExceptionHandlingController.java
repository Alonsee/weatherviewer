package weatherviewer.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	protected String handleException(HttpServletRequest request, HttpServletResponse response,Exception e,Model model) {
		    model.addAttribute("exception", e.getMessage());
		    model.addAttribute("url", request.getRequestURL());
		    
		    //on exception remove cookie
		    Cookie citycookie=new Cookie("city","");
			Cookie providercookie=new Cookie("weatherprovider","");
			citycookie.setMaxAge(0);
			providercookie.setMaxAge(0);
			response.addCookie(citycookie);
			response.addCookie(providercookie);
		    return "error.html";
	}

}
