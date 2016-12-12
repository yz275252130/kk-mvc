/**   
 * @Title: BaseAuthenticationSuccessHandler.java 
 * @Package com.yz.base.security 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月7日 下午1:55:40  
 */
package com.yz.base.security;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.yz.base.role.service.BaseRoleResourceService;
import com.yz.base.user.pojo.BaseUser;
import com.yz.base.user.pojo.VUserRoleResources;
import com.yz.base.user.service.BaseUserService;
import com.yz.core.datastructure.dto.Dto;
import com.yz.core.datastructure.dto.impl.BaseDto;
import com.yz.core.exception.ServiceException;

/**
 * @ClassName: BaseAuthenticationSuccessHandler
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author yz
 * @date 2016年5月7日 下午1:55:40
 * 
 */
public class BaseAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Resource private BaseRoleResourceService baseRoleResourceService;
	@Resource private BaseUserService baseUserService;
	private BaseUser baseUser;

	private String lastIp;
	private Timestamp lastTime;

	/**
	 * 
	 * 功能描述： 登陆认证成功后调用 创建人：yangzheng
	 * 
	 * 
	 * @param request
	 * @param response
	 * @param authentication
	 * @throws IOException
	 * @throws ServletException
	 */
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String loginIp = getIpAddr(request);
		updateUserDetailsIp(userDetails, loginIp);
		session.setAttribute("USER", baseUser);
		session.setAttribute("lastIP", lastIp);
		if (null != lastTime) {
			session.setAttribute("lastTime", (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(lastTime).toString());
		}
		
		List<VUserRoleResources> authority = baseRoleResourceService.getRoleResourceByUser(baseUser.getCode());
		request.getSession().setAttribute("authority", authority);

		super.onAuthenticationSuccess(request, response, authentication);
	}

	/**
	 * 
	 * 功能描述： 获取访问IP
	 * 
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 
	 * 功能描述： 用户登录成功后修改登录IP及登录时间
	 * 
	 * 
	 * @param userDetails
	 * @throws ServiceException
	 */

	public void updateUserDetailsIp(UserDetails userDetails, String loginIp) throws ServiceException {
		String userCode = ((BaseUserDetail) userDetails).getUserCode();
		baseUser = (BaseUser) baseUserService.getUserByUserCode(userCode);
		lastIp = baseUser.getUserLastIp();
		lastTime = new Timestamp(baseUser.getUserLoginTime().getTime());
		baseUser.setUserLastIp(baseUser.getUserLoginIp());
		
		baseUser.setUserLastTime(lastTime);
		baseUser.setUserLoginIp(loginIp);
		baseUser.setUserLoginTime(new Timestamp(System.currentTimeMillis()));
        Dto dto = new BaseDto();
        dto.put("baseUser", baseUser);
		baseUserService.update(dto);

	}
}
