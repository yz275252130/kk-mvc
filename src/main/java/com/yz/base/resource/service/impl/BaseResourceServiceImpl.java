/**   
 * @Title: ResourceServiceImpl.java 
 * @Package com.yz.base.role.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月8日 上午9:27:18  
 */
package com.yz.base.resource.service.impl;

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
import com.yz.base.security.FilterInvocationSecurityMetadataSourceImpl;
import com.yz.core.datastructure.dto.Dto;
import com.yz.core.datastructure.page.Pagination;
import com.yz.core.datastructure.response.StatusCode;
import com.yz.core.exception.ServiceException;
import com.yz.core.hibernate4.Finder;
import com.yz.core.hibernate4.HibernateBaseDAO;
import com.yz.core.utils.CoreUtils;

/**
 * @ClassName: ResourceServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author yz
 * @date 2016年5月8日 上午9:27:18
 * 
 */
@Service("BaseResourceService")
public class BaseResourceServiceImpl implements BaseResourceService {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(BaseResourceServiceImpl.class);
	@Resource
	private HibernateBaseDAO dao;
	@Resource private FilterInvocationSecurityMetadataSourceImpl securityMetadataSource ;

	@Override
	public List<BaseResource> getResourceAll() throws ServiceException {
		logger.info("***** BaseResourceServiceImpl.getResourceAll method begin*****");

		List<BaseResource> baseResourceList = (List<BaseResource>) dao.find("from BaseResource where resourceUrl is not null ", null);
		logger.info("*****  BaseResourceServiceImpl.getResourceAll method end*****");
		return baseResourceList;
	}

	@Override
	public List<BaseResource> getPresourceByCode(String code) throws ServiceException {
		logger.info("***** BaseResourceServiceImpl.getPresourceByCode method begin*****");
		List<BaseResource> list = new ArrayList<BaseResource>();
		//获取自身信息
		
		
		BaseResource resourcelast = (BaseResource) dao.findUniqueByProperty(BaseResource.class, "code", code);
		list.add(resourcelast);
		while(!resourcelast.getParentResCode().equals(0)){
			String hql = "from BaseResource where status=1 and code='"+resourcelast.getParentResCode()+"'";
			BaseResource baseResource = (BaseResource) dao.find(hql, null).get(0);
			list.add(baseResource);
		}
		
		logger.info("*****  BaseResourceServiceImpl.getPresourceByCode method end*****");
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public JSONObject getMenu() throws ServiceException {
		logger.info("***** BaseResourceServiceImpl.getMenu method begin*****");
		JSONObject root = new JSONObject();
		root.put("text", "HOME");
		root.put("code", "0");
		root.put("href", "");
		root.put("selectable", true);
		root.put("tags", "['available']");
		String rootActionHtml="<div class=\"pull-right\"><button class=\"btn btn-icon btn-success m-b-5 btn-xs \" data-toggle=\"modal\" data-target=\"#base_modal\" href=\"./baseResource/mBfAdd?code=0\"> <i class=\"md md-add\"></i></button> <button class=\"btn btn-icon btn-warning m-b-5 btn-xs \" data-toggle=\"modal\" data-target=\"#base_modal\" href=\"./baseResource/mBfEdit?code=0\"> <i class=\"md md-border-color\"></i></button> </div>";
		root.put("actionHtml", rootActionHtml);
		List<BaseResource> baseResources = this.getResourceByPcode("0");
		JSONArray array = new JSONArray();
		for (BaseResource baseResource : baseResources) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("text", baseResource.getResourceName());
			jsonObject.put("code", baseResource.getCode());
			jsonObject.put("pcode", baseResource.getParentResCode());
			String actionHtml1="<div class=\"pull-right\"><button class=\"btn btn-icon btn-success m-b-5 btn-xs \" data-toggle=\"modal\" data-target=\"#base_modal\" href=\"./baseResource/mBfAdd?code="+baseResource.getCode()+"\"> <i class=\"md md-add\"></i></button> <button class=\"btn btn-icon btn-warning m-b-5 btn-xs \" data-toggle=\"modal\" data-target=\"#base_modal\" href=\"./baseResource/mBfEdit?code="+baseResource.getCode()+"\"> <i class=\"md md-border-color\"></i></button> <button class=\"btn btn-icon btn-danger m-b-5 btn-xs ajaxToDo\"  href=\"./baseResource/delete?callbackMethod=getTree&code="+baseResource.getCode()+"\"> <i class=\"md md-clear\"></i></button></div>";
			jsonObject.put("actionHtml", actionHtml1);
			List<BaseResource> baseResources2 = this.getResourceByPcode(baseResource.getCode());
			JSONArray jsonArray = new JSONArray();
			for (BaseResource baseResource2 : baseResources2) {
				if (baseResource2.getResourceType()==1) {
					JSONObject jsonObject2 = new JSONObject();
					jsonObject2.put("text", baseResource2.getResourceName());
					jsonObject2.put("code", baseResource2.getCode());
					jsonObject2.put("pcode", baseResource2.getParentResCode());
					jsonObject2.put("href", "./baseResource/list");
					String actionHtml2="<div class=\"pull-right\"><button class=\"btn btn-icon btn-warning m-b-5 btn-xs \" data-toggle=\"modal\" data-target=\"#base_modal\" href=\"./baseResource/mBfEdit?code="+baseResource2.getCode()+"\"> <i class=\"md md-border-color\"></i></button> <button class=\"btn btn-icon btn-danger m-b-5 btn-xs ajaxToDo\"  href=\"./baseResource/delete?callbackMethod=getTree&code="+baseResource2.getCode()+"\"> <i class=\"md md-clear\"></i></button></div>";
					jsonObject2.put("actionHtml", actionHtml2);
					jsonArray.add(jsonObject2);
				}
			}
			jsonObject.put("nodes", jsonArray);
			array.add(jsonObject);
		}
		root.put("nodes", array);
		
		
		
		logger.info("*****  BaseResourceServiceImpl.getMenu method end*****");
		// TODO Auto-generated method stub
		return root;
	}
	
	@Override
	public Pagination getListByPcode(Dto dto)throws ServiceException{
		logger.info("***** BaseResourceServiceImpl.getList method begin*****");
			String pCode = dto.getAsString("pCode");
			String keyword = dto.getAsString("keyword");
			Integer pageNo = dto.getAsInteger("pageNo");
			Integer pageSize =dto.getAsInteger("pageSize");
			String orderField = dto.getAsString("orderField");
			String orderDirection = dto.getAsString("orderDirection");
			
			
			String hql = "from BaseResource where status=1";
			if (!StringUtils.isEmpty(pCode)) {
				hql += " and parentResCode='"+pCode+"'";
			}
			Map<String, Object> paramMap = null;
			if (!StringUtils.isEmpty(keyword)) {
				paramMap = new HashMap<String, Object>(1);
				hql += " and resourceName like :keyword";
				paramMap.put("keyword", "%" + keyword.trim() + "%");
			}
			
			hql += " order by " + orderField+" "+orderDirection;
			
			Finder finder = Finder.create(hql);
			if(paramMap != null && paramMap.size() > 0){
				finder.setParams(paramMap);
			}
			logger.info("*****  BaseResourceServiceImpl.getList method end*****");
			return dao.find(finder, pageNo, pageSize);
	}

	@Override
	public List<BaseResource> getResourceByPcode(String pcode) throws ServiceException {
		logger.info("***** BaseResourceServiceImpl.getResourceByPcode method begin*****");
		
		String hql = "from BaseResource where parentResCode='"+pcode+"' and status=1 order by orderd asc";
		List<BaseResource> resources =dao.find(hql, null);
		
		logger.info("*****  BaseResourceServiceImpl.getResourceByPcode method end*****");
		return resources;
	}

	@Override
	public void save(Dto dto) throws ServiceException {
		logger.info("***** BaseResourceServiceImpl.save method begin*****");
		BaseResource baseResource = (BaseResource) dto.get("baseResource");
		baseResource.setStatus(1);
		baseResource.setCode(CoreUtils.getCode("res"));
		dao.save(baseResource);
		//刷新资源
		securityMetadataSource.refreshResource();
		logger.info("*****  BaseResourceServiceImpl.save method end*****");
		
	}

	@Override
	public BaseResource get(Dto dto) throws ServiceException {
		logger.info("***** BaseResourceServiceImpl.get method begin*****");
		String code = dto.getAsString("code");
		
		BaseResource baseResource = (BaseResource) dao.findUniqueByProperty(BaseResource.class, "code", code);
		
		
		logger.info("*****  BaseResourceServiceImpl.get method end*****");
		return baseResource;
	}
	
	
	@Override
	public void update(Dto dto) throws ServiceException {
		logger.info("***** BaseResourceServiceImpl.update method begin*****");
		BaseResource baseResource = (BaseResource) dto.get("baseResource");
		dao.update(baseResource);
		//刷新资源
		securityMetadataSource.refreshResource();
		logger.info("*****  BaseResourceServiceImpl.update method end*****");
	}
	
	
	@Override
	public Dto delete(Dto dto) throws ServiceException {
		logger.info("***** BaseResourceServiceImpl.delete() method begin*****");
		
		String code = dto.getAsString("code");
		
		//判断是否 有子节点
		String hql1 = "from BaseResource where status=1 and parentResCode='"+code+"'";
		
		Integer result = dao.countQueryResult(Finder.create(hql1), null);
		if (result>0) {
			dto.setResultCode(StatusCode.M_Failure);
			dto.setResultMsg("请先删除子菜单或资源！");
			
			return dto;
		}
		String hql = "update from BaseResource set status = -1 where code='"+code+"'";
		dao.update(hql,null);
		//刷新资源
		securityMetadataSource.refreshResource();
		logger.info("***** BaseResourceServiceImpl.delete() method end*****");
		
		dto.setResultCode(StatusCode.M_Success);
		dto.setResultMsg(StatusCode.getName(StatusCode.M_Success));
		return dto;
	}
	
	@Override
	public void deleteAll(Dto dto) throws ServiceException {
		logger.info("***** BaseResourceServiceImpl.delete() method begin*****");
		
		String ids = dto.getAsString("ids");
		String hql = "update from BaseResource set status = -1 where id in ("+ids+")";
		dao.update(hql,null);
		//刷新资源
		securityMetadataSource.refreshResource();
		logger.info("***** BaseResourceServiceImpl.delete() method end*****");
	}

	@Override
	public List<BaseResource> getRightMenu(String code) throws ServiceException {
		logger.info("***** BaseResourceServiceImpl.getRightMenu() method begin*****");
		
		//获取右侧菜单
		List<BaseResource> list = new ArrayList<BaseResource>();
		//获取自身信息
		
		
		BaseResource resourcelast = (BaseResource) dao.findUniqueByProperty(BaseResource.class, "code", code);
		list.add(resourcelast);
		BaseResource baseResource = resourcelast;
		
		while(!baseResource.getParentResCode().equals("code0")){
			String hql2 = "from BaseResource where status=1 and code='"+baseResource.getParentResCode()+"'";
			baseResource = (BaseResource) dao.find(hql2, null).get(0);
			list.add(baseResource);
		}
		
		logger.info("***** BaseResourceServiceImpl.getRightMenu() method end*****");
		return list;
	}

}
