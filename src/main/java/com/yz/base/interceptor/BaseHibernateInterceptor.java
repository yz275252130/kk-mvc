/**   
 * @Title: BaseHibernateInterceptor.java 
 * @Package com.yz.base.interceptor 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月25日 上午9:32:21  
 */
package com.yz.base.interceptor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.yz.base.user.pojo.BaseUser;


/** 
 * @ClassName: BaseHibernateInterceptor 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月25日 上午9:32:21 
 *  
 */
public class BaseHibernateInterceptor extends EmptyInterceptor{
	

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		super.onDelete(entity, id, state, propertyNames, types);
	}

	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] state, Object[] previousState,
			String[] propertyNames, Type[] types) {
		String baseUserCode = getBaseUserCode();

		// 生成创建时间和更新时间
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		// 更改最后更新时间、最后更新人
		for (int i = 0; i < propertyNames.length; i++) {
			if ("lastUpdatedDate".equals(propertyNames[i])) {
				state[i] = ts;
			}else if ("lastUpdatedBy".equals(propertyNames[i]) && baseUserCode != null ) {
				state[i] = baseUserCode;
			}
		}
		
		return true;
	}

	private String getBaseUserCode() {
		BaseUser baseUser = null;
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			if(attr != null){
				baseUser = (BaseUser)attr.getRequest().getSession().getAttribute("USER");
			}
			if (null==baseUser) {
				return "SYSTEM";
			}
			return baseUser.getCode();
		} catch (Exception e) {
			logger.info(e.getMessage() + ": Data from interface or other, not web", e);
			return "SYSTEM";
		}
	}


	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {

		String baseUserCode = getBaseUserCode();
		
		// 生成创建时间和更新时间
		Timestamp ts = new Timestamp(new Date().getTime());

		// 更新创建时间、创建人、最后更新时间、最后更新人
		for (int i = 0; i < propertyNames.length; i++) {
			if ("lastUpdatedDate".equals(propertyNames[i]) || "createdDate".equals(propertyNames[i])) {
				if(state[i] == null){
					state[i] = ts;
				}
			} else if ("createdBy".equals(propertyNames[i]) || "lastUpdatedBy".equals(propertyNames[i])) {
				if(state[i] == null && baseUserCode != null){
					state[i] = baseUserCode;
				}
			}
		}

		return true;
	}


}
