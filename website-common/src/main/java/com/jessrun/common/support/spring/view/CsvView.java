/**
 * 
 */
package com.jessrun.common.support.spring.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

/**
 * @author unicorn
 * @2011-11-4
 */
public class CsvView extends AbstractView {

	private String resFileName;
	private String destFileName;
	private byte[] byt = new byte[64 * 1024];
	
	private static final String contentType = "application/vnd.ms-excel;charset=UTF-8";
	private final static String resPath = "/template/";
	private final static String suffix = ".csv";
	
	/**
	 * 
	 * @param resFileName 模板文件名
	 * @param map 填充的数据
	 */
	public CsvView(String resFileName){
		this.resFileName = resFileName;
		this.destFileName = String.valueOf(System.currentTimeMillis()+suffix);
	}
	
	/**
	 * 
	 * @param  模板文件名
	 * @param map 填充的数据
	 * @param destFileName 保存的文条件名
	 */
	public CsvView(String resFileName,String destFileName){
		this.resFileName = resFileName;
		this.destFileName = destFileName + suffix;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> arg0,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String urlPath = resPath + resFileName ;
		URL url =  XlsView.class.getResource(urlPath);
		String path = url.getPath();
		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(destFileName.getBytes("gbk"),"ISO8859-1") + "\"");
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(new File(path));
			out = response.getOutputStream();
			int readLength;
			while ((readLength = in.read(byt)) != -1) {
				out.write(byt, 0, readLength);
			}
			response.flushBuffer();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (in != null)
				in.close();
			if(out != null)
				out.close();
		}
	}
}
