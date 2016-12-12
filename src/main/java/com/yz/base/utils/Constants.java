/**
 * 常量类
 */
package com.yz.base.utils;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Constants {
	
	public static String USERCENTER_HOST = "userCenterHost";// 应用Code
	public static String USERCENTER_ADDUSER_URL = "userCenterAddUserUrl";// 应用Code
	public static String USERCENTER_DELUSER_URL = "userCenterDelUserUrl";// 应用Code
	public static String APP_CODE = "appCode";// 应用Code
    public static String FILE_PATH = "savePath";// #图片存放地址
    public static String FILE_URL = "filePath";// #图片访问地址
    public static String SAVE_FOLDER = "saveFolder";// #图片存放文件夹
    public static String PROPERTIES_FILE_NAME = "app.properties";
    public static Map<String, String> PROP_MAP = new HashMap<String, String>();
    public static Map<String, String> VAL_CODE = new HashMap<String, String>();
    public static String[] TVMALL_MULTIPLE_TVS_MAP ;
    static {
        // 加载properties文件
        Properties props = new Properties();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String files[] = Constants.PROPERTIES_FILE_NAME.split(";");
            for (int i = 0; i < files.length; i++) {
            	 InputStream in = classLoader.getResourceAsStream(files[i]);
                 props.load(in);
                 Enumeration<?> en = props.propertyNames();
                 while (en.hasMoreElements()) {
                     String key = (String) en.nextElement();
                     String value = props.getProperty(key);
                     Constants.PROP_MAP.put(key, value);
                 }
                 in.close();
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}