package com.flp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Amresh
 *
 */
@ControllerAdvice
public class ApplicationExceptionHandler extends Throwable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public ApplicationExceptionHandler() {
	}

	@ExceptionHandler(Throwable.class)
	 public ModelAndView exception(Throwable e) {
		e.printStackTrace();
	     return new ModelAndView("error");
	 }
}