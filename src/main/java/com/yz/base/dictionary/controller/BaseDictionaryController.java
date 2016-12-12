/**   
 * @Title: BaseDictionaryController.java 
 * @Package com.yz.base.dictionary.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月31日 下午5:28:24  
 */
package com.yz.base.dictionary.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yz.base.dictionary.pojo.BaseDictionary;
import com.yz.base.dictionary.service.BaseDictionaryService;
import com.yz.base.resource.pojo.BaseResource;
import com.yz.core.datastructure.dto.Dto;
import com.yz.core.datastructure.dto.impl.BaseDto;
import com.yz.core.datastructure.page.Pagination;
import com.yz.core.web.BaseController;

/**
 * @ClassName: BaseDictionaryController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author yz
 * @date 2016年5月31日 下午5:28:24
 * 
 */
@Controller
@RequestMapping(value = "/baseDictionary")
public class BaseDictionaryController extends BaseController {
	private static final Logger logger = (Logger) LoggerFactory.getLogger(BaseDictionaryController.class);

	@Autowired
	private BaseDictionaryService baseDictionaryService;
	
	@RequestMapping(value="listData")
	public JSONArray listData(String pCode){
		logger.info("***** BaseDictionaryController.listData method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("pCode", pCode);
		List<BaseDictionary> baseDictionaries = baseDictionaryService.getListByPcode(dto);
		JSONArray jsonArray = new JSONArray();
		for (BaseDictionary baseDictionary : baseDictionaries) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", baseDictionary.getCode());
			jsonObject.put("text", baseDictionary.getName());
			jsonArray.add(jsonObject);
		}
		logger.info("***** BaseDictionaryController.listData method end*****");
		return jsonArray;
	}
	
	@RequestMapping(value="dictionary")
	public ModelAndView dictionary(){
		logger.info("***** BaseDictionaryController.dictionary method begin*****");
		ModelAndView result =  new ModelAndView("/base/dictionary/dictionary");
		logger.info("***** BaseDictionaryController.dictionary method end*****");
		return result;
	}
	
	@RequestMapping(value="dictionary_tree")
	public Object getDictionaryTree(){
		logger.info("***** BaseDictionaryController.getDictionaryTree method begin*****");
		JSONObject jsonObject = baseDictionaryService.getDictionaryTree();
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(jsonObject);
		
		logger.info("***** BaseDictionaryController.getDictionaryTree method end*****");
		return jsonArray;
	}
	
	@RequestMapping(value="list")
	public ModelAndView list(String code){
		logger.info("***** BaseDictionaryController.list method begin*****");
		ModelAndView result =  new ModelAndView("/base/dictionary/dictionary_list");
		result.addObject("code", code);
		logger.info("***** BaseDictionaryController.list method end*****");
		return result;
	}
	
	@RequestMapping(value="listByPcode")
	public Pagination listByPcode(String pCode){
		logger.info("***** BaseDictionaryController.listByPcode method begin*****");

		Dto dto = getDto();
		dto.put("pCode", pCode);
		Pagination page = baseDictionaryService.getList(dto);
		
		logger.info("***** BaseDictionaryController.listByPcode method end*****");
		return page;
	}
	
	@RequestMapping(value="bfAdd")
	public ModelAndView bfAdd(){
		logger.info("***** BaseDictionaryController.bfAdd method begin*****");
		ModelAndView result =  new ModelAndView("/base/dictionary/dictionary_add");
		logger.info("***** BaseDictionaryController.bfAdd method end*****");
		return result;
	}
	
	@RequestMapping(value="mBfAdd")
	public ModelAndView mBfAdd(){
		logger.info("***** BaseDictionaryController.mBfAdd method begin*****");
		ModelAndView result =  new ModelAndView("/base/dictionary/dictionary_mAdd");
		logger.info("***** BaseDictionaryController.mBfAdd method end*****");
		return result;
	}

	
	@RequestMapping(value="add")
	public ModelAndView add(BaseDictionary baseDictionary){
		logger.info("***** BaseDictionaryController.add method begin*****");
		Dto dto = new BaseDto();
		dto.put("baseDictionary", baseDictionary);
		baseDictionaryService.save(dto);
		logger.info("***** BaseDictionaryController.add method end*****");
		return AJAX();
	}
	
	@RequestMapping(value="bfEdit")
	public ModelAndView bfEdit(Integer id){
		logger.info("***** BaseDictionaryController.bfEdit method begin*****");
		ModelAndView result = new ModelAndView("/base/dictionary/dictionary_edit");

		Dto dto = new BaseDto();
		dto.put("id", id);
		BaseDictionary baseDictionary =  baseDictionaryService.get(dto);
		result.addObject("baseDictionary", baseDictionary);
		
		logger.info("***** BaseDictionaryController.bfEdit method end*****");
		return result;
	}
	
	@RequestMapping(value="mBfEdit")
	public ModelAndView mBfEdit(Integer id){
		logger.info("***** BaseDictionaryController.mBfEdit method begin*****");
		ModelAndView result = new ModelAndView("/base/dictionary/dictionary_mEdit");

		Dto dto = new BaseDto();
		dto.put("id", id);
		BaseDictionary baseDictionary =  baseDictionaryService.get(dto);
		result.addObject("baseDictionary", baseDictionary);
		
		logger.info("***** BaseDictionaryController.mBfEdit method end*****");
		return result;
	}
	
	@RequestMapping(value="edit")
	public ModelAndView edit(BaseDictionary baseDictionary) {
		logger.info("***** BaseDictionaryController.edit method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("baseDictionary", baseDictionary);
		baseDictionaryService.update(dto);
		
		logger.info("***** BaseDictionaryController.edit method end*****");
		return AJAX();
	}
	
	@RequestMapping(value="delete")
	public ModelAndView delete(String code) {
		logger.info("***** BaseDictionaryController.delete method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("code", code);
		dto = baseDictionaryService.delete(dto);
		
		logger.info("***** BaseDictionaryController.delete method end*****");
		return AJAX(dto.getResultCode(),dto.getResultMsg());
	}
	@RequestMapping(value="del")
	public ModelAndView del(String ids) {
		logger.info("***** BaseDictionaryController.delete method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("ids", ids);
		baseDictionaryService.deleteAll(dto);
		
		logger.info("***** BaseDictionaryController.delete method end*****");
		return AJAX();
	}
	
}
