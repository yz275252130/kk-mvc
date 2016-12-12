/**   
 * @Title: MenuTag.java 
 * @Package com.yz.base.tag.menu 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月10日 下午2:17:15  
 */
package com.yz.base.tag.menu;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.taglibs.authz.JspAuthorizeTag;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.yz.base.resource.pojo.BaseResource;
import com.yz.base.resource.service.BaseResourceService;
import com.yz.core.application.ApplicationContextHolder;

/** 
 * @ClassName: MenuTag 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月10日 下午2:17:15 
 *  
 */
@Controller
public class MenuTag  extends JspAuthorizeTag{
	

	/**
	 * code标识
	 */
	private String code;
	
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public int doEndTag() throws JspException {
		HttpServletRequest re = (HttpServletRequest) pageContext.getRequest();
		StringBuffer html = new StringBuffer();
		BaseResourceService baseResourceService = (BaseResourceService) ApplicationContextHolder.getBean("BaseResourceService");
		List<BaseResource> menuList = baseResourceService.getRightMenu(code);
		try {
			Collections.reverse(menuList);
			for (int i = 0; i < menuList.size(); i++) {
				if (i==menuList.size()-1) {
					html.append("<li class=\"active\">");
					html.append(menuList.get(i).getResourceName());
					html.append("</li>");
				}else{
					html.append("<li><a href=\"");
					html.append(re.getContextPath());
					html.append(menuList.get(i).getResourceUrl());
					html.append("\">");
					html.append(menuList.get(i).getResourceName());
					html.append("</a></li>");
				}
			}
			

			pageContext.getOut().print(html.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	

}
