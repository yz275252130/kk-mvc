/**   
 * @Title: SystemController.java 
 * @Package com.yz.base.system.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月10日 上午8:57:09  
 */
package com.yz.base.system.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yz.base.resource.pojo.BaseResource;
import com.yz.base.system.service.SystemService;
import com.yz.base.utils.FileUtil;
import com.yz.core.web.BaseController;

/** 
 * @ClassName: SystemController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月10日 上午8:57:09 
 *  
 */
@Controller
public class SystemController extends BaseController{
	private static final Logger  logger = (Logger) LoggerFactory.getLogger(SystemController.class);
	@Autowired
	private SystemService systemService;
	@RequestMapping("/login")
	public ModelAndView login(){
		logger.info("***** SystemController.login method begin*****");
		
		ModelAndView result =  new ModelAndView("/login");
		
		logger.info("***** SystemController.login method end*****");
		return result;
	}
	
	@RequestMapping("/index")
	public ModelAndView index(){
		logger.info("***** SystemController.index method begin*****");
		
		ModelAndView result =  new ModelAndView("/index");
		//获取横向菜单
		List<BaseResource> menuList = systemService.getMenuByParent("0");
		result.addObject("menuList", menuList);
		//获取首页菜单
		List<BaseResource> homemenuList = systemService.getMenuByParent("code0");
		result.addObject("homemenuList", homemenuList);
		
		logger.info("***** SystemController.index method end*****");
		return result;
	}
	
	@RequestMapping(value="/getHomeMenu")
	public ModelAndView getHomeMenu(String code) {
		logger.info("***** SystemController.getHomeMenu method begin*****");
		ModelAndView result =  new ModelAndView("/base/system/menu");
		
		List<BaseResource> menuList = systemService.getMenuByParent(code);
		result.addObject("menuList", menuList);
		logger.info("***** SystemController.getHomeMenu method end*****");
		return result;
	}
	
	
	@RequestMapping(value="/getSystemConfigureMenu")
	public ModelAndView getSystemConfigureMenu(String code) {
		logger.info("***** SystemController.getHomeMenu method begin*****");
		ModelAndView result =  new ModelAndView("/base/system/menu");
		
		List<BaseResource> menuList = systemService.getMenuByParent(code);
		result.addObject("menuList", menuList);
		logger.info("***** SystemController.getHomeMenu method end*****");
		return result;
	}
	
	@RequestMapping("/sessiontimeout")
	public void sessionTimeout(Integer error, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		logger.info("***** SystemController.sessiontimeout method begin*****");
		String targetUrl = request.getContextPath() + "/login?error="+error;
		
		System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeee"+targetUrl);
		if (request.getHeader("x-requested-with") != null
				&& request.getHeader("x-requested-with").equalsIgnoreCase(
						"XMLHttpRequest")) { // ajax 超时处理
			response.setHeader("sessionstatus", "timeout");
			response.setHeader("targetUrl", targetUrl);
		} else { // http 超时处理
			response.sendRedirect(targetUrl);
		}
		logger.info("***** SystemController.sessiontimeout method end*****");
	}
}
