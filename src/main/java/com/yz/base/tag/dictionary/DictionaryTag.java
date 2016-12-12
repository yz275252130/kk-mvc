/**
 * 数据字典标签类
 */
package com.yz.base.tag.dictionary;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yz.base.dictionary.pojo.BaseDictionary;
import com.yz.base.dictionary.service.BaseDictionaryService;
import com.yz.core.application.ApplicationContextHolder;
import com.yz.core.datastructure.dto.Dto;
import com.yz.core.datastructure.dto.impl.BaseDto;



/**
 * 数据字典标签类
 * 
 * @author panjin
 * 
 */
@SuppressWarnings("serial")
public class DictionaryTag extends BodyTagSupport {

	private static final Log logger = LogFactory.getLog(DictionaryTag.class);

	// 类型（只能为"select" or "text"）
	private String type;

	// 字典编码
	private String code;

	// 字典名称
	private String value;
	
	// 是否多选
	private boolean multiple;

	// select 标签名称
	private String name;

	// select 标签onchange事件
	private String change;

	private String selectClass;
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the multiple
	 */
	public boolean isMultiple() {
		return multiple;
	}

	/**
	 * @param multiple the multiple to set
	 */
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the change
	 */
	public String getChange() {
		return change;
	}

	/**
	 * @param change the change to set
	 */
	public void setChange(String change) {
		this.change = change;
	}

	/**
	 * @return the selectClass
	 */
	public String getSelectClass() {
		return selectClass;
	}

	/**
	 * @param selectClass the selectClass to set
	 */
	public void setSelectClass(String selectClass) {
		this.selectClass = selectClass;
	}

	@Override
	public int doEndTag() throws JspException {
		StringBuffer html = new StringBuffer();
		BaseDictionaryService baseDictionaryService = (BaseDictionaryService) ApplicationContextHolder.getBean("BaseDictionaryService");
		Dto dto = new BaseDto();
		dto.put("pCode", code);
		List<BaseDictionary> dList = baseDictionaryService.getListByPcode(dto);
		try {
			if (StringUtils.equals(type, "select")) { // select标签
				
				html.append("<select ");
				html.append("class=\""+selectClass+"\" "); 
				html.append("name=\""+name+"\""); 
				if (multiple) {
					html.append(" multiple"); 
				}
				html.append(" >"); 
				for (BaseDictionary baseDictionary : dList) {
					html.append("<option value=\""+baseDictionary.getCode()+"\" "); 
					if (multiple) {
						if (StringUtils.isNotEmpty(value)) {
							String[] values = value.split(",");
							for (String string : values) {
								if (StringUtils.equals(string, baseDictionary.getCode())) {
									html.append("selected=\"selected\""); 
								}
							}
						}
					}else{
						if (StringUtils.isNotEmpty(value)) {
							if (StringUtils.equals(value, baseDictionary.getCode())) {
								html.append("selected=\"selected\""); 
							}
						}
					}
					
					html.append(" >");
					html.append(baseDictionary.getName()); 
					html.append("</option>"); 
				}
				
				html.append("</select>");
			} 
			pageContext.getOut().write(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}


}
