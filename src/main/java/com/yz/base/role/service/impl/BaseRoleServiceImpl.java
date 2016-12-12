/**   
 * @Title: BaseRoleServiceImpl.java 
 * @Package com.yz.base.role.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月26日 下午3:13:30  
 */
package com.yz.base.role.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yz.base.resource.pojo.BaseResource;
import com.yz.base.resource.service.BaseResourceService;
import com.yz.base.resource.service.impl.BaseResourceServiceImpl;
import com.yz.base.role.pojo.BaseRole;
import com.yz.base.role.pojo.BaseRoleResource;
import com.yz.base.role.pojo.BaseUserRole;
import com.yz.base.role.service.BaseRoleService;
import com.yz.base.security.FilterInvocationSecurityMetadataSourceImpl;
import com.yz.core.datastructure.dto.Dto;
import com.yz.core.datastructure.page.Pagination;
import com.yz.core.datastructure.response.StatusCode;
import com.yz.core.exception.ServiceException;
import com.yz.core.hibernate4.Finder;
import com.yz.core.hibernate4.HibernateBaseDAO;
import com.yz.core.utils.CoreUtils;

/** 
 * @ClassName: BaseRoleServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月26日 下午3:13:30 
 *  
 */

@Service("BaseRoleService")
public class BaseRoleServiceImpl implements BaseRoleService{
	private static final Logger logger = (Logger) LoggerFactory.getLogger(BaseRoleServiceImpl.class);
	@Resource
	private HibernateBaseDAO dao;
	@Resource
	private BaseResourceService baseResourceService;
	@Resource
	private FilterInvocationSecurityMetadataSourceImpl securityMetadataSource ;
	
	@Override
	public Pagination getList(Dto dto)throws ServiceException{
		logger.info("***** BaseRoleServiceImpl.getList method begin*****");
			String pCode = dto.getAsString("pCode");
			String keyword = dto.getAsString("keyword");
			Integer pageNo = dto.getAsInteger("pageNo");
			Integer pageSize =dto.getAsInteger("pageSize");
			String orderField = dto.getAsString("orderField");
			String orderDirection = dto.getAsString("orderDirection");
			
			
			String hql = "from BaseRole where status=1";
			Map<String, Object> paramMap = null;
			if (!StringUtils.isEmpty(keyword)) {
				paramMap = new HashMap<String, Object>(1);
				hql += " and roleName like :keyword";
				paramMap.put("keyword", "%" + keyword.trim() + "%");
			}
			
			hql += " order by " + orderField+" "+orderDirection;
			
			Finder finder = Finder.create(hql);
			if(paramMap != null && paramMap.size() > 0){
				finder.setParams(paramMap);
			}
			logger.info("*****  BaseRoleServiceImpl.getList method end*****");
			return dao.find(finder, pageNo, pageSize);
	}

	@Override
	public void save(Dto dto) throws ServiceException {
		logger.info("***** BaseRoleServiceImpl.save method begin*****");
		BaseRole baseRole = (BaseRole) dto.get("baseRole");
		baseRole.setStatus(1);
		baseRole.setCode(CoreUtils.getCode("res"));
		dao.save(baseRole);
		logger.info("*****  BaseRoleServiceImpl.save method end*****");
		
	}
	@Override
	public BaseRole get(Dto dto) throws ServiceException {
		logger.info("***** BaseRoleServiceImpl.get method begin*****");
		Integer id = dto.getAsInteger("id");
		
		BaseRole baseRole = (BaseRole) dao.load(BaseRole.class, id);
		
		logger.info("*****  BaseRoleServiceImpl.get method end*****");
		return baseRole;
	}

	@Override
	public void update(Dto dto) throws ServiceException {
		logger.info("***** BaseRoleServiceImpl.update method begin*****");
		BaseRole baseRole = (BaseRole) dto.get("baseRole");
		dao.update(baseRole);
		logger.info("*****  BaseRoleServiceImpl.update method end*****");
		
	}

	@Override
	public Dto delete(Dto dto) throws ServiceException {
		logger.debug("***** BaseRoleServiceImpl.delete() method begin*****");
		
		String ids = dto.getAsString("ids");
		 
		//判断是否 有子节点
		String hql1 = "from BaseUserRole where roleCode in (select code from BaseRole where status=1 and  id in ("+ids+"))";
		
		Integer result = dao.countQueryResult(Finder.create(hql1), null);
		if (result>0) {
			dto.setResultCode(StatusCode.M_Failure);
			dto.setResultMsg("该角色正被用户使用！");
			
			return dto;
		}
		String hql = "update from BaseRole set status = -1 where id in ("+ids+"))";
		dao.update(hql,null);
		
		logger.debug("***** BaseRoleServiceImpl.delete() method end*****");
		
		dto.setResultCode(StatusCode.M_Success);
		dto.setResultMsg(StatusCode.getName(StatusCode.M_Success));
		return dto;
	}
	
	
	@Override
	public Dto getRoleRes(Dto dto) throws ServiceException {
		logger.info("***** BaseRoleServiceImpl.getMenu method begin*****");
		String roleCode = dto.getAsString("code");
		//获取角色 拥有的权限
		String hql = "select resourceCode from BaseRoleResource where roleCode='"+roleCode+"'" ;
		List<String> resList = dao.find(hql,null);

		
		JSONObject root = new JSONObject();
		root.put("text", "HOME");
		root.put("code", "0");
		root.put("pcode", "0");
		root.put("href", "");
		root.put("selectable", true);
		root.put("tags", "['available']");
		if (resList.contains("0")) {
			JSONObject state = new JSONObject();
			state.put("checked", true);
			root.put("state", state);
		}
		
		List<BaseResource> baseResources = baseResourceService.getResourceByPcode("0");
		JSONArray array = new JSONArray();
		for (BaseResource baseResource : baseResources) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("text", baseResource.getResourceName());
			jsonObject.put("code", baseResource.getCode());
			jsonObject.put("pcode", baseResource.getParentResCode());
			if (resList.contains(baseResource.getCode())) {
				JSONObject state = new JSONObject();
				state.put("checked", true);
				jsonObject.put("state", state);
			}
			List<BaseResource> baseResources2 = baseResourceService.getResourceByPcode(baseResource.getCode());
			JSONArray jsonArray = new JSONArray();
			for (BaseResource baseResource2 : baseResources2) {
					JSONObject jsonObject2 = new JSONObject();
					jsonObject2.put("text", baseResource2.getResourceName());
					jsonObject2.put("code", baseResource2.getCode());
					jsonObject2.put("pcode", baseResource2.getParentResCode());
					jsonObject2.put("href", "");
					if (resList.contains(baseResource2.getCode())) {
						JSONObject state = new JSONObject();
						state.put("checked", true);
						jsonObject2.put("state", state);
					}
					jsonArray.add(jsonObject2);
					List<BaseResource> baseResources3 = baseResourceService.getResourceByPcode(baseResource2.getCode());
					JSONArray jsonArray2 = new JSONArray();
					for (BaseResource baseResource3 : baseResources3) {
						JSONObject jsonObject3 = new JSONObject();
						jsonObject3.put("text", baseResource3.getResourceName());
						jsonObject3.put("code", baseResource3.getCode());
						jsonObject3.put("pcode", baseResource3.getParentResCode());
						jsonObject3.put("href", "");
						if (resList.contains( baseResource3.getCode())) {
							JSONObject state = new JSONObject();
							state.put("checked", true);
							jsonObject3.put("state", state);
						}
						jsonArray2.add(jsonObject3);
					}
					jsonObject2.put("nodes", jsonArray2);
			}
			jsonObject.put("nodes", jsonArray);
			array.add(jsonObject);
		}
		root.put("nodes", array);
		dto.put("treeData", root);
		dto.put("roleRes", resList);
		
		logger.info("*****  BaseRoleServiceImpl.getMenu method end*****");
		// TODO Auto-generated method stub
		return dto;
	}
	
	
	@Override
	public void updateRoleRes(Dto dto) throws ServiceException {
		logger.info("***** BaseRoleServiceImpl.updateRoleRes method begin*****");
		String roleCode = dto.getAsString("roleCode");
		String res = dto.getAsString("res");
		String[] rStrings = res.split(",");
		//删除原有 权限数据
		String hql = "delete from BaseRoleResource where roleCode='"+roleCode+"'";
		dao.delete(hql, null);
			for (String rstring : rStrings) {
				if (StringUtils.isNotEmpty(rstring)) {
					BaseRoleResource baseRoleResource = new BaseRoleResource();
					baseRoleResource.setResourceCode(rstring);
					baseRoleResource.setRoleCode(roleCode);
					baseRoleResource.setStatus(1);
					dao.save(baseRoleResource);
				}
			}
			securityMetadataSource.refreshResource();
		logger.info("*****  BaseRoleServiceImpl.updateRoleRes method end*****");
		
	}
	
}
