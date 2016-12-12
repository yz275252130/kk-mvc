/**   
 * @Title: BaseUserServiceImpl.java 
 * @Package com.yz.base.user.service.impl 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月9日 上午10:18:08  
 */
package com.yz.base.user.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yz.base.role.pojo.BaseRole;
import com.yz.base.role.pojo.BaseUserRole;
import com.yz.base.user.pojo.BaseUser;
import com.yz.base.user.service.BaseUserService;
import com.yz.core.datastructure.dto.Dto;
import com.yz.core.datastructure.page.Pagination;
import com.yz.core.datastructure.response.StatusCode;
import com.yz.core.exception.ServiceException;
import com.yz.core.hibernate4.Finder;
import com.yz.core.hibernate4.HibernateBaseDAO;
import com.yz.core.utils.CoreUtils;

/** 
 * @ClassName: BaseUserServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月9日 上午10:18:08 
 *  
 */
@Service("BaseUserService")
public class BaseUserServiceImpl implements BaseUserService{
	
	private static final Logger  logger = (Logger) LoggerFactory.getLogger(BaseUserServiceImpl.class);
	@Resource
	private HibernateBaseDAO dao;
	
	@Override
	public BaseUser getUserByUserAccount(String userAccount) throws ServiceException {
		logger.info("***** BaseUserServiceImpl.getUserByUserAccount method begin*****");
		// TODO Auto-generated method stub
		BaseUser baseUser = (BaseUser) dao.findUniqueByProperty(BaseUser.class, "userAccount", userAccount);
		logger.info("***** BaseUserServiceImpl.getUserByUserAccount method end*****");
		return baseUser;
	}

	public BaseUser getUserByUserCode(String userCode) throws ServiceException {
		logger.info("***** BaseUserServiceImpl.getUserByUserCode method begin*****");
		// TODO Auto-generated method stub
		BaseUser baseUser = (BaseUser) dao.findUniqueByProperty(BaseUser.class, "code", userCode);
		logger.info("***** BaseUserServiceImpl.getUserByUserCode method end*****");
		return baseUser;
	}
	
	
	
	public Pagination getList(Dto dto)throws ServiceException{
		logger.info("***** BaseUserServiceImpl.getList method begin*****");
			String keyword = dto.getAsString("keyword");
			Integer pageNo = dto.getAsInteger("pageNo");
			Integer pageSize =dto.getAsInteger("pageSize");
			String orderField = dto.getAsString("orderField");
			String orderDirection = dto.getAsString("orderDirection");
			
			String hql = "from BaseUser where (status=1 or status=0)";
			Map<String, Object> paramMap = null;
			if (!StringUtils.isEmpty(keyword)) {
				paramMap = new HashMap<String, Object>(1);
				hql += " and (userName like :keyword or userAccount like :keyword)";
				paramMap.put("keyword", "%" + keyword.trim() + "%");
			}
			
			hql += " order by " + orderField+" "+orderDirection;
			
			Finder finder = Finder.create(hql);
			if(paramMap != null && paramMap.size() > 0){
				finder.setParams(paramMap);
			}
			Pagination pagination = dao.find(finder, pageNo, pageSize);
			List<BaseUser> baseUserList = (List<BaseUser>) pagination.getList();
			for (BaseUser baseUser : baseUserList) {
				List<String> roleCodeList = getUserRoel(baseUser.getCode());
				baseUser.setRoleCodes(roleCodeList.toString());
			}
					
			logger.info("*****  BaseUserServiceImpl.getList method end*****");
			return pagination;
	}

	@Override
	public Dto save(Dto dto) throws ServiceException {
		logger.info("***** BaseUserServiceImpl.save method begin*****");
		BaseUser baseUser = (BaseUser) dto.get("baseUser");
		
		String hql = " from BaseUser where status <> -1 and userAccount = :userAccount";
		
		//源代码int count = dao.countByProperty(BaseUser.class, "userAccount",baseUser.getUserAccount());
		int count = dao.countQueryResult(Finder.create(hql).setParam("userAccount", baseUser.getUserAccount()), null);
		
		if (count>0) {
			dto.setResultCode(StatusCode.M_Failure);
			dto.setResultMsg("该用户名已经存在!");
			return dto;
		}
		
		String userCode = CoreUtils.getCode("USER");
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String password = md5.encodePassword(getPassword(), userCode);
		baseUser.setCode(userCode);
		baseUser.setStatus(1);
		baseUser.setUserPassword(password);
		baseUser.setType(3);
		baseUser.setUserLoginTime(new Timestamp(System.currentTimeMillis()));
		baseUser.setUserLastTime(new Timestamp(System.currentTimeMillis()));
		dto.setResultCode(StatusCode.M_Success);
		dto.setResultMsg(StatusCode.getName(StatusCode.M_Success));
		dao.save(baseUser);
		logger.info("*****  BaseUserServiceImpl.save method end*****");
		return dto;
	}
	@Override
	public BaseUser get(Dto dto) throws ServiceException {
		logger.info("***** BaseUserServiceImpl.get method begin*****");
		Integer id = dto.getAsInteger("id");
		
		BaseUser baseUser = (BaseUser) dao.load(BaseUser.class, id);
		
		logger.info("*****  BaseUserServiceImpl.get method end*****");
		return baseUser;
	}

	@Override
	public void update(Dto dto) throws ServiceException {
		logger.info("***** BaseUserServiceImpl.update method begin*****");
		
		BaseUser baseUser = (BaseUser) dto.get("baseUser");
		dao.update(baseUser);
		logger.info("***** BaseUserServiceImpl.update method end*****");
	}

	@Override
	public void delete(Dto dto) throws ServiceException {
		logger.debug("***** BaseUserServiceImpl.delete() method begin*****");
		
		String ids = dto.getAsString("ids");
		 
		String hql = "update from BaseUser set status = -1 where id in ("+ids+"))";
		dao.update(hql,null);
		
		logger.debug("***** BaseUserServiceImpl.delete() method end*****");
		
	}
	
	@Override
	public void updateStatus(Dto dto) throws ServiceException{
		logger.info("***** BaseUserServiceImpl.updateStatus method begin*****");
		String code=dto.getAsString("code");
		Integer status = dto.getAsInteger("status");
		String updatehql= "update from BaseUser set status = "+status+" where  code='"+code+"'";
		dao.update(updatehql, null);
		logger.info("***** BaseUserServiceImpl.updateStatus method end*****");
	}
	
	@Override
	public void updateUserRole(Dto dto) throws ServiceException{
		logger.info("***** BaseUserServiceImpl.updateUserRole method begin*****");
		String userCode=dto.getAsString("userCode");
		String[] roles = (String[]) dto.get("roles");
		
	
		//删除原有数据
		String hql="delete from BaseUserRole where userCode='"+userCode+"'";
		dao.delete(hql, null);
		
		//插入新数据
		for (String string : roles) {
			String roleCode = string;
			BaseUserRole baseUserRole = new BaseUserRole();
			baseUserRole.setUserCode(userCode);
			baseUserRole.setRoleCode(roleCode);
			baseUserRole.setStatus(1);
			dao.save(baseUserRole);
		}
		logger.info("***** BaseUserServiceImpl.updateUserRole method end*****");
	}
	
	@Override
	public List<String> getUserRoel(String userCode) throws ServiceException{
		logger.info("***** BaseUserServiceImpl.getUserRoel method begin*****");

		String hql2 = "select roleCode from BaseUserRole where userCode='"+userCode+"'";
		//获取用户拥有的角色信息
		List<String> roleCodes =dao.find(hql2, null);
		
		
		logger.info("***** BaseUserServiceImpl.getUserRoel method end*****");
		
		return roleCodes;
	}
	
	
	@Override
	public List<BaseRole> getRoel() throws ServiceException{
		logger.info("***** BaseUserServiceImpl.getRoel method begin*****");
		//获取所有角色信息
		String hql ="from BaseRole where status=1";
		List<BaseRole> baseRoles = dao.find(hql, null);
		logger.info("***** BaseUserServiceImpl.getRoel method end*****");
		
		return baseRoles;
	}
	
	@Override
	public Dto updatePassword(Dto dto) throws ServiceException{
		logger.info("***** BaseUserServiceImpl.updatePassword method begin*****");
		String newPassword=dto.getAsString("newPassword");
		String password=dto.getAsString("password");
		String confirmPassword=dto.getAsString("confirmPassword");
		String userAccount=dto.getAsString("userAccount");
		if (!StringUtils.equals(newPassword, confirmPassword)) {
			dto.setResultCode("300");
			dto.setResultMsg("两次输入的新密码不一致");
			
			return dto;
		}
		
		String hql = "from BaseUser where status=1 and userAccount='"+userAccount+"'";
		List<BaseUser> users =  dao.find(hql, null);
		
		if (users.size()>0) {
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			BaseUser baseUser = users.get(0);
			if (StringUtils.equals(md5.encodePassword(password, baseUser.getCode()), baseUser.getUserPassword())) {
				
				String userPassword = md5.encodePassword(newPassword, baseUser.getCode());
				baseUser.setUserPassword(userPassword);
				dao.update(baseUser);
				dto.setResultCode("200");
				dto.setResultMsg("操作成功！");
				
			}else {
				dto.setResultCode("300");
				dto.setResultMsg("密码错误！");
			}
			
		}
		
		logger.info("***** BaseUserServiceImpl.updatePassword method end*****");
		
		return dto;
	}
	
	@Override
	public void updateUser(Dto dto) throws Exception{
		logger.info("***** BaseUserServiceImpl.updateUser method begin*****");
		
		Integer id = dto.getAsInteger("id");
		
		String name = dto.getAsString("name");
		
		String value = dto.getAsString("value");
		
		
		BaseUser baseUser = (BaseUser) dao.load(BaseUser.class, id);
		baseUser.getClass().getField(name).set(baseUser, value);	
		dao.update(baseUser);
		logger.info("***** BaseUserServiceImpl.updateUser method end*****");
	}
	
	private String getPassword() {
		return "123456";
	}
}
