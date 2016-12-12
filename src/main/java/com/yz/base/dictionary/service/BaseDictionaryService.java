/**   
 * @Title: BaseDictionaryService.java 
 * @Package com.yz.base.dictionary.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月31日 下午5:30:59  
 */
package com.yz.base.dictionary.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yz.base.dictionary.pojo.BaseDictionary;
import com.yz.core.datastructure.dto.Dto;
import com.yz.core.datastructure.page.Pagination;
import com.yz.core.exception.ServiceException;

/** 
 * @ClassName: BaseDictionaryService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月31日 下午5:30:59 
 *  
 */
public interface BaseDictionaryService {

	/**
	 * @method: getListByPcode 
	 * @Description: 
	 * @param: 
	 * @return: List<BaseDictionary>
	 * @throws:
	 */
	public List<BaseDictionary> getListByPcode(Dto dto) throws ServiceException;
	
	/**
	 * @method: getDictionaryTree 
	 * @Description: 
	 * @param: 
	 * @return: List<BaseResource>
	 * @throws:
	 */
	public JSONObject getDictionaryTree() throws ServiceException;
	
	/**
	 * @method: getList 
	 * @Description: 
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
	public BaseDictionary get(Dto dto) throws ServiceException;
	
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
	 * @method: deleteAll 
	 * @Description: 
	 * @param: 
	 * @return: void
	 * @throws:
	 */
	public void deleteAll(Dto dto) throws ServiceException ;
	
}
