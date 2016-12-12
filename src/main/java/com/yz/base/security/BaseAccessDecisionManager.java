/**   
 * @Title: BaseAccessDecisionManager.java 
 * @Package com.yz.base.security 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月7日 下午1:53:44  
 */
package com.yz.base.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/** 
 * @ClassName: BaseAccessDecisionManager 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月7日 下午1:53:44 
 *  
 */
public class BaseAccessDecisionManager implements AccessDecisionManager {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseAccessDecisionManager.class);

    //In this method, need to compare authentication with configAttributes.
    // 1, A object is a URL, a filter was find permission configuration by this URL, and pass to here.
    // 2, Check authentication has attribute in permission configuration (configAttributes)
    // 3, If not match corresponding authentication, throw a AccessDeniedException.
    public void decide(Authentication authentication, Object object,Collection<ConfigAttribute> configAttributes)throws AccessDeniedException, InsufficientAuthenticationException {
        if(configAttributes == null){
            return ;
        }
        Iterator<ConfigAttribute> ite=configAttributes.iterator();
        while(ite.hasNext()){
            ConfigAttribute ca=ite.next();
            //需要的权限集合
            String needAuthority=((SecurityConfig)ca).getAttribute();
            for(GrantedAuthority ga:authentication.getAuthorities()){
                if(needAuthority.equals(ga.getAuthority())){  
                    return;
                }
            }
        }
        
        if(authentication.getPrincipal() instanceof UserDetails){
        	UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        	logger.error( userDetails.getUsername() + " access " + object);
        }
        throw new AccessDeniedException("no right");
    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }


}
