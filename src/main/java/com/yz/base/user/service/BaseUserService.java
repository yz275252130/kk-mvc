/**   
 * @Title: BaseUserService.java 
 * @Package com.yz.base.user.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月9日 上午10:17:40  
 */
package com.yz.base.user.service;

import java.util.List;

import com.yz.base.role.pojo.BaseRole;
import com.yz.base.user.pojo.BaseUser;
import com.yz.core.datastructure.dto.Dto;
import com.yz.core.datastructure.page.Pagination;
import com.yz.core.exception.ServiceException;

/** 
 * @ClassName: BaseUserService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月9日 上午10:17:40 
 *  
 */
public interface BaseUserService {
	
	public BaseUser getUserByUserAccount(String userAccount)throws ServiceException; 
	
	public BaseUser getUserByUserCode(String userCode) throws ServiceException ;
	
	
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
	public Dto save(Dto dto) throws ServiceException;
	
	/**
	 * @method: get 
	 * @Description: 
	 * @param: 
	 * @return: BaseUser
	 * @throws:
	 */
	public BaseUser get(Dto dto) throws ServiceException;
	
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
	public void delete(Dto dto) throws ServiceException;
	
	/**
	 * @method: updateStatus 
	 * @Description: 
	 * @param: 
	 * @return: void
	 * @throws:
	 */
	public void updateStatus(Dto dto) throws ServiceException;
	
	/**
	 * @method: updateUserRole 
	 * @Description: 
	 * @param: 
	 * @return: void
	 * @throws:
	 */
	public void updateUserRole(Dto dto) throws ServiceException;
	
	/**
	 * @method: getUserRoel 
	 * @Description: 
	 * @param: 
	 * @return: BaseUser
	 * @throws:
	 */
	public List<String> getUserRoel(String userCode) throws ServiceException;
	
	public List<BaseRole> getRoel() throws ServiceException;
	
	public Dto updatePassword(Dto dto) throws ServiceException;
	
	
	public void updateUser(Dto dto) throws Exception;
	
}
