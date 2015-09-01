package com.iamghf.web.views;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractCachingViewResolver;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import com.ghf.core.bean.InterCfgBean;
import com.ghf.core.cache.InterCfgCache;
import com.iamghf.web.common.WebConstant;

public class MyViewSolver extends AbstractCachingViewResolver {
	private transient static final Logger log = Logger
			.getLogger(MyViewSolver.class);
	private String suffix;// บ๓ืบ
	private View view;
	private String location;
	private String viewName;
	private static int BUFFER_SIZE = 4096;
	@Override
	protected View loadView(String viewName, Locale locale) throws Exception {
		//log.debug("solver is "+viewName);
		int index = viewName.indexOf(WebConstant.WEB_JUMP_FLAG.SV_JUMP);
		if(index>-1){
			String code = viewName.substring(index+WebConstant.WEB_JUMP_FLAG.SV_JUMP.length());
			InterCfgBean bean = InterCfgCache.getCfgPage(code);
			String url = bean != null
					&& StringUtils.isNotEmpty(bean.getImplclass()) ? bean
					.getImplclass() : "";
			Resource resource = null;
			try {
				log.debug(url);
				resource = getApplicationContext().getResource(url);
			} catch (Exception e) {
				// this exception should be catched and return null in order to call
				// next view resolver
				log.error("No file found for file: " + url);
				return null;
			}
			MyView view = this.getApplicationContext()
					.getBean(this.viewName, MyView.class);
			view.setUrl(url);
			view.setResponseContent(inputStreamTOString(resource.getInputStream(),
					"UTF-8"));
			return view;
		}else{
			return null;
		}
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public static String inputStreamTOString(InputStream in, String encoding)
			throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1){
			outStream.write(data, 0, count);
		}
		data = null;
		return new String(outStream.toByteArray(), encoding);
	}
}
