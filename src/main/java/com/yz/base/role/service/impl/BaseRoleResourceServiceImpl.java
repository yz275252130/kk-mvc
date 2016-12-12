/**   
 * @Title: RoleResourceServiceImpl.java 
 * @Package com.yz.base.role.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月8日 上午9:24:03  
 */
package com.yz.base.role.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yz.base.resource.pojo.BaseResource;
import com.yz.base.role.pojo.BaseRoleResource;
import com.yz.base.role.service.BaseRoleResourceService;
import com.yz.base.user.pojo.BaseUser;
import com.yz.base.user.pojo.VUserRoleResources;
import com.yz.core.exception.ServiceException;
import com.yz.core.hibernate4.HibernateBaseDAO;

/** 
 * @ClassName: RoleResourceServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月8日 上午9:24:03 
 *  
 */
@Service("BaseRoleResourceService")
public class BaseRoleResourceServiceImpl implements BaseRoleResourceService{
	private static final Logger  log = (Logger) LoggerFactory.getLogger(BaseRoleResourceServiceImpl.class);
	@Resource
	private HibernateBaseDAO dao;
	
	@Override
	public List<BaseRoleResource> getRoleResourceAll() throws ServiceException {
		log.info("***** BaseRoleResourceServiceImpl.getRoleResourceAll method begin*****");
		
		List<BaseRoleResource> roleResourceList = dao.find("from BaseRoleResource where resourceCode in( select code from BaseResource where resourceUrl is not null) and roleCode in (select code from BaseRole where status <> -1)", null); 
		
		log.info("*****  BaseRoleResourceServiceImpl.getRoleResourceAll method end*****");
		
		return roleResourceList;
	}

	@Override
	public List<VUserRoleResources> getRoleResourceByUser(String userCode) throws ServiceException {
		log.info("***** BaseRoleResourceServiceImpl.getRoleResourceByUser method begin*****");
		
		String hql = "from VUserRoleResources where userCode = '" + userCode +"' group by resourceCode order by orderd desc";
		List<VUserRoleResources> list = dao.find(hql, null);
		log.info("*****  BaseRoleResourceServiceImpl.getRoleResourceByUser method end*****");
		
		return list;
	}
	
	@Override
	public List<VUserRoleResources> getRoleResourceByUserAndSourceType(String userCode,Integer sourceType) throws ServiceException {
		log.info("***** BaseRoleResourceServiceImpl.getRoleResourceByUserAndSourceType method begin*****");
		
		String hql = "from VUserRoleResources where userCode = '" + userCode
		+ "' and resourceType='"+sourceType+"'and parentResCode<>'0'  group by resourceCode order by orderd desc";
		List<VUserRoleResources> list = dao.find(hql, null);
		log.info("*****  BaseRoleResourceServiceImpl.getRoleResourceByUserAndSourceType method end*****");
		
		return list;
	}
}
