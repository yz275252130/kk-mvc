/**   
 * @Title: FilterInvocationSecurityMetadataSourceImpl.java 
 * @Package com.yz.base.security 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月7日 下午1:54:49  
 */
package com.yz.base.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.yz.base.resource.pojo.BaseResource;
import com.yz.base.resource.service.BaseResourceService;
import com.yz.base.role.pojo.BaseRoleResource;
import com.yz.base.role.service.BaseRoleResourceService;

/** 
 * @ClassName: FilterInvocationSecurityMetadataSourceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月7日 下午1:54:49 
 *  
 */
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {
	@Resource private BaseResourceService baseResourceService;
	@Resource private BaseRoleResourceService baseRoleResourceService;
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	
	private void loadResourceDefine() {
		List<BaseResource> baseResourceList=baseResourceService.getResourceAll();
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		List<BaseRoleResource> roleResourceList = baseRoleResourceService.getRoleResourceAll();
		//以资源为基础，查找与资源对应的角色
		
		Collection<ConfigAttribute> atts = null;
//		Collection<ConfigAttribute> value = null;
		
		for (BaseResource baseResource : baseResourceList) {
			//查找resourceCode对应的roleCode
			List<String> roleCodeList = searchRole(baseResource.getCode(), roleResourceList);
			
			ConfigAttribute ca = null;
			String url = baseResource.getResourceUrl();
			String[] urls = url.split(",");
			for (String key : urls) {
				key = getTrueUrl(key);
				// 判断资源和权限的对应关系，如果已经存在，要进行增加
				if (resourceMap.containsKey(key)) {
					atts = resourceMap.get(key);
//					atts.add(ca);
//					resourceMap.put(key, atts);
				} else {
					atts = new ArrayList<ConfigAttribute>();
				}
				
				for (String string : roleCodeList) {
					ca = new SecurityConfig(string);
					atts.add(ca);
				}
				
				resourceMap.put(key, atts);
			}
		
		}
	}

	private List<String> searchRole(String resCode, List<BaseRoleResource> roleResourceList) {
		List<String> result = new ArrayList<String>();
		for (Iterator<BaseRoleResource> iterator = roleResourceList.iterator(); iterator.hasNext();) {
			BaseRoleResource baseRoleResource = (BaseRoleResource) iterator.next();
			if(resCode.equals(baseRoleResource.getResourceCode())){
				result.add(baseRoleResource.getRoleCode());
				iterator.remove();
			}
		}
		return result;
	}

	// According to a URL, Find out permission configuration of this URL.
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		url = getTrueUrl(url);
		Collection<ConfigAttribute> cca = resourceMap.get(url);
		if(cca == null){
			cca = new ArrayList(1);
			ConfigAttribute ca = new SecurityConfig("DENY");
			cca.add(ca);
		}
		return cca;
	}

	private String getTrueUrl(String url) {
		//去掉URL中?号后面的字符
		int index = url.indexOf("?");
		if(index > 0){
			url = url.substring(0, index);
		}
		return url;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	
	public void refreshResource() {
		resourceMap.clear();
		loadResourceDefine();
	}

}