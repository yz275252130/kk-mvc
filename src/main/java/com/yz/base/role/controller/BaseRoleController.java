/**   
 * @Title: BaseRoleController.java 
 * @Package com.yz.base.role.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月26日 下午3:11:00  
 */
package com.yz.base.role.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yz.base.role.pojo.BaseRole;
import com.yz.base.role.service.BaseRoleService;
import com.yz.core.datastructure.dto.Dto;
import com.yz.core.datastructure.dto.impl.BaseDto;
import com.yz.core.datastructure.page.Pagination;
import com.yz.core.web.BaseController;

/** 
 * @ClassName: BaseRoleController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月26日 下午3:11:00 
 *  
 */
@Controller
@RequestMapping(value="/baseRole")
public class BaseRoleController extends BaseController{
	private static final Logger logger = (Logger) LoggerFactory.getLogger(BaseRoleController.class);
	
	@Autowired
	private BaseRoleService baseRoleService;
	
	@RequestMapping(value="list")
	public ModelAndView list(){
		logger.info("***** BaseRoleController.list method begin*****");
		ModelAndView result =  new ModelAndView("/base/role/role_list");
		logger.info("***** BaseRoleController.list method end*****");
		return result;
	}
	
	
	@RequestMapping(value="listData")
	public Pagination listData(String pCode){
		logger.info("***** BaseRoleController.listData method begin*****");
		
		Pagination page = baseRoleService.getList(getDto());
		
		logger.info("***** BaseRoleController.listData method end*****");
		return page;
	}
	
	
	@RequestMapping(value="bfAdd")
	public ModelAndView bfAdd(){
		logger.info("***** BaseRoleController.bfAdd method begin*****");
		ModelAndView result =  new ModelAndView("/base/role/role_add");
		logger.info("***** BaseRoleController.bfAdd method end*****");
		return result;
	}
	
	@RequestMapping(value="add")
	public ModelAndView add(BaseRole baseRole){
		logger.info("***** BaseRoleController.add method begin*****");
		Dto dto = new BaseDto();
		dto.put("baseRole", baseRole);
		baseRoleService.save(dto);
		logger.info("***** BaseRoleController.add method end*****");
		return AJAX();
	}
	
	@RequestMapping(value="bfEdit")
	public ModelAndView bfEdit(String id){
		logger.info("***** BaseRoleController.bfEdit method begin*****");
		ModelAndView result = new ModelAndView("/base/role/role_edit");

		Dto dto = new BaseDto();
		dto.put("id", id);
		BaseRole baseRole = baseRoleService.get(dto);
		result.addObject("baseRole", baseRole);
		
		logger.info("***** BaseRoleController.bfEdit method end*****");
		return result;
	}
	
	
	@RequestMapping(value="edit")
	public ModelAndView edit(BaseRole baseRole) {
		logger.info("***** BaseRoleController.edit method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("baseRole", baseRole);
		baseRoleService.update(dto);
		
		logger.info("***** BaseRoleController.edit method end*****");
		return AJAX();
	}
	
	@RequestMapping(value="delete")
	public ModelAndView delete(String ids) {
		logger.info("***** BaseRoleController.delete method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("ids", ids);
		baseRoleService.delete(dto);
		
		logger.info("***** BaseRoleController.delete method end*****");
		return AJAX();
	}
	
	@RequestMapping(value="roleRes")
	public ModelAndView roleRes(){
		logger.info("***** BaseRoleController.roleRes method begin*****");
		ModelAndView result =  new ModelAndView("/base/role/roleres");
		logger.info("***** BaseRoleController.roleRes method end*****");
		return result;
	}
	
	@RequestMapping(value="getRoleRes")
	public Object getRoleRes(String code) {
		logger.info("***** BaseRoleController.getRoleRes method begin*****");
		Dto dto = new BaseDto();
		dto.put("code", code);
		dto = baseRoleService.getRoleRes(dto);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(dto.get("treeData"));
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("treeData", jsonArray);
		jsonObject2.put("roleRes", dto.get("roleRes"));
		logger.info("***** BaseRoleController.getRoleRes method end*****");
		return jsonObject2;
	}
	
	
	@RequestMapping(value="editRoelRes")
	public ModelAndView editRoelRes(String roleCode, String res) {
		logger.info("***** BaseRoleController.edit method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("roleCode", roleCode);
		dto.put("res", res);
		baseRoleService.updateRoleRes(dto);
		
		logger.info("***** BaseRoleController.edit method end*****");
		return AJAX();
	}
	
}
