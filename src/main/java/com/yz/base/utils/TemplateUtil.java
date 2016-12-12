/**   
 * @Title: TemplateIUtil.java 
 * @Package com.yz.base.utils 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author yz  
 * @date 2016年7月7日 下午2:11:15  
 */
package com.yz.base.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/** 
 * @ClassName: TemplateIUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author yz
 * @date 2016年7月7日 下午2:11:15 
 *  
 */
public class TemplateUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(TemplateUtil.class);
	public static final String separator = System.getProperty("file.separator").replace("\\", "/");
    private static FreeMarkerConfigurer freeMarkerConfigurer;
    
    private static Configuration DEFAULT_CONFIG;
    
    private static String FILE_ROOT_PATH;
	/**
	 * @method: parse2File 
	 * @Description: 
	 * @param: 
	 * @return: void
	 * @throws:
	 */
    public static void parse2File(String ftlName, Map<String, Object> data, String FileMidPath, String fileName) throws Exception {
        String filePath = FileMidPath+separator+"html";
        String fileFullPath = filePath +separator + fileName;
        Writer out = null;
        Template t;
        try {
            File htmlPathFile = new File(filePath);
            if (!htmlPathFile.isDirectory()) {
            	htmlPathFile.mkdirs();
            }
            if (freeMarkerConfigurer != null) {
                t = freeMarkerConfigurer.getConfiguration().getTemplate(ftlName);
                out = new OutputStreamWriter(new FileOutputStream(fileFullPath), "UTF-8");
                t.process(data, out);
            }
            else {
                t = getDefaultConfig().getTemplate(ftlName);
                out = new OutputStreamWriter(new FileOutputStream(fileFullPath), "UTF-8");
                t.process(data, out);
            }
        }
        catch (TemplateException e) {
            throw new Exception(e);
        }
        catch (IOException e) {
            throw new Exception(e);
        }
        finally {
            if (out != null) {
                try {
                    out.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getFileRootPath() {
        if (FILE_ROOT_PATH == null)
        	FILE_ROOT_PATH = FileUtil.getPrefixPath();
        return FILE_ROOT_PATH;
    }
    
    private static Configuration getDefaultConfig() {
        if (DEFAULT_CONFIG == null)
            DEFAULT_CONFIG = createDefaultCfg();
        return DEFAULT_CONFIG;
    }
    
    /**
     * <获取模板配置项>
     * @param loaderPath
     * @return
     */
    private static Configuration createDefaultCfg() {
        Configuration cfg = new Configuration();
        
        // 设置模板文件所在的目录
        try {
            cfg.setDirectoryForTemplateLoading(new File(getTemplateLoaderPath()));
        }
        catch (IOException e) {
            logger.error("设置模板加载目录失败", e);
        }
        
        Properties p = new Properties();  
        try {
            p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("freemarker.properties"));
            cfg.setSettings(p);
        }
        catch (IOException e) {
            logger.error("获取freemarker.properties配置项io流异常", e);
        }
        catch (TemplateException e) {
            logger.error("配置模板发生异常", e);
        }
        
        return cfg;
    }
    
    /**
     * <获取模板加载目录>
     * @return
     */
    private static String getTemplateLoaderPath() {
    	String  aString  = TemplateUtil.class.getResource(separator).getFile();
        String path = TemplateUtil.class.getResource("/").getFile();
        String fileSeparator = System.getProperty("file.separator");
        path = path.replace(fileSeparator, "/");
        path = path.substring(0, path.indexOf("WEB-INF")) + "template/";
        return path;
    }
}
