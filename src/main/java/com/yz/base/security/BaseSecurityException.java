package com.yz.base.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.yz.base.user.pojo.BaseUser;

@Controller
@RequestMapping(value="/securityException")
public class BaseSecurityException {
	private final static Logger logger = LoggerFactory.getLogger(BaseSecurityException.class);

	@RequestMapping(value="security")
	public void security(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String targetUrl = request.getContextPath() + "/base/common/forbiddenDialog.jsp";
		
		String ip = request.getRemoteAddr();
		ServletRequestAttributes attr = null;
		String userAccount = null;
		try {
			attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		} catch (IllegalStateException e) {
		}
		if (attr != null) {
			BaseUser baseUser = (BaseUser) attr.getRequest().getSession().getAttribute("USER");
			if(baseUser != null){
				userAccount = baseUser.getUserAccount();
			}
		}
		Object[] values = new Object[]{ip, userAccount};
		logger.warn("IP为{}的用户{}访问了未授权的资源", values);
		
		if (request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equalsIgnoreCase(
						"XMLHttpRequest")) { // ajax 无访问权限处理
			response.sendError(999, userAccount+"访问了未授权的资源");
		} else { 
			response.sendRedirect(targetUrl);
		}

	}
}
