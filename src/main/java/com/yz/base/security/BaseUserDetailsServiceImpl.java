/**   
 * @Title: BaseUserDetailsServiceImpl.java 
 * @Package com.yz.base.security 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月7日 上午11:56:21  
 */
package com.yz.base.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yz.base.role.service.BaseRoleResourceService;
import com.yz.base.user.pojo.BaseUser;
import com.yz.base.user.pojo.VUserRoleResources;
import com.yz.base.user.service.BaseUserService;

/** 
 * @ClassName: BaseUserDetailsServiceImpl 
 * @Description:  从数据库中读入用户的密码，角色信息，是否锁定，账号是否过期
 * @author yz
 * @date 2016年5月7日 上午11:56:21 
 *  
 */
@SuppressWarnings({ "unchecked", "unused" })
public class BaseUserDetailsServiceImpl implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(BaseUserDetailsServiceImpl.class);
	
	@Resource 
	private BaseUserService baseUserService;
	@Resource  BaseRoleResourceService baseRoleResourceService ;
	protected MessageSourceAccessor messages;
	
	public UserDetails loadUserByUsername(String userAccount)throws UsernameNotFoundException, DataAccessException {
		Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();
		
		//20140122,edit by wds，修改登录用户名的SQL注入
		String hql = " from BaseUser where status = 1 and userAccount = :userAccount";
		Map<String, Object> map = new HashMap<String, Object>(1);
		map.put("userAccount", userAccount);
		//获取用户对象
		//源代码
		BaseUser baseUser = baseUserService.getUserByUserAccount(userAccount);
		if (null==baseUser) {
			logger.info("用户名：{}不存在", userAccount);
			throw new UsernameNotFoundException(messages.getMessage("BaseUserDetailServiceImpl.notExistUser"));
		}
		//取得用户的权限
		List<VUserRoleResources> list = baseRoleResourceService.getRoleResourceByUser(baseUser.getCode());
		
		if (null == list || list.size()==0) {
			logger.info("用户{}在此系统无任何权限", userAccount);
			throw new AuthenticationCredentialsNotFoundException(messages.getMessage("BaseUserDetailServiceImpl.noRight"));
		}
		
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		SimpleGrantedAuthority sga = null;
		for (VUserRoleResources userRole : list) {
			sga = new SimpleGrantedAuthority(userRole.getRoleCode());
			auth.add(sga);
		}
		
		//取得用户的密码
		String password=baseUser.getUserPassword();
		BaseUserDetail user = new BaseUserDetail(userAccount, baseUser.getUserName(), baseUser.getUserPassword(),  baseUser.getCode(), true, true, true, true, auth);
		return user;
	}
	
	public void setMessageSource(MessageSource messageSource) {
		this.messages = new MessageSourceAccessor(messageSource);
    }
	
}