package com.yz.base.system.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yz.base.resource.pojo.BaseResource;
import com.yz.base.system.service.SystemService;
import com.yz.core.exception.ServiceException;
import com.yz.core.hibernate4.HibernateBaseDAO;

/** 
 * @ClassName: SystemServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月10日 上午8:58:10 
 *  
 */

@Service("SystemService")
public class SystemServiceImpl implements SystemService{
	private static final Logger  logger = (Logger) LoggerFactory.getLogger(SystemServiceImpl.class);
	@Resource
	private HibernateBaseDAO dao;
	@Override
	public List<BaseResource> getMenuByParent(String parentCode) throws ServiceException {
		logger.info("***** SystemServiceImpl.getMenuByParent method begin*****");
		HashMap<String, List<BaseResource>> rHashMap = new HashMap<>();
		
		//获取左侧菜单
		String hql="from BaseResource where status=1 and parentResCode='"+parentCode+"' and resourceType=1 order by orderd asc";
		List<BaseResource> resourceList = dao.find(hql, null);
		for (BaseResource baseResource : resourceList) {
			baseResource.setResourceUrl(baseResource.getResourceUrl().split(",")[0]);
		}
		
		logger.info("***** SystemServiceImpl.getMenuByParent method end*****");
		return resourceList;
	}

	
	
}
