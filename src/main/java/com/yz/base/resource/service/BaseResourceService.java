/**   
 * @Title: ResourceService.java 
 * @Package com.yz.base.role.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月8日 上午9:26:58  
 */
package com.yz.base.resource.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yz.base.resource.pojo.BaseResource;
import com.yz.core.datastructure.dto.Dto;
import com.yz.core.datastructure.page.Pagination;
import com.yz.core.exception.ServiceException;

/** 
 * @ClassName: ResourceService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月8日 上午9:26:58 
 *  
 */
public interface BaseResourceService {
	
	/**
	 * @method: getResourceAll 
	 * @Description: 获取所有可用资源
	 * @param: 
	 * @return: List<BaseResource>
	 * @throws:
	 */
	public List<BaseResource> getResourceAll() throws ServiceException ;
	
	
	/**
	 * @method: getPresourceByCode 
	 * @Description: 根据code 获取所有父节点
	 * @param: 
	 * @return: List<BaseResource>
	 * @throws:
	 */
	public List<BaseResource> getPresourceByCode(String code) throws ServiceException;
	
	/**
	 * @method: getMenu 
	 * @Description: 获取菜单
	 * @param: 
	 * @return: List<BaseResource>
	 * @throws:
	 */
	public JSONObject getMenu() throws ServiceException;
	
	/**
	 * @method: getRightMenu 
	 * @Description: 
	 * @param: 
	 * @return: List<BaseResource>
	 * @throws:
	 */
	public List<BaseResource> getRightMenu(String code) throws ServiceException;
	
	/**
	 * @method: getResourceByPcode 
	 * @Description: 根据父节点 获取下一级子节点
	 * @param: 
	 * @return: List<BaseResource>
	 * @throws:
	 */
	public List<BaseResource> getResourceByPcode(String pcode) throws ServiceException;
	
	/**
	 * @method: save 
	 * @Description: 
	 * @param: 
	 * @return: void
	 * @throws:
	 */
	public void save(Dto dto) throws ServiceException;
	
	/**
	 * @method: get 
	 * @Description: 
	 * @param: 
	 * @return: BaseResource
	 * @throws:
	 */
	public BaseResource get(Dto dto) throws ServiceException;
	
	/**
	 * @method: update 
	 * @Description: 
	 * @param: 
	 * @return: void
	 * @throws:
	 */
	public void update(Dto dto) throws ServiceException;
	
	/**
	 * @method: delete 
	 * @Description: 
	 * @param: 
	 * @return: void
	 * @throws:
	 */
	public Dto delete(Dto dto) throws ServiceException;
	
	/**
	 * @method: getList 
	 * @Description: 
	 * @param: 
	 * @return: Pagination
	 * @throws:
	 */
	public Pagination getListByPcode(Dto dto)throws ServiceException;
	
	/**
	 * @method: deleteAll 
	 * @Description: 
	 * @param: 
	 * @return: void
	 * @throws:
	 */
	public void deleteAll(Dto dto) throws ServiceException ;
}
