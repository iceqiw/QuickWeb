package org.quick.sys.security;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;


/*
 * MyUsernamePasswordAuthenticationFilter
 attemptAuthentication
 this.getAuthenticationManager()
 ProviderManager.java
 authenticate(UsernamePasswordAuthenticationToken authRequest)
 AbstractUserDetailsAuthenticationProvider.java
 authenticate(Authentication authentication)
 P155 user = retrieveUser(username, (UsernamePasswordAuthenticationToken) authentication);
 DaoAuthenticationProvider.java
 P86 loadUserByUsername
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//	public static final String VALIDATE_CODE = "validateCode";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		// checkValidateCode(request);

		String username = obtainUsername(request);
		String password = obtainPassword(request);
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		logger.info("password is " +md5.encodePassword(password, null));
		// 验证用户账号与密码是否对应
		username = username.trim();

		// UsernamePasswordAuthenticationToken实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		// Place the last username attempted into HttpSession for views  
	    HttpSession session = request.getSession(false);  
	    //如果session不为空，添加username到session中  
	    if (session != null || getAllowSessionCreation()) {  
	        request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME", TextEscapeUtils.escapeEntities(username));  
	    }  
		// 允许子类设置详细属性
		setDetails(request, authRequest);
		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}
/*
	protected void checkValidateCode(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String sessionValidateCode = obtainSessionValidateCode(session);
		// 让上一次的验证码失效
		session.setAttribute(VALIDATE_CODE, null);
		String validateCodeParameter = obtainValidateCodeParameter(request);
		if (validateCodeParameter == null || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
			throw new AuthenticationServiceException("validateCode.notEquals");
		}
	}

	private String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request.getParameter(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}

	protected String obtainSessionValidateCode(HttpSession session) {
		Object obj = session.getAttribute(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}
*/
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString();
	}

}
