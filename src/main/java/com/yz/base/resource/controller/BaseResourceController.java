/**   
 * @Title: ResourceController.java 
 * @Package com.yz.base.resource.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月20日 上午9:53:44  
 */
package com.yz.base.resource.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yz.base.resource.pojo.BaseResource;
import com.yz.base.resource.service.BaseResourceService;
import com.yz.core.datastructure.dto.Dto;
import com.yz.core.datastructure.dto.impl.BaseDto;
import com.yz.core.datastructure.page.Pagination;
import com.yz.core.web.BaseController;

/** 
 * @ClassName: ResourceController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月20日 上午9:53:44 
 *  
 */

@Controller
@RequestMapping(value="/baseResource")
public class BaseResourceController extends BaseController{
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(BaseResourceController.class);
	@Autowired
	private BaseResourceService baseResourceService;
	@RequestMapping(value="resource")
	public ModelAndView resource(){
		logger.info("***** BaseResourceController.resource method begin*****");
		ModelAndView result =  new ModelAndView("/base/resource/resource");
		logger.info("***** BaseResourceController.resource method end*****");
		return result;
	}
	
	@RequestMapping(value="resource_menu")
	public Object getMenuResource(){
		logger.info("***** BaseResourceController.getMenuResource method begin*****");
		JSONObject jsonObject = baseResourceService.getMenu();
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(jsonObject);
		
		logger.info("***** BaseResourceController.getMenuResource method end*****");
		return jsonArray;
	}
	
	@RequestMapping(value="list")
	public ModelAndView list(String code){
		logger.info("***** BaseResourceController.list method begin*****");
		ModelAndView result =  new ModelAndView("/base/resource/resource_list");
		result.addObject("code", code);
		logger.info("***** BaseResourceController.list method end*****");
		return result;
	}
	
	@RequestMapping(value="listByPcode")
	public Pagination listByPcode(String pCode){
		logger.info("***** BaseResourceController.listByPcode method begin*****");

		Dto dto = getDto();
		dto.put("pCode", pCode);
		Pagination page = baseResourceService.getListByPcode(dto);
		
		logger.info("***** BaseResourceController.listByPcode method end*****");
		return page;
	}
	
	@RequestMapping(value="bfAdd")
	public ModelAndView bfAdd(){
		logger.info("***** BaseResourceController.bfAdd method begin*****");
		ModelAndView result =  new ModelAndView("/base/resource/resource_add");
		logger.info("***** BaseResourceController.bfAdd method end*****");
		return result;
	}
	
	@RequestMapping(value="mBfAdd")
	public ModelAndView mBfAdd(){
		logger.info("***** BaseResourceController.mBfAdd method begin*****");
		ModelAndView result =  new ModelAndView("/base/resource/resource_mAdd");
		logger.info("***** BaseResourceController.mBfAdd method end*****");
		return result;
	}

	
	@RequestMapping(value="add")
	public ModelAndView add(BaseResource baseResource){
		logger.info("***** BaseResourceController.add method begin*****");
		Dto dto = new BaseDto();
		dto.put("baseResource", baseResource);
		baseResourceService.save(dto);
		logger.info("***** BaseResourceController.add method end*****");
		return AJAX();
	}
	
	@RequestMapping(value="bfEdit")
	public ModelAndView bfEdit(String code){
		logger.info("***** BaseResourceController.bfEdit method begin*****");
		ModelAndView result = new ModelAndView("/base/resource/resource_edit");

		Dto dto = new BaseDto();
		dto.put("code", code);
		BaseResource baseResource =  baseResourceService.get(dto);
		result.addObject("baseResource", baseResource);
		
		logger.info("***** BaseResourceController.bfEdit method end*****");
		return result;
	}
	
	@RequestMapping(value="mBfEdit")
	public ModelAndView mBfEdit(String code){
		logger.info("***** BaseResourceController.mBfEdit method begin*****");
		ModelAndView result = new ModelAndView("/base/resource/resource_mEdit");

		Dto dto = new BaseDto();
		dto.put("code", code);
		BaseResource baseResource =  baseResourceService.get(dto);
		result.addObject("baseResource", baseResource);
		
		logger.info("***** BaseResourceController.mBfEdit method end*****");
		return result;
	}
	
	@RequestMapping(value="edit")
	public ModelAndView edit(BaseResource baseResource) {
		logger.info("***** BaseResourceController.edit method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("baseResource", baseResource);
		baseResourceService.update(dto);
		
		logger.info("***** BaseResourceController.edit method end*****");
		return AJAX();
	}
	
	@RequestMapping(value="delete")
	public ModelAndView delete(String code) {
		logger.info("***** BaseResourceController.delete method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("code", code);
		dto = baseResourceService.delete(dto);
		
		logger.info("***** BaseResourceController.delete method end*****");
		return AJAX(dto.getResultCode(),dto.getResultMsg());
	}
	@RequestMapping(value="del")
	public ModelAndView del(String ids) {
		logger.info("***** BaseResourceController.delete method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("ids", ids);
		baseResourceService.deleteAll(dto);
		
		logger.info("***** BaseResourceController.delete method end*****");
		return AJAX();
	}

}
