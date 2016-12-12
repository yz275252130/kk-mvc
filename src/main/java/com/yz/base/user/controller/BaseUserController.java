/**   
 * @Title: BaseUserController.java 
 * @Package com.yz.base.user.controller 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年5月30日 上午9:37:13  
 */
package com.yz.base.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yz.base.role.pojo.BaseRole;
import com.yz.base.user.pojo.BaseUser;
import com.yz.base.user.service.BaseUserService;
import com.yz.base.utils.FileUtil;
import com.yz.core.datastructure.dto.Dto;
import com.yz.core.datastructure.dto.impl.BaseDto;
import com.yz.core.datastructure.page.Pagination;
import com.yz.core.web.BaseController;

/** 
 * @ClassName: BaseUserController 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年5月30日 上午9:37:13 
 *  
 */

@Controller
@RequestMapping(value="/baseUser")
public class BaseUserController extends BaseController{
	private static final Logger logger = (Logger) LoggerFactory.getLogger(BaseUserController.class);
	
	@Autowired
	private BaseUserService baseUserService;
	
	@RequestMapping(value="list")
	public ModelAndView list(){
		logger.info("***** BaseUserController.list method begin*****");
		ModelAndView result =  new ModelAndView("/base/user/user_list");
		Pagination page = baseUserService.getList(getDto());
		result.addObject("url", FileUtil.getPrefixUrl());
		result.addObject("page", page);
		logger.info("***** BaseUserController.list method end*****");
		return result;
	}
	
//	
//	@RequestMapping(value="listData")
//	public Pagination listData(){
//		logger.info("***** BaseUserController.listData method begin*****");
//		
//		Pagination page = baseUserService.getList(getDto());
//		
//		logger.info("***** BaseUserController.listData method end*****");
//		return page;
//	}
//	
	
	@RequestMapping(value="bfAdd")
	public ModelAndView bfAdd(){
		logger.info("***** BaseUserController.bfAdd method begin*****");
		ModelAndView result =  new ModelAndView("/base/user/user_add");
		logger.info("***** BaseUserController.bfAdd method end*****");
		return result;
	}
	
	@RequestMapping(value="add")
	public ModelAndView add(BaseUser baseUser){
		logger.info("***** BaseUserController.add method begin*****");
		Dto dto = new BaseDto();
		dto.put("baseUser", baseUser);
		dto = baseUserService.save(dto);
		logger.info("***** BaseUserController.add method end*****");
		return AJAX(dto.getResultCode(), dto.getResultMsg());
	}
	
	@RequestMapping(value="bfUserEdit")
	public ModelAndView bfUserEdit(String id){
		logger.info("***** BaseUserController.bfEdit method begin*****");
		ModelAndView result = new ModelAndView("/base/user/user_info");

		Dto dto = new BaseDto();
		dto.put("id", id);
		BaseUser baseUser = baseUserService.get(dto);
		result.addObject("baseUser", baseUser);
		result.addObject("url", FileUtil.getPrefixUrl());
		logger.info("***** BaseUserController.bfEdit method end*****");
		return result;
	}
	
	@RequestMapping(value="userEdit")
	public ModelAndView userEdit(String pk, String name,String value) {
		logger.info("***** BaseUserController.edit method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("id", Integer.valueOf(pk));
		dto.put("name", name);
		dto.put("value", value);
		try {
			baseUserService.updateUser(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return AJAX("300","操作失败！系统错误！");
		}
		logger.info("***** BaseUserController.edit method end*****");
		return AJAX();
	}
	
	@RequestMapping(value="bfEdit")
	public ModelAndView bfEdit(String id){
		logger.info("***** BaseUserController.bfEdit method begin*****");
		ModelAndView result = new ModelAndView("/base/user/user_edit");
		Dto dto = new BaseDto();
		dto.put("id", id);
		BaseUser baseUser = baseUserService.get(dto);
		result.addObject("baseUser", baseUser);
		logger.info("***** BaseUserController.bfEdit method end*****");
		return result;
	}
	
	
	@RequestMapping(value="edit")
	public ModelAndView edit(BaseUser baseUser) {
		logger.info("***** BaseUserController.edit method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("baseUser", baseUser);
		baseUserService.update(dto);
		
		logger.info("***** BaseUserController.edit method end*****");
		return AJAX();
	}
	
	@RequestMapping(value="delete")
	public ModelAndView delete(String ids) {
		logger.info("***** BaseUserController.delete method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("ids", ids);
		baseUserService.delete(dto);
		
		logger.info("***** BaseUserController.delete method end*****");
		return AJAX();
	}
	
	@RequestMapping(value="editStatus")
	public ModelAndView editStatus(String pk,  Integer value) {
		logger.info("***** BaseUserController.editStatus method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("code", pk);
		dto.put("status", value);
		baseUserService.updateStatus(dto);
		
		logger.info("***** BaseUserController.editStatus method end*****");
		return AJAX();
	}
	
	@RequestMapping(value="editUserRole")
	public ModelAndView editUserRole(String pk,  @RequestParam(value = "value[]") String[] value ) {
		logger.info("***** BaseUserController.editUserRole method begin*****");
		
		Dto dto = new BaseDto();
		dto.put("userCode", pk);
		dto.put("roles", value);
		baseUserService.updateUserRole(dto);
		
		logger.info("***** BaseUserController.editUserRole method end*****");
		return AJAX();
	}
	
	
	@RequestMapping(value="bfChangePassword")
	public ModelAndView bfChangePassword(){
		logger.info("***** SystemController.list bfChangePassword begin*****");
		
		ModelAndView result =  new ModelAndView("/base/user/changePassword");
		logger.info("***** SystemController.list bfChangePassword end*****");
		return result;
	}
	
	@RequestMapping(value="changePassword")
	public ModelAndView changePassword(String password,String newPassword,String confirmPassword,String userAccount){
		logger.info("***** BaseUserController.list changePassword begin*****");
		
		Dto dto = new BaseDto();
		dto.put("newPassword", newPassword);
		dto.put("password", password);
		dto.put("confirmPassword", confirmPassword);
		dto.put("userAccount", userAccount);
		dto = baseUserService.updatePassword(dto);
		
		logger.info("***** BaseUserController.list changePassword end*****");
		return AJAX(dto.getResultCode(),dto.getResultMsg());
	}
	
	@RequestMapping(value="getRoles")
	public JSONArray getRoles(){
		logger.info("***** BaseUserController.bfEditUserRole method begin*****");
		List<BaseRole> baseRoleList = (List<BaseRole>) baseUserService.getRoel();
		JSONArray jsonArray = new JSONArray();
		for (BaseRole baseRole : baseRoleList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("value", baseRole.getCode());
			jsonObject.put("text", baseRole.getRoleName());
			jsonArray.add(jsonObject);
		}		
		logger.info("***** BaseUserController.bfEditUserRole method end*****");
		return jsonArray;
	}
	
	
	@RequestMapping(value="editPhoto")
	public ModelAndView editPhoto(MultipartFile photoFile,Integer id){
		logger.info("***** BaseUserController.editPhoto method begin*****");
		String photoFileName = photoFile.getOriginalFilename();
		photoFileName = String.valueOf(System.currentTimeMillis())+ photoFile.getOriginalFilename().substring(photoFileName.lastIndexOf("."));
		String photoImg = FileUtil.uploadFile(photoFile, photoFileName);
		Dto dto = new BaseDto();
		dto.put("id", id);
		dto.put("name", "userPhoto");
		dto.put("value", photoImg);
		try {
			baseUserService.updateUser(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return AJAX("300","操作失败！系统错误！");
		}
		
		logger.info("***** BaseUserController.editPhoto method end*****");
		return AJAX(FileUtil.getPrefixUrl()+photoImg);
	}
	
	@RequestMapping(value="editShowing")
	public ModelAndView editShowing(MultipartFile showingFile,Integer id){
		logger.info("***** BaseUserController.editPhoto method begin*****");
		String photoFileName = showingFile.getOriginalFilename();
		photoFileName = String.valueOf(System.currentTimeMillis())+ showingFile.getOriginalFilename().substring(photoFileName.lastIndexOf("."));
		String showingImg = FileUtil.uploadFile(showingFile, photoFileName);
		Dto dto = new BaseDto();
		dto.put("id", id);
		dto.put("name", "userShowing");
		dto.put("value", showingImg);
		try {
			baseUserService.updateUser(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return AJAX("300","操作失败！系统错误！");
		}
		
		logger.info("***** BaseUserController.editPhoto method end*****");
		return AJAX(FileUtil.getPrefixUrl()+showingImg);
	}
	
}
