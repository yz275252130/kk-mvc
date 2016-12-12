/**   
 * @Title: BaseDictionaryServiceImpl.java 
 * @Package com.yz.base.dictionary.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月31日 下午5:31:19  
 */
package com.yz.base.dictionary.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yz.base.dictionary.pojo.BaseDictionary;
import com.yz.base.dictionary.service.BaseDictionaryService;
import com.yz.base.resource.pojo.BaseResource;
import com.yz.base.user.pojo.BaseUser;
import com.yz.core.datastructure.dto.Dto;
import com.yz.core.datastructure.dto.impl.BaseDto;
import com.yz.core.datastructure.page.Pagination;
import com.yz.core.datastructure.response.StatusCode;
import com.yz.core.exception.ServiceException;
import com.yz.core.hibernate4.Finder;
import com.yz.core.hibernate4.HibernateBaseDAO;
import com.yz.core.utils.CoreUtils;

/**
 * @ClassName: BaseDictionaryServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author yz
 * @date 2016年5月31日 下午5:31:19
 * 
 */

@Service("BaseDictionaryService")
public class BaseDictionaryServiceImpl implements BaseDictionaryService {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(BaseDictionaryServiceImpl.class);
	@Resource
	private HibernateBaseDAO dao;

	@Override
	public List<BaseDictionary> getListByPcode(Dto dto) throws ServiceException {
		logger.info("***** BaseDictionaryServiceImpl.getListByPcode method begin*****");
		String pCode = dto.getAsString("pCode");

		String hql = "from BaseDictionary where status=1 and parentCode='" + pCode + "'";

		
		logger.info("***** BaseDictionaryServiceImpl.getListByPcode method end*****");

		return dao.find(hql, null);
	}

	@Override
	public JSONObject getDictionaryTree() throws ServiceException {
		logger.info("***** BaseDictionaryServiceImpl.getDictionaryTree method begin*****");
		JSONObject root = new JSONObject();
		root.put("text", "数据字典");
		root.put("code", "000000");
		root.put("pcode", "-1");
		root.put("href", "");
		root.put("selectable", true);
		root.put("tags", "['available']");
		String rootActionHtml="<div class=\"pull-right\"><button class=\"btn btn-icon btn-success m-b-5 btn-xs \" data-toggle=\"modal\" data-target=\"#base_modal\" href=\"./baseDictionary/mBfAdd?code=000000\"> <i class=\"md md-add\"></i></button> <button class=\"btn btn-icon btn-warning m-b-5 btn-xs \" data-toggle=\"modal\" data-target=\"#base_modal\" href=\"./baseDictionary/mBfEdit?id=1\"> <i class=\"md md-border-color\"></i></button> <button class=\"btn btn-icon btn-danger m-b-5 btn-xs ajaxToDo\"  href=\"./baseDictionary/delete?callbackMethod=getTree&code=000000\"> <i class=\"md md-clear\"></i></button></div>";
		root.put("actionHtml", rootActionHtml);
		Dto dto = new BaseDto();
		dto.put("pCode", "000000");
		List<BaseDictionary> baseDictionaries = this.getListByPcode(dto);
		JSONArray array = new JSONArray();
		for (BaseDictionary baseDictionary : baseDictionaries) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("text", baseDictionary.getName());
			jsonObject.put("code", baseDictionary.getCode());
			jsonObject.put("pcode", baseDictionary.getParentCode());
			jsonObject.put("href", "./baseDictionary/list");
			String actionHtml1="<div class=\"pull-right\"><button class=\"btn btn-icon btn-warning m-b-5 btn-xs \" data-toggle=\"modal\" data-target=\"#base_modal\" href=\"./baseDictionary/mBfEdit?id="+baseDictionary.getId()+"\"> <i class=\"md md-border-color\"></i></button> <button class=\"btn btn-icon btn-danger m-b-5 btn-xs ajaxToDo\"  href=\"./baseDictionary/delete?callbackMethod=getTree&code="+baseDictionary.getCode()+"\"> <i class=\"md md-clear\"></i></button></div>";
			jsonObject.put("actionHtml", actionHtml1);
			array.add(jsonObject);
		}
		root.put("nodes", array);
		
		
		
		logger.info("*****  BaseDictionaryServiceImpl.getDictionaryTree method end*****");
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		logger.info("***** BaseDictionaryServiceImpl.getList method begin*****");
		String pCode = dto.getAsString("pCode");
		String keyword = dto.getAsString("keyword");
		Integer pageNo = dto.getAsInteger("pageNo");
		Integer pageSize =dto.getAsInteger("pageSize");
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		
		
		String hql = "from BaseDictionary where status=1";
		if (!StringUtils.isEmpty(pCode)) {
			hql += " and parentCode='"+pCode+"'";
		}
		Map<String, Object> paramMap = null;
		if (!StringUtils.isEmpty(keyword)) {
			paramMap = new HashMap<String, Object>(1);
			hql += " and name like :keyword";
			paramMap.put("keyword", "%" + keyword.trim() + "%");
		}
		
		hql += " order by " + orderField+" "+orderDirection;
		
		Finder finder = Finder.create(hql);
		if(paramMap != null && paramMap.size() > 0){
			finder.setParams(paramMap);
		}
		logger.info("*****  BaseDictionaryServiceImpl.getList method end*****");
		return dao.find(finder, pageNo, pageSize);
	}

	@Override
	public void save(Dto dto) throws ServiceException {
		logger.info("***** BaseDictionaryServiceImpl.save method begin*****");
		BaseDictionary baseDictionary = (BaseDictionary) dto.get("baseDictionary");
		baseDictionary.setStatus(1);
		dao.save(baseDictionary);
		logger.info("*****  BaseDictionaryServiceImpl.save method end*****");
	}

	@Override
	public BaseDictionary get(Dto dto) throws ServiceException {
		logger.info("***** BaseDictionaryServiceImpl.get method begin*****");
		Integer id = dto.getAsInteger("id");
		
		
		BaseDictionary baseDictionary = (BaseDictionary) dao.load(BaseDictionary.class, id);
		
		
		logger.info("*****  BaseDictionaryServiceImpl.get method end*****");
		return baseDictionary;
	}

	@Override
	public void update(Dto dto) throws ServiceException {
		logger.info("***** BaseDictionaryServiceImpl.update method begin*****");
		BaseDictionary baseDictionary = (BaseDictionary) dto.get("baseDictionary");
		dao.update(baseDictionary);
		logger.info("*****  BaseDictionaryServiceImpl.update method end*****");
		
	}

	@Override
	public Dto delete(Dto dto) throws ServiceException {

		logger.debug("***** BaseDictionaryServiceImpl.delete() method begin*****");
		
		String code = dto.getAsString("code");
		
		//判断是否 有子节点
		String hql1 = "from BaseDictionary where status=1 and parentCode='"+code+"'";
		
		Integer result = dao.countQueryResult(Finder.create(hql1), null);
		if (result>0) {
			dto.setResultCode(StatusCode.M_Failure);
			dto.setResultMsg("数据字典中的数据！");
			
			return dto;
		}
		String hql = "update from BaseDictionary set status = -1 where code='"+code+"'";
		dao.update(hql,null);
		logger.debug("***** BaseDictionaryServiceImpl.delete() method end*****");
		
		dto.setResultCode(StatusCode.M_Success);
		dto.setResultMsg(StatusCode.getName(StatusCode.M_Success));
		return dto;
	}

	@Override
	public void deleteAll(Dto dto) throws ServiceException {
		logger.debug("***** BaseDictionaryServiceImpl.delete() method begin*****");
		
		String ids = dto.getAsString("ids");
		String hql = "update from BaseDictionary set status = -1 where id in ("+ids+")";
		dao.update(hql,null);
		logger.debug("***** BaseDictionaryServiceImpl.delete() method end*****");
		
	}

}
