/**   
 * @Title: HttpClientUtils.java 
 * @Package com.yz.base.utils 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年6月30日 上午10:14:13  
 */
package com.yz.base.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: HttpClientUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author yz
 * @date 2016年6月30日 上午10:14:13
 * 
 */
public class HttpClientUtils {
	private static final Log logger = LogFactory.getLog(HttpClientUtils.class);

	public static JSONObject doPostRespJson(String requestAddress, List<BasicNameValuePair> params) {
		String result = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost post = new HttpPost(requestAddress);
		try {
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			CloseableHttpResponse response = httpClient.execute(post);

			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		JSONObject jsonObject = new JSONObject();
		if (StringUtils.isNotEmpty(result)) {
			jsonObject.put("resultCode", "300");
			jsonObject.put("resultMsg", "操作失败！");
		}else {
			jsonObject = JSONObject.parseObject(result);
		}
		
		
		
		return jsonObject;
	}
}
