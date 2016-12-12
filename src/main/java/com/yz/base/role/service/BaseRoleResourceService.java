/**   
 * @Title: RoleResourceService.java 
 * @Package com.yz.base.role.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月8日 上午9:22:56  
 */
package com.yz.base.role.service;

import java.util.List;

import com.yz.base.role.pojo.BaseRoleResource;
import com.yz.base.user.pojo.VUserRoleResources;
import com.yz.core.exception.ServiceException;

/** 
 * @ClassName: RoleResourceService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月8日 上午9:22:56 
 *  
 */
public interface BaseRoleResourceService {

	public List<BaseRoleResource> getRoleResourceAll()throws ServiceException ;
	
	public List<VUserRoleResources> getRoleResourceByUser(String userCode)throws ServiceException ;
	
	public List<VUserRoleResources> getRoleResourceByUserAndSourceType(String userCode,Integer sourceType) throws ServiceException;
}
