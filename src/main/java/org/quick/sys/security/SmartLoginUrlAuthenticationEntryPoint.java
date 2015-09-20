package org.quick.sys.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;


public class SmartLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	 public SmartLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	 public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
	        boolean isAjax = isAjaxRequest(request);
	    	if(isAjax){
	        	 response.setContentType("text/html;charset=utf-8");
	             response.setStatus(300);
	        } else{
	            super.commence(request, response, authException);
	        }
	    }
	 
	 public boolean isAjaxRequest(HttpServletRequest request){  
	        String header = request.getHeader("X-Requested-With");  
	        boolean isAjax = "XMLHttpRequest".equals(header) ? true:false;  
	        return isAjax;  
	    } 
}
