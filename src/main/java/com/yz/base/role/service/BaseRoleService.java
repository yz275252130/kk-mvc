/**   
 * @Title: BaseRoleService.java 
 * @Package com.yz.base.role.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月26日 下午3:13:17  
 */
package com.yz.base.role.service;

import com.alibaba.fastjson.JSONObject;
import com.yz.base.role.pojo.BaseRole;
import com.yz.core.datastructure.dto.Dto;
import com.yz.core.datastructure.page.Pagination;
import com.yz.core.exception.ServiceException;

/** 
 * @ClassName: BaseRoleService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月26日 下午3:13:17 
 *  
 */
public interface BaseRoleService {

	/**
	 * @method: getList 
	 * @Description: 分页获取信息
	 * @param: 
	 * @return: Pagination
	 * @throws:
	 */
	public Pagination getList(Dto dto)throws ServiceException;
	
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
	public BaseRole get(Dto dto) throws ServiceException;
	
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
	 * @method: getRoleRes 
	 * @Description: 
	 * @param: 
	 * @return: Dto
	 * @throws:
	 */
	public Dto getRoleRes(Dto dto) throws ServiceException ;
	
	/**
	 * @method: updateRoleRes 
	 * @Description: 
	 * @param: 
	 * @return: void
	 * @throws:
	 */
	public void updateRoleRes(Dto dto) throws ServiceException;
	
}
