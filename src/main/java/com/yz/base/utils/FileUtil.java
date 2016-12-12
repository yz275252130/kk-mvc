package com.yz.base.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static final String separator = System.getProperty("file.separator")
			.replace("\\", "/");
	public static final long K = 1024;
	public static final long M = 1024 * 1024;
	public static final long G = 1024 * 1024 * 1024;

	public static String mkdirYearAndMonth(String filesPath) {

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;

		filesPath += year + "/";
		acumenMkdirs(filesPath);
		filesPath += month + "/";
		acumenMkdirs(filesPath);

		return filesPath;
	}

	/**
	 * 创建文件夹。如果指定路径的文件夹不存在则创建
	 * 
	 * @param filesPath
	 *            文件夹路径
	 */
	public static void acumenMkdirs(String filesPath) {
		File file = new File(filesPath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 删除指定的单个文件
	 * 
	 * @param filepath
	 *            文件路径
	 */
	public static void delete(String filepath) {
		File file = new File(filepath);
		delete(file);
	}

	/**
	 * 删除指定的单个文件
	 */
	public static void delete(File file) {
		if (file.exists()) {
			file.delete();
		}
	}


	/**
	 * 功能描述：上传文件 创建人：yangz
	 * 
	 * @return
	 */
	public static synchronized String uploadFile(MultipartFile file,String fName) {
		if (file != null) {
			String fileName = fName;
			String root = getPrefixPath();
			File rootPath = new File(root);
			if (!rootPath.exists() || !rootPath.isDirectory()) {
				rootPath.mkdirs();
			}
			try {
				File f = new File(root, fileName);
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存出错!");
			}
			return separator + fileName;
		}
		return null;
	}
	
	public static synchronized String saveFile(File file,String fName) {
		if (file != null) {
			String root = getPrefixPath();
			File rootPath = new File(root);
			if (!rootPath.exists() || !rootPath.isDirectory()) {
				rootPath.mkdirs();
			}
			try {
				File f = new File(root, fName);
				FileOutputStream fos = new FileOutputStream(f);
				FileInputStream fis = new FileInputStream(file);
				byte b[] = new byte[1024];
				int n=0;
				while((n=fis.read(b))!=-1){
					fos.write(b,0,n);
				}
				fos.flush();
				fos.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存出错!");
			}
			return separator + fName;
		}
		return null;
	}
	
 
	/*
	 * 访问地址前缀
	 */
	public static String getPrefixUrl() {
		String fileUrl = Constants.PROP_MAP.get(Constants.FILE_URL);
		String saveFolder = Constants.PROP_MAP.get(Constants.SAVE_FOLDER);
		String prefixUrl = fileUrl + saveFolder;
		if (!fileUrl.endsWith(separator) && !saveFolder.startsWith(separator)) {
			prefixUrl = fileUrl + separator + saveFolder;
		}
		return prefixUrl;
	}

	/*
	 * 保存地址前缀
	 */
	public static String getPrefixPath() {
		String root = Constants.PROP_MAP.get(Constants.FILE_PATH);
		String url = Constants.PROP_MAP.get(Constants.SAVE_FOLDER);
		if (!root.endsWith(separator) && !url.startsWith(separator)) {
			root += separator;
		}
		return root + url;
	}
	
}
