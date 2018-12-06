package com.flp.aspects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.flp.annotations.AdminAuthentication;
import com.flp.annotations.LoginAuthentication;
import com.flp.annotations.StudentAuthentication;
import com.flp.annotations.SuperAdminAuthentication;
import com.flp.annotations.TeacherAuthentication;
import com.flp.model.User;
import com.flp.model.UserForm;
/**
 * 
 * @author Amresh
 *
 */

@Aspect
@Component
public class FlpAuthenticationAspect {
	
	/**
	 * 
	 * @param pjp
	 * @param loginAuthentication
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(loginAuthentication)")
	public Object flpAuthenticated(ProceedingJoinPoint pjp, LoginAuthentication loginAuthentication) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		if(request.getSession().getAttribute("user") == null) {
		//	System.out.println("servletPath---"+request.getServletContext());
			Object[] args = pjp.getArgs();
			if(args != null && args.length > 0) {
				for(Object arg: args) {					
					if(arg instanceof HttpServletResponse) {
						HttpServletResponse response = (HttpServletResponse) arg;
						response.sendRedirect(request.getContextPath()+"/index.htm");
					return null;
//						
				}
				}
			}
		}
		return pjp.proceed();
	}
	
	/**
	 * 
	 * @param pjp
	 * @param studentAuthentication
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(studentAuthentication)")
	public Object studentAuthenticated(ProceedingJoinPoint pjp, StudentAuthentication studentAuthentication) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		UserForm user = (UserForm)request.getSession().getAttribute("user");
		if(user != null ) {
			if(user.getUserType() != User.USERTYPE_STUDENT && !user.getUserType().equals(User.USERTYPE_DEMO)){
			
			Object[] args = pjp.getArgs();
			if(args != null && args.length > 0) {
				for(Object arg: args) {					
					if(arg instanceof HttpServletResponse) {
						HttpServletResponse response = (HttpServletResponse) arg;
						response.sendRedirect(request.getContextPath()+"/invalid.htm");
					return null;
						
				}
				}
			}
			}
		}
		return pjp.proceed();
	}
/**
 * 
 * @param pjp
 * @param teacherAuthentication
 * @return
 * @throws Throwable
 */
	
	@Around("@annotation(teacherAuthentication)")
	public Object teacherAuthenticated(ProceedingJoinPoint pjp, TeacherAuthentication teacherAuthentication) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		UserForm user = (UserForm)request.getSession().getAttribute("user");
		if(user != null ) {
			if(user.getUserType() != User.USERTYPE_TEACHER){
			
			Object[] args = pjp.getArgs();
			if(args != null && args.length > 0) {
				for(Object arg: args) {					
					if(arg instanceof HttpServletResponse) {
						HttpServletResponse response = (HttpServletResponse) arg;
						response.sendRedirect(request.getContextPath()+"/invalid.htm");
					return null;
						
				}
				}
			}
			}
		}
		return pjp.proceed();
	}
	/**
	 * 
	 * @param pjp
	 * @param adminAuthentication
	 * @return
	 * @throws Throwable
	 */
	
	@Around("@annotation(adminAuthentication)")
	public Object adminAuthenticated(ProceedingJoinPoint pjp, AdminAuthentication adminAuthentication) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		UserForm user = (UserForm)request.getSession().getAttribute("user");
		if(user != null ) {
			if(user.getUserType() != User.USERTYPE_SCHOOL_COORDINATOR){
			
			Object[] args = pjp.getArgs();
			if(args != null && args.length > 0) {
				for(Object arg: args) {					
					if(arg instanceof HttpServletResponse) {
						HttpServletResponse response = (HttpServletResponse) arg;
						response.sendRedirect(request.getContextPath()+"/invalid.htm");
					return null;
						//request.getRequestDispatcher("/invalid.htm");
						//return null; //"redirct:invalid.htm";
				}
				}
			}
			}
		}
		return pjp.proceed();
	}
	/**
	 * 
	 * @param pjp
	 * @param superAdminAuthentication
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(superAdminAuthentication)")
	public Object superAdminAuthenticated(ProceedingJoinPoint pjp, SuperAdminAuthentication superAdminAuthentication) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		UserForm user = (UserForm)request.getSession().getAttribute("user");
		if(user != null ) {
			if(user.getUserType() != User.USERTYPE_ADMIN){
			
			Object[] args = pjp.getArgs();
			if(args != null && args.length > 0) {
				for(Object arg: args) {					
					if(arg instanceof HttpServletResponse) {
						HttpServletResponse response = (HttpServletResponse) arg;
						response.sendRedirect(request.getContextPath()+"/invalid.htm");
					return null;
						//request.getRequestDispatcher("/invalid.htm");
						//return null; //"redirct:invalid.htm";
				}
				}
			}
			}
		}
		return pjp.proceed();
	}

	
}
