/**   
 * @Title: SystemService.java 
 * @Package com.yz.base.system.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月10日 上午8:57:29  
 */
package com.yz.base.system.service;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yz.base.resource.pojo.BaseResource;
import com.yz.core.exception.ServiceException;

/** 
 * @ClassName: SystemService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月10日 上午8:57:29 
 *  
 */
public interface SystemService {
	/**
	 * @method: getMenuByParent 
	 * @Description: 根据父菜单code 获取其子菜单
	 * @param: 
	 * @return: void
	 * @throws:
	 */
	public List<BaseResource> getMenuByParent(String parentCode)throws ServiceException;
	
	
}
