package com.flp.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebFilter(urlPatterns = {"/*"}, description = "Session Checker Filter")
public class AuthFilter implements Filter {
    private FilterConfig config = null;
 
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        config.getServletContext().log("Initializing SessionCheckerFilter");
    }
 
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain)
            throws ServletException, IOException {
 
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
       
        
        //
        // Check to see if user's session attribute contains an attribute
        // named AUTHENTICATED. If the attribute is not exists redirect
        // user to the login page.
        //
 
/*        if (!request.getRequestURI().endsWith("login.jsp") &&
                request.getSession().getAttribute("AUTHENTICATED") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        }
*/
        if(request.getSession().getAttribute("AUTHENTICATE")!=null){
        if(request.getRequestURI().equals("/FLP/dashboard.htm") && !((boolean) request.getSession().getAttribute("AUTHENTICATE")))
        {
        	response.sendRedirect(request.getContextPath() + "/userLoginPage.htm");
        }
        else if(((boolean) request.getSession().getAttribute("AUTHENTICATE")))
        {
        	//response.sendRedirect(request.getContextPath() + "/dashboard.htm");
        	chain.doFilter(req, res);
        }
        }
        else if(request.getRequestURI().equals("/FLP/dashboard.htm"))
        {
        	response.sendRedirect(request.getContextPath() + "/userLoginPage.htm");
        }
        else
        chain.doFilter(req, res);
    }
 
    public void destroy() {
        config.getServletContext().log("Destroying SessionCheckerFilter");
    }
    
    
}